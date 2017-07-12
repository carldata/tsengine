package com.carl.sf

import com.carl.sf.runtime.Core.IntValue
import org.scalatest._


/**
  * Mostly tests for Semantic Analysis module
  */
class InterpreterTest extends FlatSpec with Matchers {

  "Interpreter" should "not run program with wrong number of parameters" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Int, xs: Int): Int = a
      """.stripMargin
    val result = Compiler.compile(code).flatMap { ast =>
      Interpreter.run(ast, Seq())
    }
    result.isRight shouldBe false
  }

  it should "run valid program" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Int, xs: Int): Int = a
      """.stripMargin
    val result = Compiler.compile(code).flatMap { ast =>
      Interpreter.run(ast, Seq(IntValue(1), IntValue(2)))
    }
    result.isRight shouldBe true
  }

    it should "return value of the parameter" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Int, xs: Int): Int = a
      """.stripMargin
    val result = Compiler.compile(code).flatMap { ast =>
      Interpreter.run(ast, Seq(IntValue(1), IntValue(2)))
    }
    result shouldBe Right(IntValue(1))
  }

  it should "call external function" in {
    val code =
      """
        |module Test1
        |external def min(a: Int, b: Int): Int
        |
        |def main(a: Int, b: Int): Int = min(a, b)
      """.stripMargin
    val result = Compiler.compile(code).flatMap { ast =>
      Interpreter.run(ast, Seq(IntValue(11), IntValue(2)))
    }
    result shouldBe Right(IntValue(2))
  }

}