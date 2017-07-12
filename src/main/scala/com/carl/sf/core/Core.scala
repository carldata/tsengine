package com.carl.sf.core

import com.carl.sf.Runtime

import scala.math._

/**
  * Core functions and types which can be accessed from the script
  */
object Core extends Runtime{

  def $if[T](p: Boolean, e1: T, e2: T): T = if(p) e1 else e2
  def $max(a: Float, b: Float): Float = max(a, b)
  def $min(a: Float, b: Float): Float = min(a, b)
}
