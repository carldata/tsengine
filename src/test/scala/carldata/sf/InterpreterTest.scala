package carldata.sf

import carldata.series.TimeSeries
import org.scalatest._


/**
  * Mostly tests for Semantic Analysis module
  */
class InterpreterTest extends FlatSpec with Matchers {

  "Interpreter" should "not run program with wrong number of parameters" in {
    val code =
      """
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq())
    }
    result.isRight shouldBe false
  }

  it should "run valid program" in {
    val code =
      """
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(1, 2))
    }
    result.isRight shouldBe true
  }

    it should "return value of the parameter" in {
    val code =
      """
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(1, 2))
    }
    result shouldBe Right(1)
  }

  it should "calculate a+b*2" in {
    val code =
      """
        |def main(a: Number, b: Number): Number = a+b*2
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(1, 3))
    }
    result shouldBe Right(7)
  }

  it should "calculate 2*b+a" in {
    val code =
      """
        |def main(a: Number, b: Number): Number = 2*b+a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(1, 3))
    }
    result shouldBe Right(7)
  }

  it should "calculate 2*(b+a)" in {
    val code =
      """
        |def main(a: Number, b: Number): Number = 2*(b+a)
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(1, 3))
    }
    result shouldBe Right(8)
  }

  it should "calculate -a*2" in {
    val code =
      """
        |def main(a: Number): Number = -a*2
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(3))
    }
    result shouldBe Right(-6)
  }

  it should "check let-in" in {
    val code =
      """
        |def main(a: Number): Number =
        | let
        |   x = 3
        |   y = x + x
        | in
        |   a*y
      """.stripMargin
    val result = Compiler.make(code).flatMap { ast =>
      Interpreter(ast).run("main", Seq(5))
    }
    result shouldBe Right(30)
  }

  it should "show runtime error message" in {
    val code =
      """
        |external def test(a: Number): Number
        |
        |def main(a: Number): Number = test(a)
      """.stripMargin
    val result = Compiler.make(code).flatMap { ast =>
      Interpreter(ast).run("main", Seq(12))
    }
    result.left.getOrElse("").substring(0, 7) shouldBe "Runtime"
  }

  it should "parse higher order functions with multiple params" in {
    val code =
      """
        |def test(a: Number, b: Number, f: Number, Number => Number): Number = f(a, b)
        |
        |def f(a: Number, b: Number): Number = a+b
        |
        |def main(a: Number, b: Number): Number = test(a, b, f)
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(13, 25))
    }
    result shouldBe Right(38)
  }

  it should "parse NULLs" in {
    val code =
      """
        |def main(a: Number): Number = a + NULL
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(13))
    }
    result.right.get.asInstanceOf[Double].isNaN shouldBe true
  }

  it should "add number to time series" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = xs + 1
      """.stripMargin
    val ts = TimeSeries.fromTimestamps(Seq((1L, 1.0), (2L, 2.0), (3L, 3.0)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 2.0), (2L, 3.0), (3L, 4.0)))
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(ts))
    }
    result.right.get shouldBe expected
  }

  it should "multiply 2 series" in {
    val code =
      """
        |def main(xs: TimeSeries, ys: TimeSeries): TimeSeries = xs * ys
      """.stripMargin
    val xs = TimeSeries.fromTimestamps(Seq((1L, 1.0), (2L, 2.0), (3L, 3.0)))
    val ys = TimeSeries.fromTimestamps(Seq((1L, 1.0), (3L, 3.0), (4L, 4.0)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 1.0), (3L, 9.0)))
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(xs, ys))
    }
    result.right.get shouldBe expected
  }

  it should "allow time series in if" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = if xs > 2 && xs < 5 then 1 else 3
      """.stripMargin
    val xs = TimeSeries.fromTimestamps(Seq((1L, 1.0), (2L, 2.0), (3L, 3.0), (4L, 4.0), (5L, 5.0)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 3.0), (2L, 3.0), (3L, 1.0), (4L, 1.0), (5L, 3.0)))
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(xs))
    }
    result.right.get shouldBe expected
  }
}