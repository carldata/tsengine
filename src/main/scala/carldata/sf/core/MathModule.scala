package carldata.sf.core

import carldata.series.TimeSeries
import carldata.sf.Runtime

/**
  * Core functions and types which can be accessed from the script
  */
object MathModule {
  // Header which will be provided to the compiler
  val header: String =
    """
      |external def acos(a: TimeSeries): TimeSeries
      |external def asin(a: TimeSeries): TimeSeries
      |external def ceil(a: TimeSeries): TimeSeries
      |external def cos(a: TimeSeries): TimeSeries
      |external def exp(a: TimeSeries): TimeSeries
      |external def floor(a: TimeSeries): TimeSeries
      |external def log(a: TimeSeries): TimeSeries
      |external def log10(a: TimeSeries): TimeSeries
      |external def pow(a: TimeSeries, b: Number): TimeSeries
      |external def round(a: TimeSeries): TimeSeries
      |external def sin(a: TimeSeries): TimeSeries
      |external def sqrt(a: TimeSeries): TimeSeries
      |external def tan(a: TimeSeries): TimeSeries
      |external def tanh(a: TimeSeries): TimeSeries
    """.stripMargin

  def apply(): MathModule = new MathModule()
}

class MathModule extends Runtime{

  // Function definition
  def $acos(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.acos(x))
  def $asin(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.asin(x))
  def $ceil(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.ceil(x))
  def $cos(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.cos(x))
  def $exp(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.exp(x))
  def $floor(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.floor(x))
  def $log(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.log(x))
  def $log10(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.log10(x))
  def $pow(xs: TimeSeries[Double], b: Double): TimeSeries[Double] = xs.mapValues(x => math.pow(x, b))
  def $round(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.round(x))
  def $sin(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.sin(x))
  def $sqrt(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.sqrt(x))
  def $tan(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.tan(x))
  def $tanh(xs: TimeSeries[Double]): TimeSeries[Double] = xs.mapValues(x => math.tanh(x))
}

