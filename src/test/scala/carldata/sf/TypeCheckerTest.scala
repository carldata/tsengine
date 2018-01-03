package carldata.sf

import org.scalatest._
import carldata.sf.core.MathModule

/**
  * Mostly tests for Semantic Analysis module
  */
class TypeCheckerTest extends FlatSpec with Matchers {

  "TypeChecker" should "type check number literal" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = xs+12
      """.stripMargin
    val ast = Compiler.compile(code, Seq(MathModule.header))
    ast.isRight shouldBe true
  }

  it should "not compile wrong relation type" in {
    val code =
      """
        |def main(a: TimeSeries, b: TimeSeries): TimeSeries = a == b
      """.stripMargin
    val ast = Compiler.compile(code, Seq(MathModule.header))
    ast.isRight shouldBe false
  }

  it should "compile relation type" in {
    val code =
      """
        |def main(a: TimeSeries): TimeSeries = a != 3
      """.stripMargin
    val ast = Compiler.compile(code, Seq(MathModule.header))
    ast.isRight shouldBe true
  }

  it should "check incorrect number of params" in {
    val code =
      """
        |def min(a: Number, b: Number): Number = a
        |def main(a: Number, b: Number): Number = min(a, b, a)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch function parameter type mismatch" in {
    val code =
      """
        |def my_fun(a: Number, b: Number): Number = a
        |def main(a: Number, b: TimeSeries): Number = my_fun(a, b)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    println(ast)
    ast.isRight shouldBe false
  }

  it should "catch relation operation type mismatch" in {
    val code =
      """
        |def main(a: Number, b: String): Bool = a > b
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch binary operation type mismatch" in {
    val code =
      """
        |def main(a: Number, b: TimeSeries): Number = a + b
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "catch binary operation return type" in {
    val code =
      """
        |def main(a: TimeSeries, b: TimeSeries): Number = a + b
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "type check minus operator" in {
    val code =
      """
        |def main(a: Number): Number = -a*-12
      """.stripMargin
    val ast = Compiler.compile(code, Seq(MathModule.header))
    ast.isRight shouldBe true
  }

  it should "type check time series and value arithmetic operation" in {
    val code =
      """
        |def main(ts: TimeSeries): TimeSeries = (ts*12)+3
      """.stripMargin
    val ast = Compiler.compile(code, Seq(MathModule.header))
    ast.isRight shouldBe true
  }

//  it should "type check time series in if" in {
//    val code =
//      """
//        |def main(a: TimeSeries, b: TimeSeries): TimeSeries = if a > 12 || b < 3 then 1 else 0
//      """.stripMargin
//    val ast = Compiler.compile(code, Seq(MathModule.header))
//    ast.isRight shouldBe true
//  }

  it should "type check minus on wrong type" in {
    val code =
      """
        |def main(a: Bool): Number = -a
      """.stripMargin
    val ast = Compiler.compile(code, Seq(MathModule.header))
    ast.isRight shouldBe false
  }

  it should "catch bool operation types" in {
    val code =
      """
        |def main(a: Bool, b: Number): Bool = !a && b
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "check if-then-else - if expression" in {
    val code =
      """
        |def main(a: Number, b: Number): Number = if a then b else 100
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "check if-then-else - else expression" in {
    val code =
      """
        |def main(a: Bool, b: Number): Number = if a then b else ''
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe false
  }

  it should "check multiparam external function" in {
    val code =
      """
        |external def series(id: Number, from: Number, to: Number): TimeSeries
        |
        |def main(id: Number): TimeSeries = series(id, 2015, 2016)
      """.stripMargin
    val ast = Compiler.compile(code, Seq())
    ast.isRight shouldBe true
  }

  it should "parse higher order functions" in {
    val code =
      """
        |external def map2(xs: TimeSeries, f: Number => Number): TimeSeries
        |
        |def f(a: Number): Number = 2*a
        |
        |def main(xs: TimeSeries): TimeSeries = map2(xs, f)
      """.stripMargin
    val result = Compiler.make(code)
    result.isRight shouldBe true
  }

  it should "catch HoF type mismatch" in {
    val code =
      """
        |external def map2(xs: TimeSeries, f: Number => Number): TimeSeries
        |
        |def f(a: Number): String = 2*a
        |
        |def main(xs: TimeSeries): TimeSeries = map2(xs, f)
      """.stripMargin
    val result = Compiler.make(code)
    result.isRight shouldBe false
  }

}