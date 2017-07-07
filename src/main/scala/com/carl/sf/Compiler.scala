package com.carl.sf

import com.carl.sf.compiler.AST.Module
import com.carl.sf.compiler.{Parser, SemanticAnalyzer}

/**
  * Compiler for FlowScript
  */
object Compiler {

  def compile(code: String): Either[String, Module] = {
    Parser.parse(code).flatMap(SemanticAnalyzer.analyze)
  }
}
