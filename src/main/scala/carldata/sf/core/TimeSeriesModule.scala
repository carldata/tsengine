package carldata.sf.core

import carldata.series.TimeSeries
import carldata.sf.Runtime
import carldata.sf.Runtime.{Function1Value, NumberValue, Value}

/**
  * Extend FlowScript with operations on the Time Series
  */
object TimeSeriesModule {

  case class TimeSeriesValue(ts: TimeSeries[Float]) extends Value

  // Header which will be provided to the compiler
  val header: String =
    """
      |external def map(xs: TimeSeries, f: Number => Number): TimeSeries
    """.stripMargin

  def apply(): TimeSeriesModule = new TimeSeriesModule()
}

class TimeSeriesModule extends Runtime{

  import carldata.sf.core.TimeSeriesModule._

  // Function definition
  def $map(xs: TimeSeriesValue, f: Function1Value): TimeSeriesValue = {
    def lf(x: Float): Float = f.f(NumberValue(x)).v
    val vs: Vector[Float] = xs.ts.values.map(lf)
    TimeSeriesValue(new TimeSeries(xs.ts.index, vs))
  }
}

