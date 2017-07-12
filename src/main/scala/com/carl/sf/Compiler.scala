package com.carl.sf

import com.carl.sf.compiler.AST.Module
import com.carl.sf.compiler.{Parser, TypeChecker, SymbolChecker}

/**
  * Compiler for FlowScript. It consists of the following phases:
  *  1. Parser and Lexer implemented with the help of ANTLR
  *  2. Semantic Analysis
  *   2.1. Variable Checker
  *   2.2. Type Checker
  *  3. Code generation
  */
object Compiler {

  /**
    * Compile given code and zero or more library modules.
    * Library modules are used to add definition of external function and types
    */
  def compile(code: String, libs: Seq[String]): Either[String, Module] = {
    Parser.parse(code)
      .flatMap(SymbolChecker.check)
      .flatMap(TypeChecker.check)
  }
}
