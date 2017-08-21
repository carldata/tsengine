package carldata.sf.core

import java.time.LocalDateTime

import carldata.sf.Runtime
import carldata.sf.Runtime.{NumberValue, StringValue, Value}
//import carldata.sf.Runtime.{DateTimeValue}

/**
  * Core functions and types which can be accessed from the script
  */
object DateTime {
  // Header which will be provided to the compiler
  val header: String =
    """
      |external def $adjust_date(dt: DateTime, y: Number, m: Number, d: Number): DateTime
      |external def $adjust_time(dt: DateTime, h: Number, m: Number, s: Number): DateTime
      |external def date(d: String): DateTime
      |external def datetime(y: Number, m: Number, d: Number): DateTime
      |external def datetime(y: Number, m: Number, d: Number, h: Number, mt: Number, s: Number, ns: Number): DateTime
    """.stripMargin
}

class DateTime extends Runtime {

  case class DateTimeValue(dt: LocalDateTime) extends Value

  // Function definition
  def $adjust_date(dt: DateTimeValue, y: NumberValue, m: NumberValue, d: NumberValue): DateTimeValue = DateTimeValue(dt.dt.withYear(parse(y)).withMonth(parse(m)).withDayOfMonth(parse(d)))
  def $adjust_time(dt: DateTimeValue, h: NumberValue, m: NumberValue, s: NumberValue): DateTimeValue = DateTimeValue(dt.dt.withHour(parse(h)).withMinute(parse(m)).withSecond(parse(m)))
  def $date(s: StringValue): DateTimeValue = DateTimeValue(LocalDateTime.parse(s.str))
  def $datetime(y: NumberValue, m: NumberValue, d: NumberValue): DateTimeValue = DateTimeValue(LocalDateTime.of(parse(y), parse(m), parse(d), 0, 0, 0))
  def $datetime(y: NumberValue, m: NumberValue, d: NumberValue, h: NumberValue, mt: NumberValue, s: NumberValue, ns: NumberValue): DateTimeValue = DateTimeValue(LocalDateTime.of(parse(y), parse(m), parse(d), parse(h), parse(mt), parse(s), parse(ns)))



  private def parse(n: NumberValue): Int = n.v.toInt

}

