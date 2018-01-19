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
  def $ceil(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.ceil(x).toFloat)
  def $cos(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.cos(x).toFloat)
  def $exp(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.exp(x).toFloat)
  def $floor(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.floor(x).toFloat)
  def $log(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.log(x).toFloat)
  def $log10(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.log10(x).toFloat)
  def $pow(xs: TimeSeries[Float], b: Float): TimeSeries[Float] = xs.mapValues(x => math.pow(x, b).toFloat)
  def $round(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.round(x).toFloat)
  def $sin(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.sin(x).toFloat)
  def $sqrt(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.sqrt(x).toFloat)
  def $tan(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.tan(x).toFloat)
  def $tanh(xs: TimeSeries[Float]): TimeSeries[Float] = xs.mapValues(x => math.tanh(x).toFloat)
}

