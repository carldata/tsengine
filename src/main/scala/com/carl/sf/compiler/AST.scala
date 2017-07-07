package com.carl.sf.compiler

/**
  * Script Abstract Syntax Tree
  */
object AST {

  type MathOp = String

  case class Module(name: String, funDecl: FunDecl)
  case class FunDecl(name: String, params: Seq[String], body: Expr)
  case class Assign(varName: String, expr: Expr)
  sealed trait Expr
  case class MathExpr(expr1: Expr, expr2: Expr, op: MathOp) extends Expr
  case class FunExpr(identifier: String, params: Seq[Expr]) extends Expr
  case class VarExpr(name: String) extends Expr
  case class StringExpr(value: String) extends Expr
  case class NumberExpr(value: Double) extends Expr


  // Write AST as source code
  def prettyPrint(m: Module): String = {
    val fstr = prettyPrint(m.funDecl)

    "module %s\n%s".format(m.name, fstr)
  }

  def prettyPrint(funDecl: FunDecl): String = {
    val params = funDecl.params.mkString(",")
    val body = prettyPrint(funDecl.body)
    "def %s(%s) = %s\n".format(funDecl.name, params, body)
  }

  def prettyPrint(expr: Expr): String = {
    expr match {
      case MathExpr(e1, e2, op) => prettyPrint(e1) + op + prettyPrint(e2)
      case FunExpr(name, params) => "def " + name + "(%s)".format(params.map(prettyPrint).mkString(","))
      case VarExpr(name) => name
      case StringExpr(str) => '"' + str + '"'
      case NumberExpr(v) => v.toString
    }
  }
}
