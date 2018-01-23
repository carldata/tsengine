package carldata.sf.core

import java.time.{Duration, Instant, LocalDateTime, ZoneOffset}
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

import carldata.sf.compiler.Parser
import carldata.sf.{Compiler, Interpreter}
import org.scalacheck.Prop.Exception
import org.scalatest._

class DateTimeTest extends FlatSpec with Matchers {
  val libs = Seq(DateTimeModule.header)
  val dt = new DateTimeModule()

  "Datetime" should "convert date from string" in {
    val code =
      """
        |def main(s: String): DateTime = date(s)
      """.stripMargin

    val params = Seq("2017-08-21T10:30:30")
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    toUTC(result.right.get) shouldBe "2017-08-21T10:30:30"
  }

  it should "adjust date" in {
    val code =
      """
        |def main(dt: DateTime,y: Number, m: Number, d:Number): DateTime = adjust_date(dt,y,m,d)
      """.stripMargin

    val params = Seq(dt.$from_date(2017, 8, 21), 2016, 1, 1)
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    result.right.get.toString shouldBe dt.$from_date(2016, 1, 1).toString
  }

  it should "adjust time" in {
    val code =
      """
        |def main(dt: DateTime,y: Number, m: Number, d:Number): DateTime = adjust_time(dt,y,m,d)
      """.stripMargin

    val params = Seq(dt.$from_date(2017, 8, 21), 10, 30, 30)
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    toUTC(result.right.get) shouldBe "2017-08-21T10:30:30"
  }

  it should "get day of week" in {
    val code =
      """
        |def main(dt: DateTime): Number = day_of_week(dt)
      """.stripMargin

    val result1 = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(dt.$from_date(2017, 8, 21)))
    }
    result1.right.get shouldBe 1

    val result2 = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(dt.$from_date(2017, 8, 23)))
    }
    result2.right.get shouldBe 3

    val result3 = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(dt.$from_date(2017, 8, 26)))
    }
    result3.right.get shouldBe 6

  }

  it should "floor hours" in {
    val code =
      """
        |def main(dt: DateTime): DateTime = floor_hours(dt)
      """.stripMargin

    val params = Seq(dt.$from_datetime(2017, 8, 21, 11, 31, 36, 0))
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    toUTC(result.right.get) shouldBe "2017-08-21T00:00:00"
  }

  it should "floor minutes" in {
    val code =
      """
        |def main(dt: DateTime): DateTime = floor_minutes(dt)
      """.stripMargin

    val params = Seq(dt.$from_datetime(2017, 8, 21, 11, 31, 36, 0))
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    toUTC(result.right.get) shouldBe "2017-08-21T11:00:00"
  }

  it should "floor seconds" in {
    val code =
      """
        |def main(dt: DateTime): DateTime = floor_seconds(dt)
      """.stripMargin

    val params = Seq(dt.$from_datetime(2017, 8, 21, 11, 31, 36, 0))
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", params)
    }
    toUTC(result.right.get) shouldBe "2017-08-21T11:31:00"
  }

  it should "get minutes" in {
    val code =
      """
        |def main(n: Number): Duration = minutes(n)
      """.stripMargin

    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(5f))
    }
    result.right.get.asInstanceOf[Duration].toString shouldBe "PT5M"
  }

  it should "get hours" in {
    val code =
      """
        |def main(n: Number): Duration = hours(n)
      """.stripMargin

    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(6f))
    }
    result.right.get.asInstanceOf[Duration].toString shouldBe "PT6H"
  }

  it should "get days" in {
    val code =
      """
        |def main(n: Number): Duration = days(n)
      """.stripMargin

    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(2f))
    }
    result.right.get.asInstanceOf[Duration].toString shouldBe "PT48H"
  }

  it should "get weeks" in {
    val code =
      """
        |def main(n: Number): Duration = weeks(n)
      """.stripMargin

    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(2f))
    }
    result.right.get.asInstanceOf[Duration].toString shouldBe "PT336H"
  }

  it should "get months" in {
    val code =
      """
        |def main(n: Number): Duration = months(n)
      """.stripMargin

    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(2f))
    }
    result.right.get.asInstanceOf[Duration].toDays shouldBe 60
  }

  it should "get years" in {
    val code =
      """
        |def main(n: Number): Duration = years(n)
      """.stripMargin

    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq(1f))
    }
    result.right.get.asInstanceOf[Duration].toDays shouldBe 365
  }

  it should "convert calendar expression" in {
    val code =
      """
        |def main(s: String): DateTime => DateTime = dt_convert(s)
      """.stripMargin
    val now = LocalDateTime.of(2017, 10, 13, 11, 7).toInstant(ZoneOffset.UTC)
    val expected = LocalDateTime.of(2017, 9, 14, 23, 59).toInstant(ZoneOffset.UTC)
    val result = Compiler.compile(code, libs).flatMap { exec =>
      Interpreter(exec).run("main", Seq("* * 14 * *"))
    }
    result.right.get.asInstanceOf[Instant => Instant](now) shouldBe expected
  }

  "With compiler" should "find longest duration" in {
    val code =
      """
        |def f(a: Number, b: Number, c: Number): Number = a+b+c
        |
        |def main(xs: TimeSeries, ys: TimeSeries, zs: TimeSeries): TimeSeries = join_with3(groupby_sum(xs, minutes(3)), groupby_sum(xs, days(11)), groupby_sum(xs, hours(4)), f)
      """.stripMargin

    //val params = Seq("2017-08-21T10:30:30")
    val result = Parser.parse(code)
    Compiler.getDuration(result.right.get).toString shouldBe Duration.ofDays(11).toString

  }

  def toUTC(v: Any): String = v match {
    case i: Instant => LocalDateTime.ofInstant(i, ZoneOffset.UTC).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    case _ => throw new ClassCastException()
  }

}
