package carldata.sf

import org.scalatest._


/**
  * Test SymbolChecker
  */
class SymbolCheckerTest extends FlatSpec with Matchers {

  "SymbolChecker" should "catch: Unresolved variable error" in {
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

  it should "catch function redefinition" in {
    val code =
      """
        |module Test1
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
        |module Test1
        |
        |def min(a: Number, b: Number): Number = a
        |def main(a: Number, b: Number): Number = min(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe true
  }

  it should "check function call params" in {
    val code =
      """
        |module Test1
        |
        |def min(a: Number, b: Number): Number = a
        |def main(a: Number, b: Number): Number = min(a, x)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

}