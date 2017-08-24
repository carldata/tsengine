/**
  * Specification of Runtime system.
  * Runtime is used to provide external types and functions during program execution
  * Custom Runtime should:
  *   - Provide custom types which extends TypeValue interface
  *   - Provide script with function and types definition
  *   - override valueFromJavaObject for each ValueType
  */
package carldata.sf

trait Runtime {

  def executeFunction(name: String, params: Seq[Any]): Option[Any] = {
    getClass.getMethods
      .find(_.getName == "$" + name)
      .map(m => m.invoke(this, params.map(_.asInstanceOf[AnyRef]): _*))
  }

}
