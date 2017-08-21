package carldata.sf.core

import carldata.sf.Runtime.{NumberValue, StringValue}
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
  it should "get day of week" in {
    val dt = new DateTime()
    val dt1 = dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21)) // Monday
    val dt2 = dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(23)) // Wednesday
    val dt3 = dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(26)) // Saturday
    dt.$day_of_week(dt1) shouldBe NumberValue(1)
    dt.$day_of_week(dt2) shouldBe NumberValue(3)
    dt.$day_of_week(dt3) shouldBe NumberValue(6)
  }

  it should "floor hours" in {
    val dt = new DateTime()
    val dt1 = dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(11), NumberValue(31), NumberValue(36), NumberValue(0))
    dt.$floor_hours(dt1) shouldBe dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(0), NumberValue(31), NumberValue(36), NumberValue(0))
  }

  it should "floor minutes" in {
    val dt = new DateTime()
    val dt1 = dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(11), NumberValue(31), NumberValue(36), NumberValue(0))
    dt.$floor_minutes(dt1) shouldBe dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(11), NumberValue(0), NumberValue(36), NumberValue(0))
  }

  it should "floor seconds" in {
    val dt = new DateTime()
    val dt1 = dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(11), NumberValue(31), NumberValue(36), NumberValue(0))
    dt.$floor_seconds(dt1) shouldBe dt.$datetime(NumberValue(2017), NumberValue(8), NumberValue(21), NumberValue(11), NumberValue(31), NumberValue(0), NumberValue(0))
  }

}
