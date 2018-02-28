package carldata.sf.core

import carldata.sf.{Compiler, Interpreter}
import org.scalatest._

class HydrologyTest extends FlatSpec with Matchers {
  "HydrologyCore" should "calculate manning velocity" in {
    val epsilon = 0.01
    val code =
      """
        |def main(n: Number, d: Number, s: Number, u: String): Number = manning_velocity(n, d, s, u)
      """.stripMargin
    val expected = 1.23
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(0.013, 1, 0.02, "in"))
    }
    Math.abs(result.right.get.asInstanceOf[Double] - expected) < epsilon shouldEqual true

  }

  it should "calculate manning flow" in {
    val epsilon = 0.01
    val code =
      """
        |def main(n: Number, d: Number, s: Number, u: String): Number = manning_flow(n, d, s, u)
      """.stripMargin
    val expected = 0.964
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(0.013, 1, 0.02, "in"))
    }
    Math.abs(result.right.get.asInstanceOf[Double] - expected) < epsilon shouldEqual true

  }
}
