package com.carl.sf

import com.carl.sf.compiler.AST.Module


/**
  * Run Script with given parameters
  */
object Runtime {

  sealed trait Value
  case object UnitValue extends Value
  case class IntValue(v: Int) extends Value
  case class StringValue(str: String) extends Value

  /** The runtime return either error string or computed value */
  def run(code: Module, params: Seq[Value]): Either[String, Value] = {
    Left("Not implemented yet!")
  }
}
