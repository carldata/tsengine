package com.carl.tsengine.compiler

import com.carl.tsengine.compiler.AST.{FunBody, FunDecl, Module, VarExpr}
import org.antlr.v4.runtime.CharStreams

/**
  * Script parser
  */
object Parser {

  def parse(code: String): Module = {
    val input = CharStreams.fromString(code)
    Module("Test1",
      FunDecl("my_fun", Seq("a", "xs"),
        FunBody(Seq(), VarExpr("a"))))
  }
}
