package carldata.sf

import org.scalatest._


/**
  * Test SymbolChecker
  */
class SymbolCheckerTest extends FlatSpec with Matchers {

  "SymbolChecker" should "catch: Unresolved variable error" in {
    val code =
      """
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
        |def my_fun(a: String, a: Int): Int = a
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "compile external functions" in {
    val code =
      """
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
        |def my_fun(a: Int, b: Int): Int = min(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch function redefinition" in {
    val code =
      """
        |external def min(a: Number, b: Number): Number
        |
        |def min(a: Number, b: Number): Number = a
        |def my_fun(a: Number, b: Number): Number = min(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "compile function reference" in {
    val code =
      """
        |def min(a: Number, b: Number): Number = a
        |def main(a: Number, b: Number): Number = min(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe true
  }

  it should "check function call params" in {
    val code =
      """
        |def min(a: Number, b: Number): Number = a
        |def main(a: Number, b: Number): Number = min(a, x)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "check if-then-else" in {
    val code =
      """
        |def main(a: Number, b: Number): Number = if a then b else c
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "check let-in" in {
    val code =
      """
        |def main(a: Number, b: Number): Number =
        | let
        |   x = y
        | in
        |   2
      """.stripMargin
    val result = Compiler.compile(code, Seq())
    result.isRight shouldBe false
  }

}