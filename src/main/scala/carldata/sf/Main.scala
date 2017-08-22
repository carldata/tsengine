package carldata.sf

import carldata.series.TimeSeries
import carldata.sf.core.TimeSeriesModule.TimeSeriesValue

/**
  * Sandbox for testing implementation.
  */
object Main {

  def main(args: Array[String]): Unit = {
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
    println(result)
  }
}
