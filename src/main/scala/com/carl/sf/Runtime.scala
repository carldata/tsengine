/**
  * Specification of Runtime system.
  * Runtime is used to provide external types and functions during program execution
  * Custom Runtime should:
  *   - Provide custom types which extends TypeValue interface
  *   - Provide script with function and types definition
  *   - override valueFromJavaObject for each ValueType
  */
package com.carl.sf

import com.carl.sf.Runtime.Value

object Runtime {

  trait Value {
    val toJavaObject: Object
  }
}

trait Runtime {

  val header: String

  def valueFromJavaObject(x: Object): Value

  def executeFunction(name: String, params: Seq[Value]): Value = {
    val fname = "$" + name
    val args = params.map(_.toJavaObject)
    val method = getClass.getMethods.filter(_.getName.contains(fname)).head
    valueFromJavaObject(method.invoke(this, args: _*))
  }

}
