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
    val xs = TimeSeries.fromTimestamps(Seq((1L, 1.2), (2L, 1.9), (3L, 5.1)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 2.0), (2L, 2.0), (3L, 6.0)))
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
    val xs = TimeSeries.fromTimestamps(Seq((1L, 1.0), (2L, 10.0), (3L, 100.0)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 0.0), (2L, 1.0), (3L, 2.0)))
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
    val xs = TimeSeries.fromTimestamps(Seq((1L, 1.0), (2L, 2.0), (3L, 3.0)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 1), (2L, 4), (3L, 9)))
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
    val xs = TimeSeries.fromTimestamps(Seq((1L, 0.0), (2L, 0.5), (3L, 1.0)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 0.0), (2L, 0.5235987755982989), (3L, 1.5707963267948966)))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(xs))
    }
    result shouldBe Right(expected)
  }
}
