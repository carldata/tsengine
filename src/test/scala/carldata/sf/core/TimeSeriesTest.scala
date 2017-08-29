package carldata.sf.core

import java.time.LocalDateTime

import carldata.series.TimeSeries
import carldata.sf.{Compiler, Interpreter}
import org.scalatest._


/**
  * Tests for Time Series core
  */
class TimeSeriesTest extends FlatSpec with Matchers {

  "TimeSeriesCore" should "run test function" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = xs
      """.stripMargin
    val tsv: TimeSeries[Float] = TimeSeries.empty
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(tsv))
    }
    val ts = result.right.get.asInstanceOf[TimeSeries[Float]]
    ts.length shouldBe 0
  }

  it should "map over values" in {
    val code =
      """
        |def f(a: Number): Number = a+2
        |
        |def main(xs: TimeSeries): TimeSeries = map(xs, f)
      """.stripMargin
    val tsv = TimeSeries.fromTimestamps(Seq((1L, 1f), (2L, 1f), (3L, 1f)))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(tsv))
    }
    val ts = result.right.get.asInstanceOf[TimeSeries[Float]]
    ts.sum shouldBe 9
  }

  it should "differentiate values" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = differentiate(xs)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusMinutes(15), now.plusMinutes(30), now.plusMinutes(45), now.plusMinutes(60), now.plusMinutes(75))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 3f, 2f, 6f))
    val expected = TimeSeries(idx.tail, Vector(1f, 1f, 0f, -1f, 4f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts))
    }
    result shouldBe Right(expected)
  }


}