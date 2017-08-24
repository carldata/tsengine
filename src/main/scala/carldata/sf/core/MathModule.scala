package carldata.sf.core

import carldata.sf.Runtime

/**
  * Core functions and types which can be accessed from the script
  */
object MathModule {
  // Header which will be provided to the compiler
  val header: String =
    """
      |external def ceil(a: Number): Number
      |external def cos(a: Number): Number
      |external def exp(a: Number): Number
      |external def floor(a: Number): Number
      |external def log(a: Number): Number
      |external def log10(a: Number): Number
      |external def max(a: Number, b: Number): Number
      |external def min(a: Number, b: Number): Number
      |external def pi(): Number
      |external def pow(a: Number, b: Number): Number
      |external def round(a: Number): Number
      |external def sin(a: Number): Number
      |external def sqrt(a: Number): Number
      |external def tan(a: Number): Number
      |external def tanh(a: Number): Number
    """.stripMargin

  def apply(): MathModule = new MathModule()
}

class MathModule extends Runtime{

  // Function definition
  def $ceil(a: Float): Float = math.ceil(a).toFloat
  def $cos(a: Float): Float = math.cos(a).toFloat
  def $exp(a: Float): Float = math.exp(a).toFloat
  def $floor(a: Float): Float = math.floor(a).toFloat
  def $log(a: Float): Float = math.log(a).toFloat
  def $log10(a: Float): Float = math.log10(a).toFloat
  def $max(a: Float, b: Float): Float = math.max(a, b)
  def $min(a: Float, b: Float): Float = math.min(a, b)
  def $pi(): Float = math.Pi.toFloat
  def $pow(a: Float, b: Float): Float = math.pow(a, b).toFloat
  def $round(a: Float): Float = math.round(a)
  def $sin(a: Float): Float = math.sin(a).toFloat
  def $sqrt(a: Float): Float = math.sqrt(a).toFloat
  def $tan(a: Float): Float = math.tan(a).toFloat
  def $tanh(a: Float): Float = math.tanh(a).toFloat
}

