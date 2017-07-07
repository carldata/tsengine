package com.carl.sf

import com.carl.sf.compiler.AST.Module
import com.carl.sf.compiler.{Parser, SemanticAnalyzer}

/**
  * Compiler for FlowScript. It consists of the following phases:
  *  1. Parser and Lexer implemented with the help of ANTLR
  *  2. Semantic Analysis
  */
object Compiler {

  def compile(code: String): Either[String, Module] = {
    Parser.parse(code)
      .flatMap(SemanticAnalyzer.analyze)
  }
}
