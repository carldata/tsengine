package com.carl.sf.core

import com.carl.sf.Runtime

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
  def $max(a: Float, b: Float): Float = max(a, b)
  def $min(a: Float, b: Float): Float = min(a, b)
}

