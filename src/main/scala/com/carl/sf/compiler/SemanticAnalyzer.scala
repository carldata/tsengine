package com.carl.sf.compiler

import com.carl.sf.compiler.AST.Module

/**
  * Created by Krzysztof Langner on 2017-07-07.
  */
object SemanticAnalyzer {

  def analyze(module: Module): Either[String, Module] = {
    Right(module)
  }
}
