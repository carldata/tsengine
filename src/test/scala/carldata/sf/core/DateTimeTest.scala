package carldata.sf.core

import carldata.sf.{Compiler, Interpreter}
import carldata.sf.Runtime.{BoolValue, NumberValue, StringValue}
import org.scalatest._

class DateTimeTest extends FlatSpec with Matchers {
  private val libs = Seq(new Math())

  "Datetime" should "convert date from string" in {
    val str = "2017-08-21T10:30:30"
    val dt = new DateTime()
    dt.$date(StringValue(str)) shouldBe dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(10), NumberValue(30), NumberValue(30), NumberValue(0))
  }
}
