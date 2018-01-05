package carldata.sf.core

import carldata.series.TimeSeries
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
  "DBTest" should "get value from lookup table for existing index" in {
    val code =
      """
        |def main(id: String, xs: TimeSeries): TimeSeries = lookup(id, xs)
      """.stripMargin
    val xs = TimeSeries.fromTimestamps(Seq((1L, 2f), (2L, 3f), (3L, 0f), (4L, 20f)))
    val expected = TimeSeries.fromTimestamps(Seq((1L, 150f), (2L, 200f), (3L, 100f), (4L, 500f)))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec, test_db).run("main", Seq("velocity", xs))
    }
    result.right.get shouldEqual expected

  }

}
