package com.carl.sf

import com.carl.sf.Runtime.{NumberValue, StringValue}
import com.carl.sf.core.Core
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
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(Core).run(ast, "main", Seq())
    }
    result.isRight shouldBe false
  }

  it should "run valid program" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(Core).run(ast, "main", Seq(NumberValue(1), NumberValue(2)))
    }
    result.isRight shouldBe true
  }

    it should "return value of the parameter" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(Core).run(ast, "main", Seq(NumberValue(1), NumberValue(2)))
    }
    result shouldBe Right(NumberValue(1))
  }

  it should "call external function" in {
    val code =
      """
        |module Test1
        |external def min(a: Number, b: Number): Number
        |
        |def main(a: Number, b: Number): Number = min(a, b)
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(Core).run(ast, "main", Seq(NumberValue(11), NumberValue(2)))
    }
    result shouldBe Right(NumberValue(2))
  }

  it should "return value string literal" in {
    val code =
      """
        |module Test1
        |
        |def main(): String = 'hello'
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(Core).run(ast, "main", Seq())
    }
    result shouldBe Right(StringValue("hello"))
  }

}