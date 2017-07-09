package com.carl.sf

import com.carl.sf.compiler.AST
import com.carl.sf.compiler.AST.{FunctionDef, Module, Term, VariableTerm}

import scala.collection.mutable


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
      val symbolMemory = module.funDecl.params.zip(params).map(x => x._1 -> x._2).toMap
      execExpr(module.funDecl.body, symbolMemory)
    } else {
      Left("Wrong number of parameters")
    }
  }

  /** Execute node with the function declaration */
  def execExpr(expr: Term, symbolMemory: Map[String, Value]): Either[String, Value] = {
    expr match {
      case VariableTerm(name) => Right(symbolMemory.getOrElse(name, UnitValue))
    }
  }


}
