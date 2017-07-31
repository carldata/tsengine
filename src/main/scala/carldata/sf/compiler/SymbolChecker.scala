package carldata.sf.compiler

import carldata.sf.compiler.AST._
import carldata.sf.compiler.Result.{Err, Ok, Result}

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
    addSymbolList(module.externalFun.map(_.name), new SymbolTable())
      .flatMap(t => addSymbolList(module.funDecl.map(_.name), t))
      .map(st => SymbolTables(new SymbolTable(), st))
      .flatMap { st =>
        module.funDecl.map(f => checkFunDef(f, st)).foldLeft[Result](Ok) { (r1, r2) =>
          if (r1 == Ok) r2 else r1
        } match {
          case Err(str) => Left(str)
          case Ok => Right(module)
        }
      }
  }

  /** Add list of symbols to the table. Ensure that symbols are unique. */
  def addSymbolList(names: Seq[String], st: SymbolTable): Either[String, SymbolTable] = {
    names.foldLeft[Either[String, SymbolTable]](Right(st)) {(t, x) =>
      t match {
        case Right(table) =>
          if(table.hasSymbol(x)) {
            Left("Symbol %s already defined".format(x))
          } else {
            Right(table.addSymbol(x))
          }
        case err => err
      }
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

  /** Check symbols used in expressions */
  def checkExpr(expr: Expression, st: SymbolTables): Result = {
    expr match {
      case MinusOpExpr(e) => checkExpr(e, st)

      case BinaryOpExpr(e1, _, e2) => checkExpr(e1, st).andThen(checkExpr(e2, st))

      case NegOpExpr(e) => checkExpr(e, st)

      case BoolOpExpr(e1, _, e2) => checkExpr(e1, st).andThen(checkExpr(e2, st))

      case RelationExpr(e1, _, e2) => checkExpr(e1, st).andThen(checkExpr(e2, st))

      case VariableExpr(x) =>
        if(st.varSymbols.hasSymbol(x)) { Ok } else { Err("Unresolved variable: %s".format(x)) }

      case AppExpr(name, params) =>
        val xs = params.map(x => checkExpr(x, st)).filter(_ != Ok)
        if(xs.nonEmpty){
          xs.head
        } else if(st.funSymbols.hasSymbol(name)) {
          Ok
        } else {
          Err("Unresolved function: %s".format(name))
        }

      case StringLiteral(_) => Ok
      case NumberLiteral(_) => Ok
      case BoolLiteral(_) => Ok
    }
  }
}