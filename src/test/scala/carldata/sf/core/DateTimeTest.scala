package carldata.sf

import carldata.sf.Runtime.{NumberValue, StringValue}
import carldata.sf.core.DateTime
import org.scalatest._

class DateTimeTest extends FlatSpec with Matchers {
  val libs = Seq(DateTime.header)
  val dt = new DateTime()

  "Datetime" should "convert date from string" in {
    val code =
      """
        |def main(s: String): DateTime = date(s)
      """.stripMargin

    val params = Seq(new StringValue("2017-08-21T10:30:30"))
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    result.right.get.toString shouldEqual dt.$from_datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(10), NumberValue(30), NumberValue(30), NumberValue(0)).toString
  }

  it should "adjust date" in {
    val code =
      """
        |def main(dt: DateTime,y: Number, m: Number, d:Number): DateTime = adjust_date(dt,y,m,d)
      """.stripMargin

    val params = Seq(dt.$from_date(NumberValue(2017), NumberValue(8), NumberValue(21)), NumberValue(2016), NumberValue(1), NumberValue(1))
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    result.right.get.toString shouldBe dt.$from_date(NumberValue(2016), NumberValue(1), NumberValue(1)).toString
  }

  it should "adjust time" in {
    val code =
      """
        |def main(dt: DateTime,y: Number, m: Number, d:Number): DateTime = adjust_time(dt,y,m,d)
      """.stripMargin

    val params = Seq(dt.$from_date(NumberValue(2017), NumberValue(8), NumberValue(21)), NumberValue(10), NumberValue(30), NumberValue(30))
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    result.right.get.toString shouldBe dt.$from_datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(10), NumberValue(30), NumberValue(30), NumberValue(0)).toString
  }

  it should "get day of week" in {
    val code =
      """
        |def main(dt: DateTime): Number = day_of_week(dt)
      """.stripMargin

    val result1 = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(dt.$from_date(NumberValue(2017), NumberValue(8), NumberValue(21))))
    }
    result1.right.get shouldBe NumberValue(1)

    val result2 = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(dt.$from_date(NumberValue(2017), NumberValue(8), NumberValue(23))))
    }
    result2.right.get shouldBe NumberValue(3)

    val result3 = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(dt.$from_date(NumberValue(2017), NumberValue(8), NumberValue(26))))
    }
    result3.right.get shouldBe NumberValue(6)

  }

  it should "floor hours" in {
    val code =
      """
        |def main(dt: DateTime): DateTime = floor_hours(dt)
      """.stripMargin

    val params = Seq(dt.$from_datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(11), NumberValue(31), NumberValue(36), NumberValue(0)))
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    result.right.get.toString shouldBe dt.$from_datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(0), NumberValue(31), NumberValue(36), NumberValue(0)).toString
  }

  it should "floor minutes" in {
    val code =
      """
        |def main(dt: DateTime): DateTime = floor_minutes(dt)
      """.stripMargin

    val params = Seq(dt.$from_datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(11), NumberValue(31), NumberValue(36), NumberValue(0)))
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    result.right.get.toString shouldBe dt.$from_datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(11), NumberValue(0), NumberValue(36), NumberValue(0)).toString
  }

  it should "floor seconds" in {
    val code =
      """
        |def main(dt: DateTime): DateTime = floor_seconds(dt)
      """.stripMargin

    val params = Seq(dt.$from_datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(11), NumberValue(31), NumberValue(36), NumberValue(0)))
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    result.right.get.toString shouldBe dt.$from_datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(11), NumberValue(31), NumberValue(0), NumberValue(0)).toString
  }

}
