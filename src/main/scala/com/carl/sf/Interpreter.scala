package com.carl.sf

import com.carl.sf.Runtime._
import com.carl.sf.compiler.AST._

import scala.util.Try


/**
  * Run Script with given parameters
  */
class Interpreter(runtime: Runtime) {

  /**
    * The runtime return either error string or computed value.
    * Before running the following conditions have to be met:
    *   - The program can only be run if number of provided parameters
    *     matches number of parameters in function declaration
    */
  def run(module: Module, funName: String, params: Seq[Value]): Either[String, Value] = {
    Try {
      module.funDecl.find(_.name == funName).map { f =>
        if (params.size == f.params.size) {
          val symbolMemory = f.params.zip(params).map(x => x._1.name -> x._2).toMap
          Right(execExpr(f.body, symbolMemory))
        } else {
          Left("Wrong number of parameters")
        }
      }.getOrElse(Left("Can't find function: %s".format(funName)))
    }.getOrElse(Left("Runtime exception executing module: %s".format(module.name)))
  }

  /** Execute node with the function declaration */
  private def execExpr(expr: Expression, symbolMemory: Map[String, Value]): Value = {
    expr match {
      case RelationExpr(e1, op, e2) => execRelationExpr(e1, op, e2, symbolMemory)
      case VariableExpr(name) => symbolMemory.getOrElse(name, UnitValue)
      case AppExpr(name, params) =>
        val xs = params.map(x => execExpr(x, symbolMemory))
        runtime.executeFunction(name, xs)
      case StringLiteral(text) => StringValue(text)
      case NumberLiteral(v) => NumberValue(v)
      case BoolLiteral(v) => BoolValue(v)
    }
  }

  def execRelationExpr(e1: Expression, op: String, e2: Expression, mem: Map[String, Value]): BoolValue = {
    val a = execExpr(e1, mem)
    val b = execExpr(e2, mem)
    val v = op match {
      case "==" => a == b
      case "!=" => a != b
      case ">"  => mkFloat(a) > mkFloat(b)
      case "<"  => mkFloat(a) < mkFloat(b)
      case ">=" => mkFloat(a) >= mkFloat(b)
      case "<=" => mkFloat(a) < mkFloat(b)
      case err =>
        println("Wrong relation operator: " + err)
        false
    }
    BoolValue(v)
  }

  def mkFloat(a: Value): Float = {
    a match {
      case NumberValue(v) => v
      case BoolValue(v) => if(v) 1f else 0f
      case _ => 0f
    }
  }

}
