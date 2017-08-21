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
      |external def date(d: String): DateTime
      |external def datetime(y: Number, m: Number, d: Number, h: Number, mt: Number, s: Number, ns: Number): DateTime
    """.stripMargin
}

class DateTime extends Runtime {

  case class DateTimeValue(dt: LocalDateTime) extends Value

  // Function definition
  def $date(s: StringValue): DateTimeValue = DateTimeValue(LocalDateTime.parse(s.str))
  def $datetime(y: NumberValue, m: NumberValue, d: NumberValue, h: NumberValue, mt: NumberValue, s: NumberValue, ns: NumberValue): DateTimeValue = DateTimeValue(LocalDateTime.of(parse(y), parse(m),parse(d),parse(h),parse(mt),parse(s),parse(ns)))


  private def parse(n: NumberValue): Int = n.v.toInt

}

