package carldata.sf

import carldata.sf.Runtime._
import carldata.sf.compiler.AST._
import carldata.sf.compiler.Executable.ExecCode


/**
  * Run Script with given parameters
  */
class Interpreter(exec: ExecCode, runtimes: Seq[Runtime]) {

  /**
    * The runtime return either error string or computed value.
    * Before running the following conditions have to be met:
    *   - The program can only be run if number of provided parameters
    *     matches number of parameters in function declaration
    */
  def run(funName: String, params: Seq[Value]): Either[String, Value] = {
    try {
      Right(execFunction(funName, params, Map()))
    } catch {
      case e: Exception => Left("Runtime exception: " + e);
    }
  }

  def execFunction(name: String, params: Seq[Value], symbolMemory: Map[String, Value]): Value = {
    exec.functions.find(f => f.name == name && params.size == f.params.size)
      .map{f =>
        val sm = symbolMemory ++ f.params.zip(params).map(x => x._1.name -> x._2).toMap
        execBody(f.body, sm)
      }
      .getOrElse(executeExternalFunction(name, params))
  }

  def execBody(body: FunctionBody, sm: Map[String, Value]): Value = {
    val sm2 = body.assignments.foldLeft(sm){ (t, a) =>
      t + (a.varName -> execExpr(a.expr, t))
    }
    execExpr(body.expr, sm2)
  }

  /** Execute node with the function declaration */
  private def execExpr(expr: Expression, symbolMemory: Map[String, Value]): Value = {
    expr match {
      case MinusOpExpr(e1) => execMinusOpExpr(e1, symbolMemory)
      case BinaryOpExpr(e1, op, e2) => execBinaryOpExpr(e1, op, e2, symbolMemory)
      case NegOpExpr(e1) => execNegOpExpr(e1, symbolMemory)
      case BoolOpExpr(e1, op, e2) => execBoolOpExpr(e1, op, e2, symbolMemory)
      case RelationExpr(e1, op, e2) => execRelationExpr(e1, op, e2, symbolMemory)
      case VariableExpr(name) => symbolMemory.getOrElse(name, UnitValue)
      case IfExpr(p, e1, e2) => execIfExpr(p, e1, e2, symbolMemory)
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

  def execNegOpExpr(e1: Expression, mem: Map[String, Value]): BoolValue = {
    val a = execExpr(e1, mem)
    val b = mkBool(a)
    BoolValue(!b)
  }

  def execBoolOpExpr(e1: Expression, op: String, e2: Expression, mem: Map[String, Value]): BoolValue = {
    val a = execExpr(e1, mem)
    val b = execExpr(e2, mem)
    val v = op match {
      case "&&" => mkBool(a) && mkBool(b)
      case "||" => mkBool(a) || mkBool(b)
      case err =>
        println("Wrong boolean operator: " + err)
        false
    }
    BoolValue(v)
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

  def execIfExpr(p: Expression, e1: Expression, e2: Expression, mem: Map[String, Value]): Value = {
    val pred = execExpr(p, mem)
    if(mkBool(pred)) execExpr(e1, mem) else execExpr(e2, mem)
  }

  def mkFloat(a: Value): Float = {
    a match {
      case NumberValue(v) => v
      case BoolValue(v) => if(v) 1f else 0f
      case _ => 0f
    }
  }

  def mkBool(a: Value): Boolean = {
    a match {
      case BoolValue(b) => b
      case _ => false
    }
  }

  def executeExternalFunction(name: String, params: Seq[Value]): Value = {
    runtimes.foldLeft[Option[Value]](None) { (acc, r) =>
      if (acc.isEmpty) r.executeFunction(name, params)
      else acc
    } match {
      case Some(value) => value
      case None => throw new NoSuchElementException(name)
    }
  }

}
