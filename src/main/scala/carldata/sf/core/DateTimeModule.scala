package carldata.sf.core

import java.time.LocalDateTime

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
      |external def from_date(y: Number, m: Number, d: Number): DateTime
      |external def from_datetime(y: Number, m: Number, d: Number, h: Number, mt: Number, s: Number, ns: Number): DateTime
      |external def day_of_week(dt: DateTime) : Number
      |external def floor_hours(dt: DateTime): DateTime
      |external def floor_minutes(dt: DateTime): DateTime
      |external def floor_seconds(dt: DateTime): DateTime
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

  def $from_date(y: Float, m: Float, d: Float): LocalDateTime =
    LocalDateTime.of(parse(y), parse(m), parse(d), 0, 0, 0)

  def $from_datetime(y: Float, m: Float, d: Float, h: Float, mt: Float,
                     s: Float, ns: Float): LocalDateTime =
    LocalDateTime.of(parse(y), parse(m), parse(d), parse(h), parse(mt), parse(s), parse(ns))

  def $day_of_week(dt: LocalDateTime): Float = dt.getDayOfWeek.getValue

  def $floor_hours(dt: LocalDateTime): LocalDateTime = dt.withHour(0)

  def $floor_minutes(dt: LocalDateTime): LocalDateTime = dt.withMinute(0)

  def $floor_seconds(dt: LocalDateTime): LocalDateTime = dt.withSecond(0)

  private def parse(n: Float): Int = n.toInt

}

