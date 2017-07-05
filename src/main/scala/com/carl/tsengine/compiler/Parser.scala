package com.carl.tsengine.compiler

import com.carl.tsengine.compiler.AST.{FunBody, FunDecl, Module, VarExpr}
import com.carl.tsengine.compiler.gen.{FlowScriptLexer, FlowScriptParser}
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}

/**
  * Script parser.
  * This parser uses ANTLR4 generated Parser and Lexer.
  * What this object adds is conversion of ANTLR AST into FlowScript AST.
  */
object Parser {

  def parse(code: String): Module = {
    val input = CharStreams.fromString(code)
    val lexer = new FlowScriptLexer(input)
    val tokens = new CommonTokenStream(lexer)
    val parser = new FlowScriptParser(tokens)
    println(parser.compilationUnit())
    Module("Test1",
      FunDecl("my_fun", Seq("a", "xs"),
        FunBody(Seq(), VarExpr("a"))))
  }
}
