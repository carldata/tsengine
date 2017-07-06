package com.carl.tsengine.compiler

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

  "Parser" should "compile simple function expression" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a, xs) = a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }
}