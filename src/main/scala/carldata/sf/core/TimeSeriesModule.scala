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
      |external def discrete(xs: TimeSeries, v: Number): TimeSeries
      |external def delta_time(xs: TimeSeries): TimeSeries
      |external def fill_missing(xs: TimeSeries, d: Duration, v: Number): TimeSeries
      |external def groupby_avg(xs: TimeSeries, f: DateTime => DateTime): TimeSeries
      |external def groupby_max(xs: TimeSeries, f: DateTime => DateTime): TimeSeries
      |external def groupby_median(xs: TimeSeries, f: DateTime => DateTime): TimeSeries
      |external def groupby_min(xs: TimeSeries, f: DateTime => DateTime): TimeSeries
      |external def groupby_sum(xs: TimeSeries, f: DateTime => DateTime): TimeSeries
      |external def interpolate(xs: TimeSeries, d: Duration): TimeSeries
      |external def interpolate_outliers(xs: TimeSeries, bottom: Number, top: Number): TimeSeries
      |external def prev(xs: TimeSeries): TimeSeries
      |external def remove_outliers(xs: TimeSeries, bottom: Number, top: Number): TimeSeries
      |external def repeat(xs: TimeSeries, sd: DateTime, ed: DateTime, d: Duration): TimeSeries
      |external def rolling_avg(xs: TimeSeries, d: Duration): TimeSeries
      |external def rolling_sum(xs: TimeSeries, d: Duration): TimeSeries
      |external def running_total(xs: TimeSeries, f: DateTime => DateTime): TimeSeries
      |external def shift(xs: TimeSeries, d: Duration): TimeSeries
      |external def slice(xs: TimeSeries, sd: DateTime, ed: DateTime): TimeSeries
      |external def step(xs: TimeSeries, d: Duration): TimeSeries
      |external def time_weight_average(xs: TimeSeries, d: Duration): TimeSeries
      |external def const(xs: TimeSeries, v: Number): TimeSeries
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

  def $fill_missing(xs: TimeSeries[Float], d: Duration, v: Float): TimeSeries[Float] = xs.resampleWithDefault(d, v)

  def $discrete(xs: TimeSeries[Float], v: Float): TimeSeries[Float] = TimeSeries.diffOverflow(xs, v)

  def $interpolate(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = TimeSeries.interpolate(xs, d)

  def $interpolate_outliers(xs: TimeSeries[Float], bottom: Float, top: Float): TimeSeries[Float] = {
    def f(x: Float, y: Float): Float = {
      (x + y) / 2
    }

    xs.interpolateOutliers(bottom, top, f)
  }

  def $remove_outliers(xs: TimeSeries[Float], bottom: Float, top: Float): TimeSeries[Float] = xs.removeOutliers(bottom, top)

  def $groupby_avg(xs: TimeSeries[Float], f: LocalDateTime => LocalDateTime): TimeSeries[Float] = {
    def g(seq: Seq[Float]): Float = seq.sum / seq.length

    if (xs.isEmpty) xs
    else {
      xs.groupByTime(f, x => g(x.unzip._2))
    }
  }

  def $groupby_max(xs: TimeSeries[Float], f: LocalDateTime => LocalDateTime): TimeSeries[Float] = xs.groupByTime(f, _.unzip._2.max)

  def $groupby_median(xs: TimeSeries[Float], f: LocalDateTime => LocalDateTime): TimeSeries[Float] = {
    def g(seq: Seq[Float]): Float = {
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
      xs.groupByTime(f, x => g(x.unzip._2))
    }


  }

  def $groupby_min(xs: TimeSeries[Float], f: LocalDateTime => LocalDateTime): TimeSeries[Float] = xs.groupByTime(f, _.unzip._2.min)


  def $groupby_sum(xs: TimeSeries[Float], f: LocalDateTime => LocalDateTime): TimeSeries[Float] = xs.groupByTime(f, _.unzip._2.sum)

  def $prev(xs: TimeSeries[Float]): TimeSeries[Float] = {
    if (xs.isEmpty) xs
    else {
      val idx = xs.index.tail
      val vs = xs.values.take(idx.size)
      TimeSeries(idx, vs)
    }
  }

  def $repeat(xs: TimeSeries[Float], sd: LocalDateTime, ed: LocalDateTime, d: Duration): TimeSeries[Float] = xs.repeat(sd, ed, d)

  def $rolling_avg(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = {
    def f(v: Seq[Float]): Float = v.sum / v.length

    xs.rollingWindow(d, f)
  }

  def $rolling_sum(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = xs.rollingWindow(d, _.sum)

  def $running_total(xs: TimeSeries[Float], f: LocalDateTime => LocalDateTime): TimeSeries[Float] = TimeSeries.integrateByTime(xs, f)

  def $shift(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = xs.shiftTime(d, forward = true)

  def $slice(xs: TimeSeries[Float], sd: LocalDateTime, ed: LocalDateTime): TimeSeries[Float] = xs.slice(sd, ed)

  def $step(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = TimeSeries.step(xs, d)

  def $time_weight_average(xs: TimeSeries[Float], d: Duration): TimeSeries[Float] = {
    def f(x1: (LocalDateTime, Float), x2: (LocalDateTime, Float), tsh: LocalDateTime) = x1._2

    val xs2 = TimeSeries(xs.index.head.withMinute(0).withSecond(0) +: xs.index, 0f +: xs.values)
      .addMissing(d, f)

    def g(ys0: Seq[(LocalDateTime, Float)]): Float = {
      val ys = if (ys0.head._1 != xs.index.head && ys0.head._1 == xs2.index.head) ys0.tail else ys0
      val unzipped = ys.unzip
      val lastIndex = floor_time(xs2.index.head, unzipped._1.head, d).plus(d)
      val deltas = (unzipped._1.tail :+ lastIndex).zip(unzipped._1)
        .map(x => x._1.toEpochSecond(ZoneOffset.UTC) - x._2.toEpochSecond(ZoneOffset.UTC))
        .map(_.toFloat)

      unzipped._2
        .zip(deltas)
        .map(x => x._2 * (x._1 / d.getSeconds))
        .sum
    }

    val ys = xs2.groupByTime(floor_time(xs2.index.head, _, d), g)
    TimeSeries(ys.index.tail :+ ys.index.last.plusSeconds(d.getSeconds), ys.values)
  }

  def $const(xs: TimeSeries[Float], v: Float): TimeSeries[Float] = {
    xs.mapValues(_ => v)
  }

  private def floor_time(st: LocalDateTime, ct: LocalDateTime, d: Duration): LocalDateTime = {
    val diff = ChronoUnit.SECONDS.between(st, ct)
    st.plusSeconds((diff / d.getSeconds) * d.getSeconds)
  }

}

