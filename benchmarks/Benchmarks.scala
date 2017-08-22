package carldata.sf

import carldata.sf.Runtime.NumberValue
import carldata.sf.core.MathModule
import org.scalameter._

/**
  * Measure library performance
  */
object Benchmarks{

  def main(args: Array[String]): Unit = {
    val code =
      """
        |def main(a: Number, b: Number): Number = sin(a) + cos(b)
      """.stripMargin
    val exec = Compiler.compile(code, Seq(MathModule.header))
      .map { ast => new Interpreter(ast, Seq(new MathModule())) }
      .right.get

    val time: Quantity[Double] = withWarmer(new Warmer.Default).measure {
      exec.run("main", Seq(NumberValue(1), NumberValue(3)))
    }

    println(s"Benchmark simple script: $time")
  }
}