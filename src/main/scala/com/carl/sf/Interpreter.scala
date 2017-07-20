package com.carl.sf

import com.carl.sf.Runtime._
import com.carl.sf.compiler.AST._
import com.carl.sf.compiler.Executable.ExecCode

import scala.util.Try


/**
  * Run Script with given parameters
  */
class Interpreter(exec: ExecCode, runtime: Runtime) {

  /**
    * The runtime return either error string or computed value.
    * Before running the following conditions have to be met:
    *   - The program can only be run if number of provided parameters
    *     matches number of parameters in function declaration
    */
  def run(funName: String, params: Seq[Value]): Either[String, Value] = {
    Try {
      Right(execFunction(funName, params, Map()))
    }.getOrElse(Left("Runtime exception"))
  }

  def execFunction(name: String, params: Seq[Value], symbolMemory: Map[String, Value]): Value = {
    exec.functions.find(f => f.name == name && params.size == f.params.size)
      .map{f =>
        val sm = symbolMemory ++ f.params.zip(params).map(x => x._1.name -> x._2).toMap
        execExpr(f.body, sm)
      }
      .getOrElse(runtime.executeFunction(name, params))
  }

  /** Execute node with the function declaration */
  private def execExpr(expr: Expression, symbolMemory: Map[String, Value]): Value = {
    expr match {
      case MinusOpExpr(e1) => execMinusOpExpr(e1, symbolMemory)
      case BinaryOpExpr(e1, op, e2) => execBinaryOpExpr(e1, op, e2, symbolMemory)
      case RelationExpr(e1, op, e2) => execRelationExpr(e1, op, e2, symbolMemory)
      case VariableExpr(name) => symbolMemory.getOrElse(name, UnitValue)
      case AppExpr(name, params) =>
        val xs = params.map(x => execExpr(x, symbolMemory))
        execFunction(name, xs, symbolMemory)
      case StringLiteral(text) => StringValue(text)
      case NumberLiteral(v) => NumberValue(v)
      case BoolLiteral(v) => BoolValue(v)
    }
  }

  def execMinusOpExpr(e1: Expression, mem: Map[String, Value]): NumberValue = {
    val a = execExpr(e1, mem)
    val v = mkFloat(a)
    NumberValue(-v)
  }

  def execBinaryOpExpr(e1: Expression, op: String, e2: Expression, mem: Map[String, Value]): NumberValue = {
    val a = execExpr(e1, mem)
    val b = execExpr(e2, mem)
    val v = op match {
      case "+" => mkFloat(a) + mkFloat(b)
      case "-" => mkFloat(a) - mkFloat(b)
      case "*"  => mkFloat(a) * mkFloat(b)
      case "/"  => mkFloat(a) / mkFloat(b)
      case err =>
        println("Wrong binary operator: " + err)
        0f
    }
    NumberValue(v)
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
