package com.carl.sf

/**
  * Specification of Runtime system.
  * Runtime is used to provide external types and functions during program execution
  */
object Runtime {

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
}

trait Runtime {
  import com.carl.sf.Runtime._

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
