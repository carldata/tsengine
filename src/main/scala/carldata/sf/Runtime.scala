/**
  * Specification of Runtime system.
  * Runtime is used to provide external types and functions during program execution
  * Custom Runtime should:
  *   - Provide custom types which extends TypeValue interface
  *   - Provide script with function and types definition
  *   - override valueFromJavaObject for each ValueType
  */
package carldata.sf

import carldata.sf.Runtime.Value

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
    getClass.getMethods
      .find(_.getName.contains("$" + name))
      .map(m => m.invoke(this, params: _*).asInstanceOf[Value]) match {
      case Some(value) => value
      case None => throw new NoSuchElementException(name)
    }
  }

}
