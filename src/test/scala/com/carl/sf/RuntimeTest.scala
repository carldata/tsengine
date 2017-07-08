package com.carl.sf

import com.carl.sf.Runtime.{IntValue, UnitValue}
import org.scalatest._


/**
  * Mostly tests for Semantic Analysis module
  */
class RuntimeTest extends FlatSpec with Matchers {

  "Runtime" should "not run program with wrong number of parameters" in {
    val code =
      """
        |module Test1
        |
        |def main(a, xs) = a
      """.stripMargin
    val result = Compiler.compile(code).flatMap { ast =>
      Runtime.run(ast, Seq())
    }
    result.isRight shouldBe false
  }

//  it should "run valid program" in {
//    val code =
//      """
//        |module Test1
//        |
//        |def main(a, xs) = a
//      """.stripMargin
//    val result = Compiler.compile(code).flatMap { ast =>
//      Runtime.run(ast, Seq(IntValue(1), IntValue(2)))
//    }
//    result.isRight shouldBe true
//  }

  //  "Runtime" should "return value of the parameter" in {
//    val code =
//      """
//        |module Test1
//        |
//        |def main(a, xs) = a
//      """.stripMargin
//    val result = Compiler.compile(code).flatMap { ast =>
//      Runtime.run(ast, Seq(IntValue(1), IntValue(2)))
//    }
//    result shouldBe Right(IntValue(2))
//  }
}