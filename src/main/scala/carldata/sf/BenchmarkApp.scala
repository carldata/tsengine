package carldata.sf

import carldata.series.TimeSeries
import carldata.sf.core.DBImplementation
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

  val lookupScript: String =
    """
      |def f(a: Number): Number = lookup("test", a)
      |
      |def main(xs: TimeSeries): TimeSeries = map(xs, f)
    """.stripMargin
  private val intFormatter = java.text.NumberFormat.getIntegerInstance

  def main(args: Array[String]): Unit = {
    val size1M = 1000000
    val size100K = 100000

    println("\n1. Measure map")
    measureScript(size1M, mapScript)
    println("\n2. Measure delta_time")
    measureScript(size1M, deltaTimeScript)
    println("\n3. Measure 100 items in lookup table")
    measureScript(size100K, lookupScript)
    println()
  }

  def measureScript(size: Int, code: String): Unit = {
    val xs = 1.to(size).toVector
    val testDB: TestDB = new TestDB
    val ts = TimeSeries.fromTimestamps(xs.map(x => (x.toLong * 3000, x.toFloat)))
    val exec = Compiler.make(code).map { ast => Interpreter(ast, testDB) }.right.get

    val time: Quantity[Double] = withWarmer(new Warmer.Default).measure {
      exec.run("main", Seq(ts))
    }

    val sizeFormatted = intFormatter.format(size)
    println(s"$sizeFormatted points: $time.")
  }

}

class TestDB extends DBImplementation {
  def f(x: Float): (String, Float, Float) = ("test", x, 2 * x)

  val lookup_table: Seq[(String, Float, Float)] = for (x <- 1 to 100) yield f(x)

  def getTable(id: String): IndexedSeq[(Float, Float)] = {
    lookup_table.filter(p => p._1.equals(id)).map(x => (x._2, x._3)).toIndexedSeq
  }
}