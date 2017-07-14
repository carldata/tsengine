package com.carl.sf

import com.carl.sf.core.Core
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
        |def my_fun(a: Int, xs: Int): Int = a
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe true
  }

  it should "catch: Unresolved variable error" in {
    val code =
      """
        |module Test1
        |external def min(a: Int, b: Int): Int
        |
        |def my_fun(): Int = a
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch: 'a' is already defined in the scope" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a: String, a: Int): Int = a
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch function return type error" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a: Int, xs: String): String = a
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "compile external functions" in {
    val code =
      """
        |module Test1
        |external def min(a: Int, b: Int): Int
        |
        |def main(a: Int, b: Int): Int = min(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe true
  }

  it should "catch no external function definition" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a: Int, b: Int): Int = min(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch external function type mismatch" in {
    val code =
      """
        |module Test1
        |external def min(a: Int, b: Int): Int
        |def my_fun(a: Int, b: Int): String = min(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "use library script" in {
    val code =
      """
        |module Test1
        |def min(a: Number, b: Number): Number = min(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe true
  }

  it should "use string literal" in {
    val code =
      """
        |module Test1
        |def main(): String = 'hello'
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe true
  }

  it should "use number literal" in {
    val code =
      """
        |module Test1
        |def main(): Number = 12
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe true
  }

  it should "compile bool literal" in {
    val code =
      """
        |module Test1
        |def main(): Bool = False
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe true
  }

}