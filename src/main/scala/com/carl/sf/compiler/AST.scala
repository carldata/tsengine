package com.carl.sf.compiler

/**
  * Script Abstract Syntax Tree
  */
object AST {

  type MathOp = String

  case class Module(name: String, funDecl: FunctionDef)
  case class FunctionDef(name: String, params: Seq[FunParam], typeName: String, body: Expression)
  case class FunParam(name: String, typeName: String)
  sealed trait Expression
  case class AppExpr(name: String, params: Seq[Expression]) extends Expression
  case class VariableExpr(name: String) extends Expression


  // Write AST as source code
  def prettyPrint(m: Module): String = {
    val fstr = prettyPrint(m.funDecl)

    "module %s\n%s".format(m.name, fstr)
  }

  def prettyPrint(funDef: FunctionDef): String = {
    val params = funDef.params.map(prettyPrint).mkString(",")
    val body = prettyPrint(funDef.body)
    "def %s(%s):%s = %s\n".format(funDef.name, params, funDef.typeName, body)
  }

  def prettyPrint(param: FunParam): String = {
    param.name + ": " + param.typeName
  }

  def prettyPrint(expr: Expression): String = {
    expr match {
      case AppExpr(name, params) => name + "(%s)".format(params.map(prettyPrint).mkString(","))
      case VariableExpr(name) => name
    }
  }
}
