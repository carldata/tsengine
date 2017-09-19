package carldata.sf.core

import carldata.sf.{Compiler, Interpreter}
import org.scalatest._

class TestDB extends DBImplementation {
  val lookup_table = Seq(("precip", 0f, 100f)
    , ("velocity", 1f, 100f)
    , ("velocity", 3f, 200f)
    , ("velocity", 5f, 400f)
    , ("velocity", 10f, 500f)
    , ("volume", 1f, 100f)
    , ("volume", 40f, 1000f))

  def getTable(id: String): IndexedSeq[(Float, Float)] = {
    lookup_table.filter(p => p._1.equals(id)).map(x => (x._2, x._3)).toIndexedSeq
  }
}

class DBTest extends FlatSpec with Matchers {
  val test_db: TestDB = new TestDB
  "DBCore" should "get value from lookup table for existing index" in {
    val code =
      """
        |def main(id: String, x: Number): Number = lookup(id, x)
      """.stripMargin
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec, test_db).run("main", Seq("velocity", 3f))
    }
    result.right.get.asInstanceOf[Float] shouldEqual 200f

  }

  it should "interpolate value from lookup table" in {
    val code =
      """
        |def main(id: String, x: Number): Number = lookup(id, x)
      """.stripMargin
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec, test_db).run("main", Seq("velocity", 2f))
    }
    result.right.get.asInstanceOf[Float] shouldEqual 150f

  }

  it should "get value outside lookup table: less than" in {
    val code =
      """
        |def main(id: String, x: Number): Number = lookup(id, x)
      """.stripMargin
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec, test_db).run("main", Seq("velocity", 0f))
    }
    result.right.get.asInstanceOf[Float] shouldEqual 100f

  }

  it should "get value outside lookup table: greater than" in {
    val code =
      """
        |def main(id: String, x: Number): Number = lookup(id, x)
      """.stripMargin
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec, test_db).run("main", Seq("velocity", 20f))
    }
    result.right.get.asInstanceOf[Float] shouldEqual 500f

  }

}
