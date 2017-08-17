package carldata.sf

import carldata.sf.Runtime.NumberValue
import carldata.sf.core.Math
import org.scalameter._

/**
  * Measure library performance
  */
object Benchmarks{

  def runBenchmarks(): Unit = {
    val code =
      """
        |def main(a: Number, b: Number): Number = sin(a) + cos(b)
      """.stripMargin
    val exec = Compiler.compile(code, Seq(Math.header))
      .map { ast => new Interpreter(ast, Seq(new Math())) }
      .right.get

    val time: Quantity[Double] = withWarmer(new Warmer.Default).measure {
      exec.run("main", Seq(NumberValue(1), NumberValue(3)))
    }

    println(s"Benchmark simple script: $time")
  }
}