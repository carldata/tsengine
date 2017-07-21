package com.carl.sf.core

import com.carl.sf.Runtime
import com.carl.sf.Runtime.NumberValue

import scala.math._

/**
  * Core functions and types which can be accessed from the script
  */
object Core extends Runtime{

  // Header which will be povided to the compiler
  val header: String =
    """
      |module Core
      |
      |external def max(a: Number, b: Number): Number
      |external def min(a: Number, b: Number): Number
    """.stripMargin

  // Function definition
  def $max(a: NumberValue, b: NumberValue): NumberValue = NumberValue(max(a.v, b.v))
  def $min(a: NumberValue, b: NumberValue): NumberValue = NumberValue(min(a.v, b.v))
}

