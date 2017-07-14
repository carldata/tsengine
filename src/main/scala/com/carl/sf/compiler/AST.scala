package com.carl.sf.compiler

/**
  * Script Abstract Syntax Tree
  */
object AST {

  type MathOp = String

  case class Module(name: String, externalFun: Seq[ExternalFun], funDecl: Seq[FunctionDef])
  case class ExternalFun(name: String, params: Seq[FunParam], typeName: String)
  case class FunctionDef(name: String, params: Seq[FunParam], typeName: String, body: Expression)
  case class FunParam(name: String, typeName: String)
  sealed trait Expression
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
    val body = printExpr(funDef.body)
    "def %s(%s):%s = %s\n".format(funDef.name, params, funDef.typeName, body)
  }

  def printParam(param: FunParam): String = {
    param.name + ": " + param.typeName
  }

  def printExpr(expr: Expression): String = {
    expr match {
      case AppExpr(name, params) => name + "(%s)".format(params.map(printExpr).mkString(","))
      case VariableExpr(name) => name
      case StringLiteral(text) => '\'' + text + '\''
      case NumberLiteral(v) => v.toString
      case BoolLiteral(b) => if(b) "True" else "False"
    }
  }
}
