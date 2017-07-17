package com.carl.sf

import com.carl.sf.core.Core
import org.scalatest._


/**
  * Mostly tests for Semantic Analysis module
  */
class TypeCheckerTest extends FlatSpec with Matchers {

  "TypeChecher" should "catch function return type error" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a: Int, xs: String): String = a
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch external function type mismatch" in {
    val code =
      """
        |module Test1
        |external def min(a: Number, b: Number): Number
        |def my_fun(a: Number, b: Number): String = min(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "type check string literal" in {
    val code =
      """
        |module Test1
        |def main(): String = 'hello'
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe true
  }

  it should "type check number literal" in {
    val code =
      """
        |module Test1
        |def main(): Number = 12
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe true
  }

  it should "type check bool literal" in {
    val code =
      """
        |module Test1
        |def main(): Bool = False
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe true
  }

  it should "not compile wrong relation type" in {
    val code =
      """
        |module Test1
        |def main(a: Number, b: Number): String = a == b
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe false
  }

  it should "compile relation type" in {
    val code =
      """
        |module Test1
        |def main(a: Number, b: Number): Bool = a != b
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe true
  }

//  it should "catch function parameter type mismatch" in {
//    val code =
//      """
//        |module Test1
//        |
//        |def my_fun(a: Number, b: String): Number = a
//        |def main(a: Number, b: String): Number = my_fun(a, b)
//      """.stripMargin
//    val ast = Compiler.compile(code, Seq())
//    ast.isRight shouldBe false
//  }

}