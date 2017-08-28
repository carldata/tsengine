package carldata.sf

import carldata.series.TimeSeries
import org.scalameter._


/**
  * Sandbox for testing implementation.
  */
object Main {

  def main(args: Array[String]): Unit = {
    measureMap()
  }

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
      exec.run("main", Seq(ts))
    }

    println(s"map over 1M points (native: $timeNative, script: $timeScript")
  }

  def sandbox(): Unit = {
    val code =
      """
        |def main(a: Number, b: Number): Number = a+b
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(2, 2))
    }
    println(result)
  }

}
