package com.carl.sf.compiler

/**
  * Script Abstract Syntax Tree
  */
object AST {

  type MathOp = String

  case class Module(name: String, funDecl: FunctionDef)
  case class FunctionDef(name: String, params: Seq[String], body: Term)
//  case class Assign(varName: String, expr: Term)
  sealed trait Term
//  case class ApplicationTerm(name: String, params: Seq[Term]) extends Term
  case class VariableTerm(name: String) extends Term
//  case class StringTerm(value: String) extends Term
//  case class NumberTerm(value: Double) extends Term


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

  def prettyPrint(expr: Term): String = {
    expr match {
//      case ApplicationTerm(name, params) => "def " + name + "(%s)".format(params.map(prettyPrint).mkString(","))
      case VariableTerm(name) => name
//      case StringTerm(str) => '"' + str + '"'
//      case NumberTerm(v) => v.toString
    }
  }
}
