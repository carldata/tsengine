package carldata.sf.compiler

import carldata.sf.compiler.AST._
import carldata.sf.compiler.gen.{FlowScriptLexer, FlowScriptParser}
import carldata.sf.compiler.gen.FlowScriptParser._
import org.antlr.v4.runtime._

import scala.collection.JavaConverters._

/**
  * Script parser.
  * This parser uses ANTLR4 generated Parser and Lexer.
  * What this object adds is conversion of ANTLR AST into FlowScript AST.
  */
object Parser {

  def parse(code: String): Either[String, Module] = {
    val errorListener = new SyntaxErrorListener()
    val input = CharStreams.fromString(code)
    val lexer = new FlowScriptLexer(input)
    lexer.removeErrorListeners()
    lexer.addErrorListener(errorListener)
    val tokens = new CommonTokenStream(lexer)
    val parser = new FlowScriptParser(tokens)
    // Don't show parser error on the console
    parser.removeErrorListeners()
    parser.addErrorListener(errorListener)
    // Now we are ready to run ANTLR parser
    val compilationUnit = parser.compilationUnit()
    // Parser succeeded only if error list is empty
    if(errorListener.getErrors.size() > 0)
      Left(errorListener.getErrors.firstElement())
    else
      Right(convertCompilationUnit(compilationUnit))
  }

  /** Convert ANTLR Context into AST Module node */
  def convertCompilationUnit(ctx: CompilationUnitContext): Module = {
    val xs = ctx.externalFunDef().asScala.map(convertExternFun)
    val funDecl = ctx.functionDefinition().asScala.map(x => convertFunDef(x))
    Module(xs, funDecl)
  }

  /** Convert ANTLR Context into Function Definition node */
  def convertExternFun(ctx: ExternalFunDefContext): ExternalFun = {
    // Function name
    val funName = ctx.Identifier().getText
    // Function params
    val params = if(ctx.paramList() == null) {
      Seq()
    } else {
      ctx.paramList().param().asScala.map{pctx =>
        FunParam(pctx.Identifier().getText, convertTypeDecl(pctx.typeDefinition()))
      }
    }
    // Function type
    val typeDefCtx = ctx.typeDefinition()
    val typeName = convertTypeDecl(typeDefCtx)
    ExternalFun(funName, params, typeName)

  }

  /** Convert ANTLR Context into Function Definition node */
  def convertFunDef(ctx: FunctionDefinitionContext): FunctionDef = {
    // Function name
    val funName = ctx.Identifier().getText
    // Function params
    val params = if(ctx.paramList() == null) {
      Seq()
    } else {
      ctx.paramList().param().asScala.map{pctx =>
        FunParam(pctx.Identifier().getText, convertTypeDecl(pctx.typeDefinition))
      }
    }
    // Function type
    val typeDefCtx = ctx.typeDefinition()
    val typeName = convertTypeDecl(typeDefCtx)
    // function body
    val body = convertBody(ctx.functionBody())
    FunctionDef(funName, params, typeName, body)

  }

  def convertTypeDecl(context: TypeDefinitionContext): TypeDecl = {
    if(context.typeList() != null) {
      val inputTypes = context.typeList().Identifier().asScala.map(_.getText)
      val outputType = context.Identifier.getText
      FunType(inputTypes, outputType)
    } else {
      ValueType(context.Identifier.getText)
    }
  }

  def convertBody(ctx: FunctionBodyContext): FunctionBody = {
    val as = ctx.assignment().asScala.map { actx =>
      Assignment(actx.Identifier().getText, convertExpr(actx.expression()))
    }
    val e = convertExpr(ctx.expression())
    FunctionBody(as, e)
  }

  /** Convert ANTLR Context into Term node */
  def convertExpr(ctx: ExpressionContext): Expression = {
    if(ctx.MultiplyOp() != null) {
      val e1 = convertExpr(ctx.expression(0))
      val e2 = convertExpr(ctx.expression(1))
      val op = ctx.MultiplyOp().getText
      BinaryOpExpr(e1, op, e2)
    } else if(ctx.minusOp != null) {
        val e1 = convertExpr(ctx.expression(0))
        MinusOpExpr(e1)
    } else if(ctx.negOp != null) {
      val e1 = convertExpr(ctx.expression(0))
      NegOpExpr(e1)
    } else if(ctx.addOp != null) {
      val e1 = convertExpr(ctx.expression(0))
      val e2 = convertExpr(ctx.expression(1))
      val op = ctx.addOp.getText
      BinaryOpExpr(e1, op, e2)
    } else if(ctx.boolOp != null) {
      val e1 = convertExpr(ctx.expression(0))
      val e2 = convertExpr(ctx.expression(1))
      val op = ctx.boolOp.getText
      BoolOpExpr(e1, op, e2)
    } else if(ctx.RelationOp() != null) {
      val e1 = convertExpr(ctx.expression(0))
      val e2 = convertExpr(ctx.expression(1))
      val op = ctx.RelationOp().getText
      RelationExpr(e1, op, e2)
    } else if(ctx.ifExpr != null) {
      val e1 = convertExpr(ctx.expression(0))
      val e2 = convertExpr(ctx.expression(1))
      val e3 = convertExpr(ctx.expression(2))
      IfExpr(e1, e2, e3)
    } else if(ctx.funApp() != null) {
      val name = ctx.funApp().Identifier().getText
      val params = if(ctx.funApp().expressionList() == null) {
        Seq()
      } else {
        ctx.funApp().expressionList().expression().asScala.map(convertExpr)
      }
      AppExpr(name, params)
    } else if(ctx.variableExpr() != null) {
      VariableExpr(ctx.variableExpr().Identifier().getText)
    } else if(ctx.stringLiteral() != null){
      val str = ctx.stringLiteral().QuotedString.getText
      StringLiteral(str.substring(1,str.length-1))
    } else if(ctx.numberLiteral() != null){
      val v1 = ctx.numberLiteral().Integer(0).getText
      val v2 = if(ctx.numberLiteral().Integer().size() > 1){
        v1 + "." + ctx.numberLiteral().Integer(1).getText
      } else {
        v1
      }
      NumberLiteral(v2.toFloat)
    } else if(ctx.boolLiteral() != null){
      BoolLiteral(ctx.boolLiteral().TRUE() != null)
    } else {
      // Convert: '(' expression ')'
      convertExpr(ctx.expression(0))
    }
  }

}
