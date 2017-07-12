package com.carl.sf.runtime

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
  case class IntValue(v: Int) extends Value {
    override val toValue: java.lang.Integer = v
  }
  case class StringValue(str: String) extends Value {
    override val toValue: Object = str
  }

  def $if[T](p: Boolean, e1: T, e2: T): T = if(p) e1 else e2
  def $max(a: Int, b: Int): Int = Math.max(a, b)
  def $min(a: Int, b: Int): Int = Math.min(a, b)

  def $test(a: String): String = a

  def executeFunction(name: String, params: Seq[Value]): Value = {
    val fname = "$" + name
    val args = params.map(_.toValue)
    val method = getClass.getMethods.filter(_.getName.contains(fname)).head
    method.invoke(this, args: _*) match {
      case v: Integer => IntValue(v)
      case str: String =>
        println(str)
        StringValue(str)
      case x =>
        println(x)
        UnitValue
    }
  }
}
