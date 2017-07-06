package com.carl.tsengine.compiler

/**
  * Script Abstract Syntax Tree
  */
object AST {

  type MathOp = String

  case class Module(name: String, funDecl: FunDecl)
  case class FunDecl(name: String, params: Seq[String], body: FunBody)
  case class FunBody(let: Seq[Assign], output: Expr)
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

  def prettyPrint(fundecl: FunDecl): String = {
    val body = "a"
    "def %s() = %s\n".format(fundecl.name, body)
  }

  def prettyPrint(str: String): String = '"' + str.toString + '"'
  def prettyPrint(v: NumberExpr): String = v.toString
}
