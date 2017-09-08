package carldata.sf.core

import carldata.sf.{Compiler, Interpreter}
import org.scalatest._

class HydrologyTest extends FlatSpec with Matchers {
  "HydrologyCore" should "calculate manning velocity" in {
    val epsilon = 0.01
    val code =
      """
        |def main(n: Number, d: Float, s: Float, u: String): Number = manning_velocity(n, d, s, u)
      """.stripMargin
    val expected = 1.23f
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(0.013f, 0.0833f, 0.02f, "in"))
    }
    Math.abs(result.right.get.asInstanceOf[Float] - expected) < epsilon shouldEqual true

  }
}
