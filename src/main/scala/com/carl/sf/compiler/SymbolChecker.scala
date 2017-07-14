package com.carl.sf.compiler

import com.carl.sf.compiler.AST._
import com.carl.sf.compiler.Result.{Err, Ok, Result}

/**
  * Run the following checks
  *   1. Variable not declared
  *   2. Variable redefinition in the same scope
  *   3. Function not declared
  *   4. Function redefinition
  *
  *   Variables and function are in separate scopes.
  */
object SymbolChecker {

  /** Symbol tables for variables and function. */
  case class SymbolTables(varSymbols: SymbolTable, funSymbols: SymbolTable)

  /** Check for errors in the module. */
  def check(module: Module): Either[String, Module] = {
    val fsymbols = module.externalFun.map(_.name).foldLeft(new SymbolTable()) {(t, x) =>
      t.addSymbol(x)
    }
    val st = SymbolTables(new SymbolTable(), fsymbols)
    module.funDecl.map(f => checkFunDef(f, st)).foldLeft[Result](Ok){ (r1, r2) =>
      if(r1 == Ok) r2 else r1
    } match {
      case Err(str) => Left(str)
      case Ok => Right(module)
    }
  }

  /** Check if there are duplicate params and add them to the symbol table */
  def checkFunDef(f: FunctionDef, st: SymbolTables): Result = {
    f.params.foldLeft[Either[String, SymbolTables]](Right(st)) { (e, x) =>
      e.flatMap { t =>
        if (t.varSymbols.checkScope(x.name)) {
          Left("Variable '%s' is already defined in the scope".format(x))
        } else {
          Right(SymbolTables(t.varSymbols.addSymbol(x.name), t.funSymbols))
        }
      }
    } match {
      case Left(err) => Err(err)
      case Right(t) => checkExpr(f.body, t)
    }
  }

  /** Return unit if all symbols can be correctly resolved */
  def checkExpr(expr: Expression, st: SymbolTables): Result = {
    expr match {
      case VariableExpr(x) =>
        if(st.varSymbols.hasSymbol(x)) { Ok } else { Err("Unresolved variable: %s".format(x)) }
      case AppExpr(name, _) =>
        if(st.funSymbols.hasSymbol(name)) { Ok } else { Err("Unresolved function: %s".format(name)) }
      case StringLiteral(_) => Ok
    }
  }
}