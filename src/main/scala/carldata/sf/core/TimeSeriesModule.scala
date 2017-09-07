package carldata.sf.core

import java.time.temporal.ChronoUnit
import java.time.{Duration, LocalDateTime, ZoneOffset}

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
      |external def maximum(xs: TimeSeries, d: Duration): TimeSeries
      |external def median(xs: TimeSeries, d: Duration): TimeSeries
      |external def minimum(xs: TimeSeries, d: Duration): TimeSeries
      |external def running_total(xs: TimeSeries, d: Duration): TimeSeries
      |external def sum(xs: TimeSeries, d: Duration): TimeSeries
    """.stripMargin

  def apply(): TimeSeriesModule = new TimeSeriesModule()
}

class TimeSeriesModule extends Runtime {

  // Function definition
  def $map(xs: TimeSeries[Float], f: Float => Float): TimeSeries[Float] = xs.mapValues(f)

  def $differentiate(xs: TimeSeries[Float]): TimeSeries[Float] = TimeSeries.differentiate(xs)

  def $delta_time(xs: TimeSeries[Float]): TimeSeries[Float] = {
    if (xs.isEmpty) xs
    else {
      val idx = xs.index.tail
      val vs = xs.index.tail.zip(xs.index)
        .map(x => x._1.toEpochSecond(ZoneOffset.UTC) - x._2.toEpochSecond(ZoneOffset.UTC))
        .map(_.toFloat)
      TimeSeries(idx, vs)
    }
  }

  def $maximum(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = {
    if (xs.isEmpty) xs
    else {
      val st = xs.index.head
      xs.groupByTime(floor_time(st, _, d), _.max)
    }
  }

  def $median(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = {
    def f(seq: Seq[Float]): Float = {
      val sorted = seq.sorted
      val center = Math.abs(sorted.length / 2)
      if (seq.length % 2 == 0) {
        (sorted(center) + sorted(center - 1)) / 2
      }
      else {
        sorted(center)
      }
    }

    if (xs.isEmpty) xs
    else {
      val st = xs.index.head
      xs.groupByTime(floor_time(st, _, d), f)
    }


  }

  def $minimum(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = {
    if (xs.isEmpty) xs
    else {
      val st = xs.index.head
      xs.groupByTime(floor_time(st, _, d), _.min)
    }
  }

  def $running_total(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = TimeSeries.integrateByTime(xs, d)

  def $sum(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = {
    if (xs.isEmpty) xs
    else {
      val st = xs.index.head
      xs.groupByTime(floor_time(st, _, d), _.sum)
    }
  }

  private def floor_time(st: LocalDateTime, ct: LocalDateTime, d: Duration): LocalDateTime = {
    val diff = ChronoUnit.SECONDS.between(st, ct)
    st.plusSeconds((diff / d.getSeconds) * d.getSeconds)
  }

}

