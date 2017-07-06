package com.carl.tsengine.compiler

import com.carl.tsengine.compiler.AST.{FunBody, FunDecl, Module, VarExpr}
import com.carl.tsengine.compiler.gen.FlowScriptParser.CompilationUnitContext
import com.carl.tsengine.compiler.gen.{FlowScriptLexer, FlowScriptParser}
import org.antlr.v4.runtime._

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
    val compilationUnit = parser.compilationUnit()
    if(errorListener.getErrors.size() > 0)
      Left(errorListener.getErrors.firstElement())
    else
      Right(convertCompilationUnit(compilationUnit))
  }

  def convertCompilationUnit(ctx: CompilationUnitContext): Module = {
    val moduleName = ctx.moduleDeclaration().Identifier().getText
    Module(moduleName,
      FunDecl("my_fun", Seq("a", "xs"),
        FunBody(Seq(), VarExpr("a"))))
  }
}
