package carldata.sf.compiler

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
        |def my_fun(a: Int, xs: Int): Int = a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isLeft shouldBe true
  }

  "Parser" should "at least one function definition is required" in {
    val code =
      """
        |module Test1 aaa
        |
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isLeft shouldBe true
  }

  it should "parse simple function expression" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a: Int, xs: Int): Int = a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse function application" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a: Int, b: Int): Int = min(a, b)
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse function application without params" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a: Int, b: Int): Int = min()
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse external function definitions" in {
    val code =
      """
        |module Test1
        |
        |external def min(v1: Int, v2: Int): Int
        |
        |def my_fun(a: Int, b: Int): Int = min(a, b)
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse string literal" in {
    val code =
      """
        |module Test1
        |
        |def main(): String = 'hello'
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse string literal in double quotes" in {
    val code =
      """
        |module Test1
        |
        |def main(): String = "hello "\"world\""
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse numeric literal" in {
    val code =
      """
        |module Test1
        |
        |def main(): Number = 123.45
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse boolean literal" in {
    val code =
      """
        |module Test1
        |
        |def main(): Bool = True
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse '=='" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Bool = a == b
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse math expression" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Number = a + b * 0.5 - a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse brackets" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Number = (a + b) * 0.5 - a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse '-' operation" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number): Number = -1
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse && and || expression" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Bool, b: Bool): Bool = a || b == !a && b
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse if-then-else" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Bool, b: String): Bool = if a then b else ""
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse let ... in" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Bool, b: String): Bool =
        |  let c = true
        |  in if a && c then b else ""
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

}