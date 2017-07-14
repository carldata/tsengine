package com.carl.sf.compiler

import com.carl.sf.compiler.AST._
import com.carl.sf.compiler.Result.{Err, Ok, Result}
import com.carl.sf.compiler.SymbolChecker.checkFunDef


/** Symbol table helper for Type Checker */
class TypeTable private(symbolType: Map[String, String], parent: Option[TypeTable]){

  def this() = this(Map(), None)


  /** Add scope to the current table */
  def addScope(): TypeTable = {
    new TypeTable(Map(), Some(this))
  }

  /** If symbol doesn't have type that it is treated as polymorphic */
  def symbolType(x: String): Option[String] = {
    symbolType.get(x).orElse(parent.flatMap(_.symbolType(x)))
  }

  /** Add symbol to the current scope */
  def addType(symbol: String, typeName: String): TypeTable = {
    new TypeTable(symbolType + (symbol -> typeName), parent)
  }

}


/**
  * Check Type correctness.
  * Type checker add missing Type information.
  */
object TypeChecker {

  /** Check for errors in the module. Returns None if no error was found */
  def check(module: Module): Either[String, Module] = {
    val tt = module.externalFun.foldLeft(new TypeTable()) {(t, x) =>
      t.addType(x.name, x.typeName)
    }
    module.funDecl
      .map(f => checkFunction(f, tt))
      .foldLeft[Either[String, Seq[FunctionDef]]](Right(Seq())) {(e, f) =>
        e.flatMap(xs => f.map(x => xs :+ x))
      }
      .map(fs => Module(module.name, module.externalFun, fs))
  }

  /** Update symbol table and at the same time look for duplicate variables */
  def checkFunction(f: FunctionDef, table: TypeTable): Either[String, FunctionDef] = {
    // Add param types to the Type Table.
    val table2 = f.params.foldLeft(table)((t, x) => t.addType(x.name, x.typeName))
    // Compare expression type with function return type. Also update AST with type information if necessary
    val t1 = exprType(f.body, table2)
    if(f.typeName == t1) {
      Right(f)
    } else {
      Left("Wrong return type for function %s\n Expected: %s\nGot: %s\n".format(
        f.name, f.typeName, t1))
    }
  }

  /** Deduce Expression type.
    * Return Error or expression type
    */
  def exprType(expr: Expression, table: TypeTable): String = {
    expr match {
      case VariableExpr(name) => table.symbolType(name).getOrElse("T")
      case AppExpr(name, _) => table.symbolType(name).getOrElse("T")
      case StringLiteral(_) => "String"
    }
  }
}