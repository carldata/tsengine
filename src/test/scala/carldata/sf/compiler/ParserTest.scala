package carldata.sf.compiler

import org.scalatest._


/**
  * Test for checking that we can parse specific syntax
  */
class ParserTest extends FlatSpec with Matchers {

  "Parser" should "return error for wrong syntax" in {
    val code =
      """
        |aaa
        |def my_fun(a: Int, xs: Int): Int = a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isLeft shouldBe true
  }

  it should "parse simple function expression" in {
    val code =
      """
        |def my_fun(a: Int, xs: Int): Int = a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse function application" in {
    val code =
      """
        |def my_fun(a: Int, b: Int): Int = min(a, b)
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse function application without params" in {
    val code =
      """
        |def my_fun(a: Int, b: Int): Int = min()
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse external function definitions" in {
    val code =
      """
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
        |def main(): String = 'hello'
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse string literal in double quotes" in {
    val code =
      """
        |def main(): String = "hello \"world\""
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse numeric literal" in {
    val code =
      """
        |def main(): Number = 123.45
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse boolean literal" in {
    val code =
      """
        |def main(): Bool = True
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse '=='" in {
    val code =
      """
        |def main(a: Number, b: Number): Bool = a == b
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse math expression" in {
    val code =
      """
        |def main(a: Number, b: Number): Number = a + b * 0.5 - a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse brackets" in {
    val code =
      """
        |def main(a: Number, b: Number): Number = (a + b) * 0.5 - a
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse '-' operation" in {
    val code =
      """
        |def main(a: Number): Number = -1
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse && and || expression" in {
    val code =
      """
        |def main(a: Bool, b: Bool): Bool = a || b == !a && b
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse if-then-else" in {
    val code =
      """
        |def main(a: Bool, b: String): Bool = if a then b else ""
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse let ... in" in {
    val code =
      """
        |def main(a: Bool, b: String): Bool =
        |  let c = true
        |  in if a && c then b else ""
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

  it should "parse higher order functions" in {
    val code =
      """
        |external def map(xs: TimeSeries, f: Number => Number): TimeSeries
        |
        |def f(a: Number): Number = 2*a
        |
        |def main(xs: TimeSeries): TimeSeries = map(xs, f)
      """.stripMargin
    val ast = Parser.parse(code)
    ast.isRight shouldBe true
  }

}