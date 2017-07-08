package com.carl.sf

import org.scalatest._


/**
  * Mostly tests for Semantic Analysis module
  */
class CompilerTest extends FlatSpec with Matchers {

  "Compiler" should "parse correct syntax" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a, xs) = a
      """.stripMargin
    val ast = Compiler.compile(code)
    ast.isRight shouldBe true
  }

  it should "catch: Unresolved variable error" in {
    val code =
      """
        |module Test1
        |
        |def my_fun() = a
      """.stripMargin
    val ast = Compiler.compile(code)
    ast.isRight shouldBe false
  }


  it should "catch: 'a' is already defined in the scope" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a, a) = a
      """.stripMargin
    val ast = Compiler.compile(code)
    ast.isRight shouldBe false
  }

}