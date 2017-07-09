package com.carl.sf.compiler

import com.carl.sf.compiler.AST._

/**
  * Run the following checks
  *   1. Variable redefinition in the same scope
  *   2. Variable not declared
  */
object VariableChecker {

  /** Check for errors in the module. Returns None if no error was found */
  def check(module: Module): Either[String, Unit] = {
    val st = new SymbolTable()
    check(module.funDecl, st)
  }

  /** Update symbol table and at the same time look for duplicate variables */
  def check(f: FunctionDef, table: SymbolTable): Either[String, Unit] = {
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

  def check(expr: Expression, table: SymbolTable): Either[String, Unit] = {
    expr match {
      case VariableExpr(x) => if(table.hasSymbol(x)) { Right() } else { Left("Unresolved variable: %s".format(x)) }
    }
  }
}