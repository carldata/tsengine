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
    """.stripMargin

  def apply(): TimeSeriesModule = new TimeSeriesModule()
}

class TimeSeriesModule extends Runtime{


  // Function definition
  def $map(xs: TimeSeries[Float], f: Float => Float): TimeSeries[Float] = {
    def lf(x: Float): Float = f(x)
    val vs: Vector[Float] = xs.values.map(lf)
    new TimeSeries(xs.index, vs)
  }
}

