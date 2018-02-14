package carldata.sf.core

import java.time.temporal.ChronoUnit
import java.time.{Duration, Instant}

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
  def $map(xs: TimeSeries[Double], f: Double => Double): TimeSeries[Double] = xs.mapValues(f)

  def $differentiate(xs: TimeSeries[Double]): TimeSeries[Double] = TimeSeries.differentiate(xs)

  def $delta_time(xs: TimeSeries[Double]): TimeSeries[Double] = {
    if (xs.isEmpty) xs
    else {
      val idx = xs.index.tail
      val vs = xs.index.tail.zip(xs.index)
        .map(x => x._1.getEpochSecond - x._2.getEpochSecond)
        .map(_.toDouble)
      TimeSeries(idx, vs)
    }
  }

  def $fill_missing(xs: TimeSeries[Double], d: Duration, v: Double): TimeSeries[Double] = xs.resampleWithDefault(d, v)

  def $discrete(xs: TimeSeries[Double], v: Double): TimeSeries[Double] = TimeSeries.diffOverflow(xs, v)

  def $interpolate(xs: TimeSeries[Double], d: Duration): TimeSeries[Double] = TimeSeries.interpolate(xs, d)

  def $interpolate_outliers(xs: TimeSeries[Double], bottom: Double, top: Double): TimeSeries[Double] = {
    def f(x: Double, y: Double): Double = {
      (x + y) / 2
    }

    xs.interpolateOutliers(bottom, top, f)
  }

  def $remove_outliers(xs: TimeSeries[Double], bottom: Double, top: Double): TimeSeries[Double] = xs.removeOutliers(bottom, top)

  def $groupby_avg(xs: TimeSeries[Double], f: Instant => Instant): TimeSeries[Double] = {
    def g(seq: Seq[Double]): Double = seq.sum / seq.length

    if (xs.isEmpty) xs
    else {
      xs.groupByTime(f, x => g(x.unzip._2))
    }
  }

  def $groupby_max(xs: TimeSeries[Double], f: Instant => Instant): TimeSeries[Double] = xs.groupByTime(f, _.unzip._2.max)

  def $groupby_median(xs: TimeSeries[Double], f: Instant => Instant): TimeSeries[Double] = {
    def g(seq: Seq[Double]): Double = {
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

  def $groupby_min(xs: TimeSeries[Double], f: Instant => Instant): TimeSeries[Double] = xs.groupByTime(f, _.unzip._2.min)


  def $groupby_sum(xs: TimeSeries[Double], f: Instant => Instant): TimeSeries[Double] = xs.groupByTime(f, _.unzip._2.sum)

  def $prev(xs: TimeSeries[Double]): TimeSeries[Double] = {
    if (xs.isEmpty) xs
    else {
      val idx = xs.index.tail
      val vs = xs.values.take(idx.size)
      TimeSeries(idx, vs)
    }
  }

  def $repeat(xs: TimeSeries[Double], sd: Instant, ed: Instant, d: Duration): TimeSeries[Double] = xs.repeat(sd, ed, d)

  def $rolling_avg(xs: TimeSeries[Double], d: Duration): TimeSeries[Double] = {
    def f(v: Seq[Double]): Double = v.sum / v.length

    xs.rollingWindow(d, f)
  }

  def $rolling_sum(xs: TimeSeries[Double], d: Duration): TimeSeries[Double] = xs.rollingWindow(d, _.sum)

  def $running_total(xs: TimeSeries[Double], f: Instant => Instant): TimeSeries[Double] = TimeSeries.integrateByTime(xs, f)

  def $shift(xs: TimeSeries[Double], d: Duration): TimeSeries[Double] = xs.shiftTime(d)

  def $slice(xs: TimeSeries[Double], sd: Instant, ed: Instant): TimeSeries[Double] = xs.slice(sd, ed)

  def $step(xs: TimeSeries[Double], d: Duration): TimeSeries[Double] = TimeSeries.step(xs, d)

  def $time_weight_average(xs: TimeSeries[Double], d: Duration): TimeSeries[Double] = {
    def f[V](x1: (Instant, V), x2: (Instant, V), tsh: Instant) = x1._2

    val xs2 = TimeSeries[Double](xs.index.head.truncatedTo(ChronoUnit.HOURS) +: xs.index, 0d +: xs.values)
      .addMissing(d, f)
    
    def g[V](ys0: Seq[(Instant, V)]): V = {
      val ys = if (ys0.head._1 != xs.index.head && ys0.head._1 == xs2.index.head) ys0.tail.sortBy(_._1) else ys0.sortBy(_._1)
      val unzipped = ys.unzip
      val lastIndex = floor_time(xs2.index.head, unzipped._1.head, d).plus(d)
      val deltas = (unzipped._1.tail :+ lastIndex).zip(unzipped._1)
        .map(x => x._1.getEpochSecond - x._2.getEpochSecond)
        .map(_.toDouble)

      unzipped._2
        .zip(deltas)
        .map(x => x._2 * (x._1.asInstanceOf[Double] / d.getSeconds))
        .sum
        .asInstanceOf[V]
    }

    val ys = xs2.groupByTime(floor_time(xs2.index.head, _, d), g)
    TimeSeries(ys.index.tail :+ ys.index.last.plusSeconds(d.getSeconds), ys.values.map(_.asInstanceOf[Double]))
  }

  def $const(xs: TimeSeries[Double], v: Double): TimeSeries[Double] = {
    xs.mapValues(_ => v)
  }

  private def floor_time(st: Instant, ct: Instant, d: Duration): Instant = {
    val diff = ChronoUnit.SECONDS.between(st, ct)
    st.plusSeconds((diff / d.getSeconds) * d.getSeconds)
  }

}

