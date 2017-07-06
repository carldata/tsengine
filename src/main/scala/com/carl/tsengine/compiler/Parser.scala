package com.carl.tsengine.compiler

import com.carl.tsengine.compiler.AST.{Expr, FunDecl, Module, VarExpr}
import com.carl.tsengine.compiler.gen.FlowScriptParser.{CompilationUnitContext, ExpressionContext, FunctionDeclarationContext}
import com.carl.tsengine.compiler.gen.{FlowScriptLexer, FlowScriptParser}
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
    if(errorListener.getErrors.size() > 0)
      Left(errorListener.getErrors.firstElement())
    else
      Right(convertCompilationUnit(compilationUnit))
  }

  def convertCompilationUnit(ctx: CompilationUnitContext): Module = {
    val moduleName = ctx.moduleDeclaration().Identifier().getText
    val funDecl = convertFunDecl(ctx.functionDeclaration())
    Module(moduleName, funDecl)
  }

  def convertFunDecl(ctx: FunctionDeclarationContext): FunDecl = {
    val funName = ctx.Identifier().getText
    val params = if(ctx.paramList() == null) {
      Seq()
    } else {
      ctx.paramList().param().asScala.map(pctx => pctx.Identifier().getText)
    }
    val body = convertExpr(ctx.expression())
    FunDecl(funName, params, body)

  }

  def convertExpr(ctx: ExpressionContext): Expr = {
    VarExpr(ctx.Identifier().getText)
  }

}
