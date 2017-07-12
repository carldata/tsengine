package com.carl.sf.compiler

import com.carl.sf.compiler.AST._
import com.carl.sf.compiler.gen.FlowScriptParser._
import com.carl.sf.compiler.gen.{FlowScriptLexer, FlowScriptParser}
import org.antlr.v4.runtime._

import scala.collection.JavaConverters._

/**
  * Script parser.
  * This parser uses ANTLR4 generated Parser and Lexer.
  * What this object adds is conversion of ANTLR AST into FlowScript AST.
  */
object Parser {

  def parse(code: String): Either[String, Module] = {
    val input = CharStreams.fromString(code)
    val lexer = new FlowScriptLexer(input)
    val tokens = new CommonTokenStream(lexer)
    val parser = new FlowScriptParser(tokens)
    // Don't show parser error on the console
    parser.removeErrorListeners()
    val errorListener = new SyntaxErrorListener()
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
    val moduleName = ctx.moduleDeclaration().Identifier().getText
    val xs = ctx.externalFunDef().asScala.map(convertExternFun)
    val funDecl = ctx.functionDefinition().asScala.map(x => convertFunDef(x))
    Module(moduleName, xs, funDecl)
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
        FunParam(pctx.Identifier().getText, pctx.typeDefinition().Identifier().getText)
      }
    }
    // Function type
    val typeDefCtx = ctx.typeDefinition()
    val typeName = typeDefCtx.Identifier().getText
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
        FunParam(pctx.Identifier().getText, pctx.typeDefinition().Identifier().getText)
      }
    }
    // Function type
    val typeDefCtx = ctx.typeDefinition()
    val typeName = typeDefCtx.Identifier().getText
    // function body
    val body = convertExpr(ctx.expression())
    FunctionDef(funName, params, typeName, body)

  }

  /** Convert ANTLR Context into Term node */
  def convertExpr(ctx: ExpressionContext): Expression = {
    if(ctx.funApp() != null) {
      val name = ctx.funApp().Identifier().getText
      val params = if(ctx.funApp().expressionList() == null) {
        Seq()
      } else {
        ctx.funApp().expressionList().expression().asScala.map(convertExpr)
      }
      AppExpr(name, params)
    } else {
      VariableExpr(ctx.variableExpr().Identifier().getText)
    }
  }

}
