package carldata.sf.core

import java.time.ZoneOffset

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
      |external def delta_time(xs: TimeSeries): TimeSeries
    """.stripMargin

  def apply(): TimeSeriesModule = new TimeSeriesModule()
}

class TimeSeriesModule extends Runtime {

  // Function definition
  def $map(xs: TimeSeries[Float], f: Float => Float): TimeSeries[Float] = xs.mapValues(f)
  def $differentiate(xs: TimeSeries[Float]): TimeSeries[Float] = xs.differentiate
  def $delta_time(xs: TimeSeries[Float]): TimeSeries[Float] = {
    if(xs.isEmpty) xs
    else{
      val idx = xs.index.tail
      val vs = xs.index.tail.zip(xs.index)
        .map(x => x._1.toEpochSecond(ZoneOffset.UTC) - x._2.toEpochSecond(ZoneOffset.UTC))
        .map(_.toFloat)
      TimeSeries(idx, vs)
    }
  }
}

