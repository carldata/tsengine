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
    val ts = TimeSeriesValue(TimeSeries.empty)
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts))
    }
    result.right.get.isInstanceOf[TimeSeriesValue] shouldBe true
  }
}