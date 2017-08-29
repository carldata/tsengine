package carldata.sf.core

import carldata.series.TimeSeries
import carldata.sf.Runtime

/**
  * Extend FlowScript with operations on the Time Series
  */
object TimeSeriesModule {

  // Header which will be provided to the compiler
  val header: String =
    """
      |external def map(xs: TimeSeries, f: Number => Number): TimeSeries
      |external def differentiate(xs: TimeSeries): TimeSeries
    """.stripMargin

  def apply(): TimeSeriesModule = new TimeSeriesModule()
}

class TimeSeriesModule extends Runtime {

  // Function definition
  def $map(xs: TimeSeries[Float], f: Float => Float): TimeSeries[Float] = xs.mapValues(f)

  def $differentiate(xs: TimeSeries[Float]): TimeSeries[Float] = xs.differentiate
}

