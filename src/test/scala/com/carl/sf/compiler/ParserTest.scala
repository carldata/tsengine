package com.carl.sf.compiler

import org.scalatest._


/**
  * Test for checking that we can parse specific syntax
  */
class ParserTest extends FlatSpec with Matchers {

  "Parser" should "return error for wrong syntax" in {
    val code =
      """
        |module Test1 aaa
        |
        |def my_fun(a, xs) = a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isLeft shouldBe true
  }

  it should "compile simple function expression" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a, xs) = a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "compile typed function" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a: Int, xs: String): Int = a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "compile function application" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a: Int, b: Int): Int = min(a, b)
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }
}