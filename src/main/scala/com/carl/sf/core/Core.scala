package com.carl.sf.core

import com.carl.sf.Runtime
import com.carl.sf.Runtime.NumberValue

/**
  * Core functions and types which can be accessed from the script
  */
object Core {
  // Header which will be provided to the compiler
  val header: String =
    """
      |module Core
      |
      |external def exp(a: Number): Number
      |external def max(a: Number, b: Number): Number
      |external def min(a: Number, b: Number): Number
      |external def pow(a: Number, b: Number): Number
      |external def sqrt(a: Number): Number
    """.stripMargin
}

class Core extends Runtime{

  // Function definition
  def $exp(a: NumberValue): NumberValue = NumberValue(math.exp(a.v.toDouble).toFloat)
  def $max(a: NumberValue, b: NumberValue): NumberValue = NumberValue(math.max(a.v, b.v))
  def $min(a: NumberValue, b: NumberValue): NumberValue = NumberValue(math.min(a.v, b.v))
  def $pow(a: NumberValue, b: NumberValue): NumberValue =
    NumberValue(math.pow(a.v.toDouble, b.v.toDouble).toFloat)
  def $sqrt(a: NumberValue): NumberValue = NumberValue(math.sqrt(a.v.toDouble).toFloat)
}

