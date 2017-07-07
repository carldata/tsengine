package com.carl.sf.compiler

import com.carl.sf.compiler.AST.Module

/**
  * Created by Krzysztof Langner on 2017-07-07.
  */
object SemanticAnalyzer {

  def analyze(module: Module): Either[String, Module] = {
    VariableChecker.check(module)
  }
}

private object VariableChecker {

  /** Check for errors in the module. Returns None if no error was found */
  def check(module: Module): Either[String, Module] = {
    val st = new SymbolTable()
    Right(module)
  }
}