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

  trait Value

  // Hardcoded type definition
  case object UnitValue extends Value
  case class NumberValue(v: Float) extends Value
  case class StringValue(str: String) extends Value
  case class BoolValue(v: Boolean) extends Value

}

trait Runtime {

  def executeFunction(name: String, params: Seq[Value]): Value = {
    val method = getClass.getMethods.filter(_.getName.contains("$"+name)).head
    method.invoke(this, params: _*).asInstanceOf[Value]
  }

}
