/**
  * Specification of Runtime system.
  * Runtime is used to provide external types and functions during program execution
  * Custom Runtime should:
  *   - Provide custom types which extends TypeValue interface
  *   - Provide script with function and types definition
  *   - override valueFromJavaObject for each ValueType
  */
package com.carl.sf

import com.carl.sf.Runtime._

object Runtime {

  trait Value {
    val toJavaObject: Object
  }

  // Hardcoded type definition
  case object UnitValue extends Value {
    override val toJavaObject: Object = Unit
  }
  case class NumberValue(v: Float) extends Value {
    override val toJavaObject: java.lang.Float = v
  }
  case class StringValue(str: String) extends Value {
    override val toJavaObject: String = str
  }
  case class BoolValue(v: Boolean) extends Value {
    override val toJavaObject: java.lang.Boolean = v
  }

}

trait Runtime {

  val header: String

  // Convert Java object to Value
  // Basic implementation do it for Hardcoded types
  def valueFromJavaObject(x: Object): Value = {
    x match {
      case v: java.lang.Float => NumberValue(v)
      case str: String => StringValue(str)
      case b: java.lang.Boolean => BoolValue(b)
      case _ => UnitValue
    }
  }


  def executeFunction(name: String, params: Seq[Value]): Value = {
    val fname = "$" + name
    val args = params.map(_.toJavaObject)
    val method = getClass.getMethods.filter(_.getName.contains(fname)).head
    valueFromJavaObject(method.invoke(this, args: _*))
  }

}
