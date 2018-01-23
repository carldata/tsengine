package carldata.sf.core

import java.time.temporal.ChronoUnit
import java.time.{Duration, Instant, LocalDateTime, ZoneOffset}

import carldata.series.TimeConverter
import carldata.sf.Runtime

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
  def $adjust_date(dt: LocalDateTime, y: Float, m: Float, d: Float): LocalDateTime =
    dt.withYear(parse(y)).withMonth(parse(m)).withDayOfMonth(parse(d))

  def $adjust_time(dt: LocalDateTime, h: Float, m: Float, s: Float): LocalDateTime =
    dt.withHour(parse(h)).withMinute(parse(m)).withSecond(parse(m))

  def $date(s: String): LocalDateTime = LocalDateTime.parse(s)

  def $days(n: Float): Duration = Duration.ofDays(n.toLong)

  def $dt_convert(d: String): Instant => Instant = {
    val f: LocalDateTime => LocalDateTime = TimeConverter.mkCronLike(d)
      .map(TimeConverter.mkConverter)
      .getOrElse(identity)

    (i: Instant) => f(LocalDateTime.ofInstant(i, ZoneOffset.UTC)).toInstant(ZoneOffset.UTC)
  }


  def $from_date(y: Float, m: Float, d: Float): LocalDateTime =
    LocalDateTime.of(parse(y), parse(m), parse(d), 0, 0, 0)

  def $from_datetime(y: Float, m: Float, d: Float, h: Float, mt: Float,
                     s: Float, ns: Float): LocalDateTime =
    LocalDateTime.of(parse(y), parse(m), parse(d), parse(h), parse(mt), parse(s), parse(ns))

  def $day_of_week(dt: LocalDateTime): Float = dt.getDayOfWeek.getValue

  def $floor_hours(dt: LocalDateTime): LocalDateTime = dt.withHour(0)

  def $floor_minutes(dt: LocalDateTime): LocalDateTime = dt.withMinute(0)

  def $floor_seconds(dt: LocalDateTime): LocalDateTime = dt.withSecond(0)

  def $hours(n: Float): Duration = Duration.ofHours(n.toLong)

  def $minutes(n: Float): Duration = Duration.ofMinutes(n.toLong)

  def $months(n: Float): Duration = ChronoUnit.MONTHS.getDuration.multipliedBy(n.toLong)

  def $weeks(n: Float): Duration = ChronoUnit.WEEKS.getDuration.multipliedBy(n.toLong)

  def $years(n: Float): Duration = ChronoUnit.YEARS.getDuration.multipliedBy(n.toLong)


  private def parse(n: Float): Int = n.toInt

}

