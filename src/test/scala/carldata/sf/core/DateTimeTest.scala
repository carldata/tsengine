package carldata.sf.core

import carldata.sf.Runtime.{NumberValue, StringValue}
import carldata.sf.{Compiler, Interpreter}
import org.scalatest._

class DateTimeTest extends FlatSpec with Matchers {
  private val libs = Seq(new Math())

  "Datetime" should "convert date from string" in {
    val str = "2017-08-21T10:30:30"
    val dt = new DateTime()
    dt.$date(StringValue(str)) shouldBe dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(10), NumberValue(30), NumberValue(30), NumberValue(0))
  }

  it should "adjust date" in {
    val dt = new DateTime()
    val dt1 = dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21))
    dt.$adjust_date(dt1,NumberValue(2016), NumberValue(1), NumberValue(1)) shouldBe dt.$datetime(NumberValue(2016), NumberValue(1), NumberValue(1))
  }

  it should "adjust time" in {
    val dt = new DateTime()
    val dt1 = dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21))
    dt.$adjust_time(dt1,NumberValue(10), NumberValue(30), NumberValue(30)) shouldBe dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(10), NumberValue(30), NumberValue(30), NumberValue(0))
  }


}
