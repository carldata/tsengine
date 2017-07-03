package com.carl.tsengine.compiler

import com.carl.tsengine.compiler.AST.{FunExpr, ScriptAST}

/**
  * Script parser
  */
object Parser {

  def parse(code: String): ScriptAST = {
    ScriptAST(Seq(), FunExpr("empty_series", Seq()))
  }
}
