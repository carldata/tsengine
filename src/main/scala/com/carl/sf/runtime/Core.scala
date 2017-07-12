package com.carl.sf.runtime

import scala.math._

/**
  * Core functions and types which can be accessed from the script
  */
object Core {

  sealed trait Value {
    val toValue: Object
  }
  case object UnitValue extends Value {
    override val toValue: Object = Unit
  }
  case class NumberValue(v: Float) extends Value {
    override val toValue: java.lang.Float = v
  }
  case class StringValue(str: String) extends Value {
    override val toValue: String = str
  }

  def $if[T](p: Boolean, e1: T, e2: T): T = if(p) e1 else e2
  def $max(a: Float, b: Float): Float = max(a, b)
  def $min(a: Float, b: Float): Float = min(a, b)

  def $test(a: String): String = a

  def executeFunction(name: String, params: Seq[Value]): Value = {
    val fname = "$" + name
    val args = params.map(_.toValue)
    val method = getClass.getMethods.filter(_.getName.contains(fname)).head
    method.invoke(this, args: _*) match {
      case v: java.lang.Float => NumberValue(v)
      case str: String => StringValue(str)
      case x => UnitValue
    }
  }
}
