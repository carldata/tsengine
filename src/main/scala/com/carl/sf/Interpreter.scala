package com.carl.sf

import com.carl.sf.compiler.AST._
import com.carl.sf.runtime.Core
import com.carl.sf.runtime.Core.{UnitValue, Value}

import scala.util.Try


/**
  * Run Script with given parameters
  */
object Interpreter {

  /**
    * The runtime return either error string or computed value.
    * Before running the following conditions have to be met:
    *   - The program can only be run if number of provided parameters
    *     matches number of parameters in function declaration
    */
  def run(module: Module, params: Seq[Value]): Either[String, Value] = {
    Try {
      if (params.size == module.funDecl.params.size) {
        val symbolMemory = module.funDecl.params.zip(params).map(x => x._1.name -> x._2).toMap
        Right(execExpr(module.funDecl.body, symbolMemory))
      } else {
        Left("Wrong number of parameters")
      }
    }.getOrElse(Left("Can't execute module: %s".format(module.name)))
  }

  /** Execute node with the function declaration */
  def execExpr(expr: Expression, symbolMemory: Map[String, Value]): Value = {
    expr match {
      case VariableExpr(name) => symbolMemory.getOrElse(name, UnitValue)
      case AppExpr(name, params) =>
        val xs = params.map(x => execExpr(x, symbolMemory))
        Core.executeFunction(name, xs)
    }
  }

}
