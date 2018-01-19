package carldata.sf.core

import carldata.series.TimeSeries
import carldata.sf.{Compiler, Interpreter}
import org.scalatest._

class MathTest extends FlatSpec with Matchers {

  "MathModule" should "calculate ceil" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = ceil(xs)
      """.stripMargin
    val xs = TimeSeries.fromTimestamps(Seq((1L, 1.2f), (2L, 1.9f), (3L, 5.1f)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 2.0f), (2L, 2.0f), (3L, 6.0f)))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(xs))
    }
    result shouldBe Right(expected)
  }

  it should "calculate log" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = log10(xs)
      """.stripMargin
    val xs = TimeSeries.fromTimestamps(Seq((1L, 1f), (2L, 10f), (3L, 100f)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 0.0f), (2L, 1.0f), (3L, 2.0f)))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(xs))
    }
    result shouldBe Right(expected)
  }

  it should "calculate pow" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = pow(xs, 2)
      """.stripMargin
    val xs = TimeSeries.fromTimestamps(Seq((1L, 1f), (2L, 2f), (3L, 3f)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 1f), (2L, 4f), (3L, 9f)))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(xs))
    }
    result shouldBe Right(expected)
  }

  it should "calculate acos" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = asin(xs)
      """.stripMargin
    val xs = TimeSeries.fromTimestamps(Seq((1L, 0f), (2L, 0.5f), (3L, 1f)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 0f), (2L, 0.5235988f), (3L, 1.5707964f)))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(xs))
    }
    result shouldBe Right(expected)
  }
}
