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


  // Write AST as source code
  def prettyPrint(m: Module): String = {
    val xs = m.externalFun.map(prettyPrint).mkString("")
    val fstr = m.funDecl.map(prettyPrint).mkString("\n")

    "module %s\n%s\n%s".format(m.name, xs, fstr)
  }

  def prettyPrint(f: ExternalFun): String = {
    val params = f.params.map(prettyPrint).mkString(",")
    "external def %s(%s):%s\n".format(f.name, params, f.typeName)
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
