package carldata.sf

import carldata.series.TimeSeries
import carldata.sf.core.TimeSeriesModule.TimeSeriesValue
import org.scalatest.{FlatSpec, Matchers}

import org.scalameter._

/**
  * Measure library performance
  */
class Benchmarks extends FlatSpec with Matchers {

  "Benchmarks" should "measure all" in {
    measureMap()
  }

  /**
    * Test performance of HoF on timeSeries with 1M points
    */
  def measureMap(): Unit = {
    val code =
      """
        |def f(a: Number): Number = a+2
        |
        |def main(xs: TimeSeries): TimeSeries = map(xs, f)
      """.stripMargin
    val xs = 1.to(1000000).toVector
    val ts = TimeSeries.fromTimestamps(xs.map(x => (x.toLong, x.toFloat)))
    val exec = Compiler.make(code)
      .map { ast => Interpreter(ast) }
      .right.get

    val timeNative: Quantity[Double] = withWarmer(new Warmer.Default).measure {
      ts.map(x => x._2 + 2)
    }

    val timeScript: Quantity[Double] = withWarmer(new Warmer.Default).measure {
      exec.run("main", Seq(TimeSeriesValue(ts)))
    }

    println(s"map over 1M points (native: $timeNative, script: $timeScript")
  }
}