package carldata.sf.core

import java.time.LocalDateTime

import carldata.sf.Runtime
import carldata.sf.Runtime.{NumberValue, StringValue, Value}
import carldata.sf.core.DateTime.DateTimeValue

/**
  * Core functions and types which can be accessed from the script
  */
object DateTime {

  case class DateTimeValue(dt: LocalDateTime) extends Value

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

  def apply(): DateTime = new DateTime()
}

class DateTime extends Runtime {

  // Function definition
  def $adjust_date(dt: DateTimeValue, y: NumberValue, m: NumberValue, d: NumberValue): DateTimeValue = DateTimeValue(dt.dt.withYear(parse(y)).withMonth(parse(m)).withDayOfMonth(parse(d)))

  def $adjust_time(dt: DateTimeValue, h: NumberValue, m: NumberValue, s: NumberValue): DateTimeValue = DateTimeValue(dt.dt.withHour(parse(h)).withMinute(parse(m)).withSecond(parse(m)))

  def $date(s: StringValue): DateTimeValue = DateTimeValue(LocalDateTime.parse(s.str))

  def $from_date(y: NumberValue, m: NumberValue, d: NumberValue): DateTimeValue = DateTimeValue(LocalDateTime.of(parse(y), parse(m), parse(d), 0, 0, 0))

  def $from_datetime(y: NumberValue, m: NumberValue, d: NumberValue, h: NumberValue, mt: NumberValue, s: NumberValue, ns: NumberValue): DateTimeValue = DateTimeValue(LocalDateTime.of(parse(y), parse(m), parse(d), parse(h), parse(mt), parse(s), parse(ns)))

  def $day_of_week(dt: DateTimeValue): NumberValue = NumberValue(dt.dt.getDayOfWeek.getValue)

  def $floor_hours(dt: DateTimeValue): DateTimeValue = DateTimeValue(dt.dt.withHour(0))

  def $floor_minutes(dt: DateTimeValue): DateTimeValue = DateTimeValue(dt.dt.withMinute(0))

  def $floor_seconds(dt: DateTimeValue): DateTimeValue = DateTimeValue(dt.dt.withSecond(0))


  private def parse(n: NumberValue): Int = n.v.toInt

}

