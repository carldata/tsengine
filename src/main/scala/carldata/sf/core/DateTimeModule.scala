package carldata.sf.core

import java.time.temporal.ChronoUnit
import java.time.{Duration, Instant, LocalDateTime, ZoneOffset}

import carldata.sf.Runtime
import carldata.sf.face.TimeConverter

/**
  * Core functions and types which can be accessed from the script
  */
object DateTimeModule {

  // Header which will be provided to the compiler
  val header: String =
    """
      |external def adjust_date(dt: DateTime, y: Number, m: Number, d: Number): DateTime
      |external def adjust_time(dt: DateTime, h: Number, m: Number, s: Number): DateTime
      |external def date(d: String): DateTime
      |external def days(n: Number): Duration
      |external def dt_convert(d: String): DateTime => DateTime
      |external def from_date(y: Number, m: Number, d: Number): DateTime
      |external def from_datetime(y: Number, m: Number, d: Number, h: Number, mt: Number, s: Number, ns: Number): DateTime
      |external def day_of_week(dt: DateTime) : Number
      |external def floor_hours(dt: DateTime): DateTime
      |external def floor_minutes(dt: DateTime): DateTime
      |external def floor_seconds(dt: DateTime): DateTime
      |external def hours(n: Number): Duration
      |external def minutes(n: Number): Duration
      |external def months(n: Number): Duration
      |external def weeks(n: Number): Duration
      |external def years(n: Number): Duration
    """.stripMargin

  def apply(): DateTimeModule = new DateTimeModule()
}

class DateTimeModule extends Runtime {

  // Function definition
  def $adjust_date(dt: Instant, y: Double, m: Double, d: Double): Instant = {
    LocalDateTime.ofInstant(dt, ZoneOffset.UTC)
      .withYear(parse(y))
      .withMonth(parse(m))
      .withDayOfMonth(parse(d))
      .toInstant(ZoneOffset.UTC)
  }

  def $adjust_time(dt: Instant, h: Double, m: Double, s: Double): Instant = {
    LocalDateTime.ofInstant(dt, ZoneOffset.UTC)
      .withHour(parse(h))
      .withMinute(parse(m))
      .withSecond(parse(m))
      .toInstant(ZoneOffset.UTC)
  }

  def $date(s: String): Instant = LocalDateTime.parse(s).toInstant(ZoneOffset.UTC)

  def $days(n: Double): Duration = Duration.ofDays(n.toLong)

  def $dt_convert(d: String): Instant => Instant = {
    val f: LocalDateTime => LocalDateTime = TimeConverter.mkCronLike(d)
      .map(TimeConverter.mkConverter)
      .getOrElse(identity)

    (i: Instant) => f(LocalDateTime.ofInstant(i, ZoneOffset.UTC)).toInstant(ZoneOffset.UTC)
  }


  def $from_date(y: Double, m: Double, d: Double): Instant =
    LocalDateTime.of(parse(y), parse(m), parse(d), 0, 0, 0).toInstant(ZoneOffset.UTC)

  def $from_datetime(y: Double, m: Double, d: Double, h: Double, mt: Double,
                     s: Double, ns: Double): Instant =
    LocalDateTime.of(parse(y), parse(m), parse(d), parse(h), parse(mt), parse(s), parse(ns)).toInstant(ZoneOffset.UTC)

  def $day_of_week(dt: Instant): Double = LocalDateTime.ofInstant(dt, ZoneOffset.UTC).getDayOfWeek.getValue

  def $floor_hours(dt: Instant): Instant = dt.truncatedTo(ChronoUnit.DAYS)

  def $floor_minutes(dt: Instant): Instant = dt.truncatedTo(ChronoUnit.HOURS)

  def $floor_seconds(dt: Instant): Instant = dt.truncatedTo(ChronoUnit.MINUTES)

  def $hours(n: Double): Duration = Duration.ofHours(n.toLong)

  def $minutes(n: Double): Duration = Duration.ofMinutes(n.toLong)

  def $months(n: Double): Duration = ChronoUnit.MONTHS.getDuration.multipliedBy(n.toLong)

  def $weeks(n: Double): Duration = ChronoUnit.WEEKS.getDuration.multipliedBy(n.toLong)

  def $years(n: Double): Duration = ChronoUnit.YEARS.getDuration.multipliedBy(n.toLong)


  private def parse(n: Double): Int = n.toInt

}

