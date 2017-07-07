package com.carl.sf.compiler

import com.carl.sf.compiler.AST._

/**
  * Semantic Analysis consists of:
  *   1. Variable scope checker
  */
object SemanticAnalyzer {

  def analyze(module: Module): Either[String, Unit] = {
    VariableChecker.check(module)
  }
}

private object VariableChecker {

  /** Check for errors in the module. Returns None if no error was found */
  def check(module: Module): Either[String, Unit] = {
    val st = new SymbolTable()
    check(module.funDecl, st)
  }

  /** Update symbol table and at the same time look for duplicate variables */
  def check(f: FunDecl, table: SymbolTable): Either[String, Unit] = {
    val st = f.params.foldLeft[Either[String, SymbolTable]](Right(table)) { (e, x) =>
      e.flatMap { t =>
        if (t.checkScope(x)) {
          Left("Variable '%s' is already defined in the scope".format(x))
        } else {
          Right(t.addSymbol(x))
        }
      }
    }
    st.flatMap(check(f.body, _))
  }

  def check(expr: Expr, table: SymbolTable): Either[String, Unit] = {
    expr match {
      case MathExpr(e1, e2, op) => Right()
      case FunExpr(name, params) => Right()
      case VarExpr(x) => if(table.hasSymbol(x)) { Right() } else { Left("Unresolved variable: %s".format(x)) }
      case StringExpr(_) => Right()
      case NumberExpr(_) => Right()
    }
  }
}