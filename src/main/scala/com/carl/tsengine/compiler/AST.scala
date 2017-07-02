package com.carl.tsengine.compiler

/**
  * Script Abstract Syntax Tree
  */
object AST {

  type MathOp = String

  case class Script(let: Seq[Assign], output: Expr)
  case class Assign(varName: String, expr: Expr)
  sealed trait Expr
  case class MathExpr(expr1: Expr, expr2: Expr, op: MathOp) extends Expr
  case class FunExpr(identifier: String, params: Seq[Expr]) extends Expr
  case class StringExpr(value: String) extends Expr
  case class NumberExpr(value: Double) extends Expr


}
