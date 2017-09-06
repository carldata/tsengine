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
      |external def minimum(xs: TimeSeries, d: Duration): TimeSeries
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
      val st = xs.head.get._1
      val ys = xs.groupByTime(floor_time(st, _, d), _.max)
      find_missing(ys, d)
    }
  }

  def $minimum(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = {
    if (xs.isEmpty) xs
    else {
      val st = xs.head.get._1
      val ys = xs.groupByTime(floor_time(st, _, d), _.min)
      find_missing(ys, d)
    }
  }

  def $sum(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = {
    if (xs.isEmpty) xs
    else {
      val st = xs.head.get._1
      val ys = xs.groupByTime(floor_time(st, _, d), _.sum)
      find_missing(ys, d)
    }
  }

  private def floor_time(st: LocalDateTime, ct: LocalDateTime, d: Duration): LocalDateTime = {
    val diff = ChronoUnit.SECONDS.between(st, ct)
    st.plusSeconds((diff / d.getSeconds) * d.getSeconds)
  }

  private def find_missing(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = {
    val diff = ChronoUnit.SECONDS.between(xs.head.get._1, xs.last.get._1)
    if ((diff / d.getSeconds) + 1 == xs.length) xs
    else {
      val ts = Iterator.iterate(xs.index.head)(_.plusNanos(d.toNanos))
        .takeWhile(_.isBefore(xs.index.last.plusNanos(1))).toVector
        .map(x => (x, 0f))
        .map(x => (x._1, xs.filter(z => z._1 == x._1).head match {
          case Some(t) => t._2 + x._2
          case _ => x._2
        }))
        .map(x => (x._1.toEpochSecond(ZoneOffset.UTC), x._2))
      TimeSeries.fromTimestamps(ts)
    }
  }

}

