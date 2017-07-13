package com.carl.sf.core

import com.carl.sf.Runtime
import com.carl.sf.Runtime.Value

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

  // Type definition
  case object UnitValue extends Value {
    override val toJavaObject: Object = Unit
  }
  case class NumberValue(v: Float) extends Value {
    override val toJavaObject: java.lang.Float = v
  }
  case class StringValue(str: String) extends Value {
    override val toJavaObject: String = str
  }

  // Convert Java object to Value
  override def valueFromJavaObject(x: Object): Value = {
    x match {
      case v: java.lang.Float => NumberValue(v)
      case str: String => StringValue(str)
      case _ => UnitValue
    }
  }

  // Function definition
  def $if[T](p: Boolean, e1: T, e2: T): T = if(p) e1 else e2
  def $max(a: Float, b: Float): Float = max(a, b)
  def $min(a: Float, b: Float): Float = min(a, b)
}

