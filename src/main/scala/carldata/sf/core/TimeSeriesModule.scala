package carldata.sf.core

import carldata.series.TimeSeries
import carldata.sf.Runtime
import carldata.sf.Runtime.Value
import carldata.sf.core.TimeSeriesModule.TimeSeriesValue

/**
  * Extend FlowScript with operations on the Time Series
  */
object TimeSeriesModule {

  case class TimeSeriesValue(ts: TimeSeries[Float]) extends Value
  // Header which will be provided to the compiler
  val header: String =
    """
      |external def ts_test(xs: TimeSeries): TimeSeries
    """.stripMargin

  def apply(): TimeSeriesModule = new TimeSeriesModule()
}

class TimeSeriesModule extends Runtime{

  // Function definition
  def $ts_test(xs: TimeSeriesValue): TimeSeriesValue = xs
}

