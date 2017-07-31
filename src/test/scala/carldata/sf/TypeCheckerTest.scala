package carldata.sf

import carldata.sf.core.Core
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

  it should "check incorrect number of params" in {
    val code =
      """
        |module Test1
        |
        |def min(a: Number, b: Number): Number = a
        |def main(a: Number, b: Number): Number = min(a, b, a)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch function parameter type mismatch" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a: Number, b: Number): Number = a
        |def main(a: Number, b: String): Number = my_fun(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch relation operation type mismatch" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: String): Bool = a > b
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch binary operation type mismatch" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: String): Number = a + b
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch binary operation return type" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Bool = a + b
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "type check minus operator" in {
    val code =
      """
        |module Test1
        |def main(a: Number): Number = -a*-12
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe true
  }

  it should "type check minus on wrong type" in {
    val code =
      """
        |module Test1
        |def main(a: Bool): Number = -a
      """.stripMargin
    val ast = Compiler.compile(code, Seq(Core.header))
    ast.isRight shouldBe false
  }

  it should "catch bool operation types" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Bool, b: Number): Bool = !a && b
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

}