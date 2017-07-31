package carldata.sf

import carldata.sf.Runtime.NumberValue

/**
  * Core functions and types which can be accessed from the script
  */
object Core {
  // Header which will be provided to the compiler
  val header: String =
    """
      |module Core
      |
      |external def cos(a: Number): Number
      |external def exp(a: Number): Number
      |external def log(a: Number): Number
      |external def log10(a: Number): Number
      |external def max(a: Number, b: Number): Number
      |external def min(a: Number, b: Number): Number
      |external def pi(): Number
      |external def pow(a: Number, b: Number): Number
      |external def sin(a: Number): Number
      |external def sqrt(a: Number): Number
      |external def tan(a: Number): Number
      |external def tanh(a: Number): Number
    """.stripMargin
}

class Core extends Runtime{

  // Function definition
  def $cos(a: NumberValue): NumberValue = NumberValue(math.cos(a.v.toDouble).toFloat)
  def $exp(a: NumberValue): NumberValue = NumberValue(math.exp(a.v.toDouble).toFloat)
  def $log(a: NumberValue): NumberValue = NumberValue(math.log(a.v.toDouble).toFloat)
  def $log10(a: NumberValue): NumberValue = NumberValue(math.log10(a.v.toDouble).toFloat)
  def $max(a: NumberValue, b: NumberValue): NumberValue = NumberValue(math.max(a.v, b.v))
  def $min(a: NumberValue, b: NumberValue): NumberValue = NumberValue(math.min(a.v, b.v))
  def $pi(): NumberValue = NumberValue(math.Pi.toFloat)
  def $pow(a: NumberValue, b: NumberValue): NumberValue =
    NumberValue(math.pow(a.v.toDouble, b.v.toDouble).toFloat)
  def $sin(a: NumberValue): NumberValue = NumberValue(math.sin(a.v.toDouble).toFloat)
  def $sqrt(a: NumberValue): NumberValue = NumberValue(math.sqrt(a.v.toDouble).toFloat)
  def $tan(a: NumberValue): NumberValue = NumberValue(math.tan(a.v.toDouble).toFloat)
  def $tanh(a: NumberValue): NumberValue = NumberValue(math.tanh(a.v.toDouble).toFloat)
}

