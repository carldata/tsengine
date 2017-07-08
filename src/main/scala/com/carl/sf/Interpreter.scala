package com.carl.sf

import com.carl.sf.compiler.AST.Module


/**
  * Run Script with given parameters
  */
object Interpreter {

  sealed trait Value
  case object UnitValue extends Value
  case class IntValue(v: Int) extends Value
  case class StringValue(str: String) extends Value

  /**
    * The runtime return either error string or computed value.
    * Before running the following conditions have to be met:
    *   - The program can only be run if number of provided parameters
    *     matches number of parameters in function declaration
    */
  def run(module: Module, params: Seq[Value]): Either[String, Value] = {
    if(params.size == module.funDecl.params.size) {
      Right(IntValue(params.size))
    } else {
      Left("Not implemented yet!")
    }
  }
}
