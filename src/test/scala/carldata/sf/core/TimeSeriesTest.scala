package carldata.sf.core

import java.time.LocalDateTime

import carldata.series.TimeSeries
import carldata.sf.{Compiler, Interpreter}
import org.scalatest._


/**
  * Tests for Time Series core
  */
class TimeSeriesTest extends FlatSpec with Matchers {

  "TimeSeriesCore" should "run test function" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = xs
      """.stripMargin
    val tsv: TimeSeries[Float] = TimeSeries.empty
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(tsv))
    }
    val ts = result.right.get.asInstanceOf[TimeSeries[Float]]
    ts.length shouldBe 0
  }

  it should "map over values" in {
    val code =
      """
        |def f(a: Number): Number = a+2
        |
        |def main(xs: TimeSeries): TimeSeries = map(xs, f)
      """.stripMargin
    val tsv = TimeSeries.fromTimestamps(Seq((1L, 1f), (2L, 1f), (3L, 1f)))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(tsv))
    }
    val ts = result.right.get.asInstanceOf[TimeSeries[Float]]
    ts.values.sum shouldBe 9
  }

  it should "differentiate values" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = differentiate(xs)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusMinutes(15), now.plusMinutes(30), now.plusMinutes(45), now.plusMinutes(60), now.plusMinutes(75))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 3f, 2f, 6f))
    val expected = TimeSeries(idx.tail, Vector(1f, 1f, 0f, -1f, 4f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts))
    }
    result shouldBe Right(expected)
  }

  it should "differentiate time" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = delta_time(xs)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusSeconds(15), now.plusSeconds(30), now.plusSeconds(34), now.plusSeconds(56), now.plusSeconds(57))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 3f, 2f, 6f))
    val expected = TimeSeries(idx.tail, Vector(15f, 15f, 4f, 22f, 1f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts))
    }
    result shouldBe Right(expected)
  }

  it should "find average in fixed-interval series" in {
    val code =
      """
        |def main(xs: TimeSeries, d: String): TimeSeries = groupby_avg(xs, dt_convert(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:01:00")
    val idx = Vector(now, now.plusSeconds(15), now.plusSeconds(30), now.plusSeconds(45), now.plusSeconds(65), now.plusSeconds(180))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 3f, 2f, 6f))
    val expected = TimeSeries(Vector(now, now.plusMinutes(1), now.plusMinutes(3)), Vector(2.25f, 2f, 6f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, "*/1 * * * *"))
    }
    result shouldBe Right(expected)
  }

  it should "find maximum in fixed-interval series" in {
    val code =
      """
        |def main(xs: TimeSeries, d: String): TimeSeries = groupby_max(xs, dt_convert(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:01:00")
    val idx = Vector(now, now.plusSeconds(15), now.plusSeconds(30), now.plusSeconds(45), now.plusSeconds(65), now.plusSeconds(180))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 3f, 2f, 6f))
    val expected = TimeSeries(Vector(now, now.plusMinutes(1), now.plusMinutes(3)), Vector(3f, 2f, 6f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, "*/1 * * * *"))
    }
    result shouldBe Right(expected)
  }

  it should "find minimum in fixed-interval series" in {
    val code =
      """
        |def main(xs: TimeSeries, d: String): TimeSeries = groupby_min(xs, dt_convert(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:01:00")
    val idx = Vector(now, now.plusSeconds(15), now.plusSeconds(30), now.plusSeconds(45), now.plusSeconds(65), now.plusSeconds(80))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 3f, 2f, 6f))
    val expected = TimeSeries(Vector(now, now.plusMinutes(1)), Vector(1f, 2f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, "*/1 * * * *"))
    }
    result shouldBe Right(expected)
  }

  it should "find median in fixed-interval series" in {
    val code =
      """
        |def main(xs: TimeSeries, d: String): TimeSeries = groupby_median(xs, dt_convert(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:01:00")
    val idx = Vector(now, now.plusSeconds(15), now.plusSeconds(30), now.plusSeconds(45), now.plusSeconds(65), now.plusSeconds(80), now.plusSeconds(85))
    val ts = TimeSeries(idx, Vector(3f, 2f, 1f, 3f, 2f, 6f, 7f))
    val expected = TimeSeries(Vector(now, now.plusMinutes(1)), Vector(2.5f, 6f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, "*/1 * * * *"))
    }
    result shouldBe Right(expected)
  }

  it should "find sum in fixed-interval series" in {
    val code =
      """
        |def main(xs: TimeSeries, d: String): TimeSeries = groupby_sum(xs, dt_convert(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:01:00")
    val idx = Vector(now, now.plusSeconds(15), now.plusSeconds(30), now.plusSeconds(45), now.plusSeconds(65), now.plusSeconds(80))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 3f, 2f, 6f))
    val expected = TimeSeries(Vector(now, now.plusMinutes(1)), Vector(9f, 8f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, "*/1 * * * *"))
    }
    result shouldBe Right(expected)
  }

  it should "shift time forward" in {
    val code =
      """
        |def main(xs: TimeSeries, d: Number, v: Number): TimeSeries = shift(xs, hours(d), v)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusMinutes(15), now.plusMinutes(30), now.plusMinutes(45),
      now.plusMinutes(60), now.plusMinutes(70))
    val idx2 = Vector(now.plusMinutes(60), now.plusMinutes(75), now.plusMinutes(90), now.plusMinutes(105),
      now.plusMinutes(120), now.plusMinutes(130))
    val vs = Vector(1f, 2f, 3f, 4f, 5f, 6f)
    val ts = TimeSeries(idx, vs)
    val expected = TimeSeries(idx2, vs)
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 1f, 1))
    }
    result shouldBe Right(expected)
  }

  it should "shift time backwards" in {
    val code =
      """
        |def main(xs: TimeSeries, d: Number, v: Number): TimeSeries = shift(xs, hours(d), v)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusMinutes(15), now.plusMinutes(30), now.plusMinutes(45),
      now.plusMinutes(60), now.plusMinutes(70))
    val idx2 = Vector(now.minusMinutes(60), now.minusMinutes(45), now.minusMinutes(30), now.minusMinutes(15),
      now, now.plusMinutes(10))
    val vs = Vector(1f, 2f, 3f, 4f, 5f, 6f)
    val ts = TimeSeries(idx, vs)
    val expected = TimeSeries(idx2, vs)
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 1f, -1))
    }
    result shouldBe Right(expected)
  }

  it should "slice timeseries" in {
    val code =
      """
        |def main(xs: TimeSeries, sd: DateTime, ed: DateTime): TimeSeries = slice(xs, sd, ed)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val startDate = now.plusMinutes(15)
    val endDate = now.plusMinutes(60)
    val idx = Vector(now, now.plusMinutes(15), now.plusMinutes(30), now.plusMinutes(45),
      now.plusMinutes(59), now.plusMinutes(60))
    val idx2 = Vector(now.plusMinutes(15), now.plusMinutes(30), now.plusMinutes(45), now.plusMinutes(59))
    val vs = Vector(1f, 2f, 3f, 4f, 5f, 6f)
    val vs2 = Vector(2f, 3f, 4f, 5f)
    val ts = TimeSeries(idx, vs)
    val expected = TimeSeries(idx2, vs2)
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, startDate, endDate))
    }
    result shouldBe Right(expected)
  }

  it should "find cumulative" in {
    val code =
      """
        |def main(xs: TimeSeries, f: DateTime => DateTime): TimeSeries = running_total(xs, f)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusMinutes(15), now.plusMinutes(30), now.plusMinutes(45),
      now.plusMinutes(60), now.plusMinutes(70))
    def f(d: LocalDateTime):LocalDateTime = d.withMinute(0)
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 4f, 5f, 6f))
    val expected = TimeSeries(idx, Vector(1f, 3f, 6f, 10f, 5f, 11f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts,f(_)))
    }
    result shouldBe Right(expected)
  }

  it should "calculate rolling avg" in {
    val code =
      """
        |def main(xs: TimeSeries, d: Number): TimeSeries = rolling_avg(xs, minutes(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusSeconds(15), now.plusSeconds(30), now.plusSeconds(45), now.plusSeconds(65), now.plusSeconds(80))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 3f, 2f, 6f))
    val expected = TimeSeries(idx, Vector(1f, 1.5f, 2.0f, 2.25f, 2.5f, 3.5f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 1f))
    }
    result shouldBe Right(expected)
  }

  it should "calculate rolling sum" in {
    val code =
      """
        |def main(xs: TimeSeries, d: Number): TimeSeries = rolling_sum(xs, minutes(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusSeconds(15), now.plusSeconds(30), now.plusSeconds(45), now.plusSeconds(65), now.plusSeconds(80))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 3f, 2f, 6f))
    val expected = TimeSeries(idx, Vector(1f, 3f, 6f, 9f, 10f, 14f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 1f))
    }
    result shouldBe Right(expected)
  }

  it should "step index" in {
    val code =
      """
        |def main(xs: TimeSeries, d: Number): TimeSeries = step(xs, minutes(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusHours(1), now.plusHours(2))
    val idx2 = Vector(
      now, now.plusMinutes(15), now.plusMinutes(30), now.plusMinutes(45),
      now.plusMinutes(60), now.plusMinutes(75), now.plusMinutes(90), now.plusMinutes(105))
    val ts = TimeSeries(idx, Vector(10f, 8f, 12f))
    val expected = TimeSeries(idx2, Vector(2, 2, 2, 2, 3, 3, 3, 3))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 15f))
    }
    result shouldBe Right(expected)
  }

  it should "interpolate missing values" in {
    val code =
      """
        |def main(xs: TimeSeries, d: Number): TimeSeries = interpolate(xs, minutes(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:02")
    val idx = Vector(now.plusMinutes(1), now.plusMinutes(4), now.plusMinutes(6))
    val ts = TimeSeries(idx, Vector(1f, 4f, 6f))
    val expected = TimeSeries(Vector(now.plusMinutes(1), now.plusMinutes(3), now.plusMinutes(5)), Vector(1f, 3f, 5f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 2f))
    }
    result shouldBe Right(expected)
  }

  it should "fill missing values with default value" in {
    val code =
      """
        |def main(xs: TimeSeries, d: Number, v: Number): TimeSeries = fill_missing(xs, minutes(d), v)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:02")
    val idx = Vector(now.plusMinutes(1), now.plusMinutes(3), now.plusMinutes(4))
    val ts = TimeSeries(idx, Vector(1f, 4f, 6f))
    val expected = TimeSeries(Vector(now.plusMinutes(1), now.plusMinutes(2), now.plusMinutes(3), now.plusMinutes(4)), Vector(1f, 0f, 4f, 6f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 1f, 0f))
    }
    result shouldBe Right(expected)
  }

  it should "repeat values" in {
    val code =
      """
        |def main(xs: TimeSeries, sd: DateTime, ed: DateTime, d: Number): TimeSeries = repeat(xs, sd, ed, hours(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusMinutes(15), now.plusMinutes(30), now.plusMinutes(45))
    val ts = TimeSeries(idx, Vector(1, 4, 6, 8))
    val idx2 = Vector(now.plusMinutes(60), now.plusMinutes(75), now.plusMinutes(90), now.plusMinutes(105))
    val expected = TimeSeries(idx ++ idx2, Vector(1, 4, 6, 8, 1, 4, 6, 8))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, now, now.plusHours(2), 1f))
    }
    result shouldBe Right(expected)
  }

  it should "find time weight average" in {
    val code =
      """
        |def main(xs: TimeSeries,  d: Number): TimeSeries = time_weight_average(xs, minutes(d))
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusSeconds(15), now.plusSeconds(30), now.plusSeconds(45), now.plusSeconds(65), now.plusSeconds(80))
    val idx2 = Vector(now, now.plusMinutes(1))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 3f, 2f, 6f))
    val expected = TimeSeries(idx2, Vector(2.25f, 4.75f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 1f))
    }
    result shouldBe Right(expected)
  }

  it should "find discrete values" in {
    val code =
      """
        |def main(xs: TimeSeries, v: Number): TimeSeries = discrete(xs, v)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now, now.plusMinutes(5), now.plusMinutes(10), now.plusMinutes(15),
      now.plusMinutes(20), now.plusMinutes(25), now.plusMinutes(30))
    val ts = TimeSeries(idx, Vector(1f, 2f, 3f, 5f, 8f, 3f, 2f))
    val expected = TimeSeries(idx.tail, Vector(1f, 1f, 2f, 3f, 5f, 9f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 10f))
    }
    result shouldBe Right(expected)
  }

  it should "interpolate outliers" in {
    val code =
      """
        |def main(xs: TimeSeries, b: Number, t: Number): TimeSeries = interpolate_outliers(xs, b ,t)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now.plusMinutes(1), now.plusMinutes(2), now.plusMinutes(3), now.plusMinutes(4), now.plusMinutes(5), now.plusMinutes(6))
    val ts = TimeSeries(idx, Vector(3f, 20f, 5f, 6f, 0f, 8f))
    val expected = TimeSeries(idx, Vector(3f, 4f, 5f, 6f, 7f, 8f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 1f, 10f))
    }
    result shouldBe Right(expected)
  }

  it should "const" in {
    val code =
      """
        |def main(xs: TimeSeries): TimeSeries = const(xs, 5)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now.plusMinutes(1), now.plusMinutes(2), now.plusMinutes(4), now.plusMinutes(5))
    val xs = TimeSeries(idx, Vector(3f, 20f, 5f, 6f))
    val expected = TimeSeries(idx, Vector(5f, 5f, 5f, 5f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(xs))
    }
    result shouldBe Right(expected)
  }


  it should "remove outliers" in {
    val code =
      """
        |def main(xs: TimeSeries, b: Number, t: Number): TimeSeries = remove_outliers(xs, b ,t)
      """.stripMargin
    val now = LocalDateTime.parse("2015-01-01T00:00:00")
    val idx = Vector(now.plusMinutes(1), now.plusMinutes(2), now.plusMinutes(3), now.plusMinutes(4), now.plusMinutes(5), now.plusMinutes(6))
    val idx2 = Vector(now.plusMinutes(1), now.plusMinutes(3), now.plusMinutes(4), now.plusMinutes(6))
    val ts = TimeSeries(idx, Vector(3f, 20f, 5f, 6f, 0f, 8f))
    val expected = TimeSeries(idx2, Vector(3f, 5f, 6f, 8f))
    val result = Compiler.make(code).flatMap { exec =>
      Interpreter(exec).run("main", Seq(ts, 1f, 10f))
    }
    result shouldBe Right(expected)
  }

}