package carldata.sf.compiler

/**
  * Script Abstract Syntax Tree
  */
object AST {

  type MathOp = String

  case class Module(name: String, externalFun: Seq[ExternalFun], funDecl: Seq[FunctionDef])
  case class ExternalFun(name: String, params: Seq[FunParam], typeName: String)
  case class FunctionDef(name: String, params: Seq[FunParam], typeName: String, body: FunctionBody)
  case class FunParam(name: String, typeName: String)
  case class FunctionBody(assignments: Seq[Assignment], expr: Expression)
  case class Assignment(varName: String, expr: Expression)
  sealed trait Expression
  case class IfExpr(ifExpr: Expression, thenExpr: Expression, elseExpr: Expression) extends Expression
  case class MinusOpExpr(expr: Expression) extends Expression
  case class BinaryOpExpr(e1: Expression, op: String, e2: Expression) extends Expression
  case class NegOpExpr(expr: Expression) extends Expression
  case class BoolOpExpr(e1: Expression, op: String, e2: Expression) extends Expression
  case class RelationExpr(e1: Expression, op: String, e2: Expression) extends Expression
  case class AppExpr(name: String, params: Seq[Expression]) extends Expression
  case class VariableExpr(name: String) extends Expression
  case class BoolLiteral(b: Boolean) extends Expression
  case class NumberLiteral(v: Float) extends Expression
  case class StringLiteral(text: String) extends Expression


  def mergeModules(m1: Module, m2: Module): Module = {
    Module(m1.name, m1.externalFun ++ m2.externalFun, m1.funDecl ++ m2.funDecl)
  }

  // Write AST as source code
  def printModule(m: Module): String = {
    val xs = m.externalFun.map(printExternalFun).mkString("")
    val fstr = m.funDecl.map(printFunDef).mkString("\n")

    "module %s\n%s\n%s".format(m.name, xs, fstr)
  }

  def printExternalFun(f: ExternalFun): String = {
    val params = f.params.map(printParam).mkString(",")
    "external def %s(%s):%s\n".format(f.name, params, f.typeName)
  }

  def printFunDef(funDef: FunctionDef): String = {
    val params = funDef.params.map(printParam).mkString(",")
    val body = printFunBody(funDef.body)
    "def %s(%s):%s = %s\n".format(funDef.name, params, funDef.typeName, body)
  }

  def printParam(param: FunParam): String = {
    param.name + ": " + param.typeName
  }

  def printFunBody(body: FunctionBody): String = {
    if(body.assignments.nonEmpty){
      "let\n" +
        body.assignments.map(a => printAssign(a) + "\n") +
      "in\n" +
        printExpr(body.expr)
    } else {
      printExpr(body.expr)
    }
  }

  def printAssign(assign: Assignment): String = {
    assign.varName + "=" + printExpr(assign.expr)
  }

  def printExpr(expr: Expression): String = {
    expr match {
      case IfExpr(e1, e2, e3) => "if " + printExpr(e1) + " then " + printExpr(e2) + " else " + printExpr(e3)
      case MinusOpExpr(e) => "-" + printExpr(e)
      case BinaryOpExpr(e1, op, e2) => printExpr(e1) + op + printExpr(e2)
      case NegOpExpr(e) => "!" + printExpr(e)
      case BoolOpExpr(e1, op, e2) => printExpr(e1) + " " + op + " " + printExpr(e2)
      case RelationExpr(e1, op, e2) => printExpr(e1) + op + printExpr(e2)
      case AppExpr(name, params) => name + "(%s)".format(params.map(printExpr).mkString(","))
      case VariableExpr(name) => name
      case StringLiteral(text) => '\'' + text + '\''
      case NumberLiteral(v) => v.toString
      case BoolLiteral(b) => if(b) "True" else "False"
    }
  }
}
