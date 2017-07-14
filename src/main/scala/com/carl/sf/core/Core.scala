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
      |external def if(p: Bool, a: T, b: T): T
      |external def max(a: Number, b: Number): Number
      |external def min(a: Number, b: Number): Number
    """.stripMargin

  // Function definition
  def $if[T](p: Boolean, e1: T, e2: T): T = if(p) e1 else e2
  def $max(a: Float, b: Float): Float = max(a, b)
  def $min(a: Float, b: Float): Float = min(a, b)
}

