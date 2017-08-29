package carldata.sf

import carldata.series.TimeSeries
import org.scalameter._


/**
  * Sandbox for testing implementation.
  */
object BenchmarkApp {

  val mapScript: String =
    """
      |def f(a: Number): Number = a+2
      |
      |def main(xs: TimeSeries): TimeSeries = map(xs, f)
    """.stripMargin
  val deltaTimeScript = "def main(xs: TimeSeries): TimeSeries = delta_time(xs)"

  private val intFormatter = java.text.NumberFormat.getIntegerInstance

  def main(args: Array[String]): Unit = {
    val size1M = 1000000

    println("\n1. Measure map")
    measureScript(size1M, mapScript)
    println("\n1. Measure delta_time")
    measureScript(size1M, deltaTimeScript)
    println()
  }

  def measureScript(size: Int, code: String): Unit = {
    val xs = 1.to(size).toVector
    val ts = TimeSeries.fromTimestamps(xs.map(x => (x.toLong*3000, x.toFloat)))
    val exec = Compiler.make(code).map { ast => Interpreter(ast) }.right.get

    val time: Quantity[Double] = withWarmer(new Warmer.Default).measure {
      exec.run("main", Seq(ts))
    }

    val sizeFormatted = intFormatter.format(size)
    println(s"$sizeFormatted points: $time.")
  }

}
