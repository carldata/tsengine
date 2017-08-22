package carldata.sf.core

import carldata.series.TimeSeries
import carldata.sf.core.TimeSeriesModule.TimeSeriesValue
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
    val tsv = TimeSeriesValue(TimeSeries.empty)
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(tsv))
    }
    val ts = result.right.get.asInstanceOf[TimeSeriesValue].ts
    ts.length shouldBe 0
  }

  it should "map over values" in {
    val code =
      """
        |def f(a: Number): Number = a+2
        |
        |def main(xs: TimeSeries): TimeSeries = map(xs, f)
      """.stripMargin
    val tsv = TimeSeriesValue(TimeSeries.fromTimestamps(Seq((1, 1f), (2, 1f), (3, 1f))))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(tsv))
    }
    val ts = result.right.get.asInstanceOf[TimeSeriesValue].ts
    ts.sum shouldBe 9
  }


}