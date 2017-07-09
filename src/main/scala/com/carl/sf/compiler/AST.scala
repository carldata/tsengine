package com.carl.sf.compiler

/**
  * Script Abstract Syntax Tree
  */
object AST {

  type MathOp = String

  case class Module(name: String, funDecl: FunctionDef)
  case class FunctionDef(name: String, params: Seq[String], body: Expression)
  sealed trait Expression
//  case class AppExpr(name: String, params: Seq[Expression]) extends Expression
  case class VariableExpr(name: String) extends Expression


  // Write AST as source code
  def prettyPrint(m: Module): String = {
    val fstr = prettyPrint(m.funDecl)

    "module %s\n%s".format(m.name, fstr)
  }

  def prettyPrint(funDecl: FunctionDef): String = {
    val params = funDecl.params.mkString(",")
    val body = prettyPrint(funDecl.body)
    "def %s(%s) = %s\n".format(funDecl.name, params, body)
  }

  def prettyPrint(expr: Expression): String = {
    expr match {
//      case AppExpr(name, params) => "def " + name + "(%s)".format(params.map(prettyPrint).mkString(","))
      case VariableExpr(name) => name
    }
  }
}
