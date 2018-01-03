package carldata.sf.compiler

import carldata.sf.compiler.AST._


/**
  * Check Type correctness.
  * This implementation uses multiple passes. One for each type check.
  * This is made for clarity event if it is not optimal.
  */
object TypeChecker {

  /** Type information about function */
  case class Function(name: String, returnType: TypeDecl, params: Seq[FunParam]) {
    /** Get type of the function */
    def funType: TypeDecl = FunType(params.map(_.typeName), returnType)
  }

  case class FunParam(name: String, typeName: TypeDecl)

  /** Symbol table helper for Type Checker */
  private class Environment(symbols: Map[String, TypeDecl], functions: Map[String, Function]) {

    def this() = this(Map(), Map())

    /** Return symbol type if defined */
    def getSymbolType(x: String): Option[TypeDecl] = symbols.get(x).orElse(functions.get(x).map(_.funType))

    /** Add symbol to the current scope */
    def addSymbol(symbol: String, typeName: TypeDecl): Environment = {
      new Environment(symbols + (symbol -> typeName), functions)
    }

    def addFunction(fs: Seq[Function]): Environment = {
      val fs2 = fs.map(f => f.name -> f)
      new Environment(symbols, functions ++ fs2)
    }

    /** Return function if defined */
    def getFunction(x: String): Option[Function] = functions.get(x)
  }

  /**
    * Check for errors in the module. Returns Right if no error was found
    */
  def check(module: Module): Either[String, Module] = {
    val env = initEnvironment(module)
    // Type check all function declarations
    module.funDecl.map(f => checkFunctionDef(f, env))
      .foldLeft[Either[String, Seq[FunctionDef]]](Right(Seq())) { (e, f) =>
      e.flatMap(xs => f.map(x => xs :+ x))
    }
      .map(fs => Module(module.externalFun, fs))
  }

  /** Create environment and add top level declarations to it */
  private def initEnvironment(module: Module): Environment = {
    val efs = module.externalFun.map { f =>
      Function(f.name, f.typeName, f.params.map(p => FunParam(p.name, p.typeName)))
    }
    val fds = module.funDecl.map { f =>
      Function(f.name, f.typeName, f.params.map(p => FunParam(p.name, p.typeName)))
    }
    new Environment()
      .addFunction(efs)
      .addFunction(fds)
  }

  /** Update symbol table and at the same time look for duplicate variables */
  private def checkFunctionDef(f: FunctionDef, table: Environment): Either[String, FunctionDef] = {
    // Add param types to the Type Table.
    val table2 = f.params.foldLeft(table)((t, x) => t.addSymbol(x.name, x.typeName))
    // Compare expression type with function return type. Also update AST with type information if necessary
    val t1 = checkFunctionBody(f.body, table2)
    if (t1 == Right(f.typeName)) {
      Right(f)
    } else {
      Left("Wrong return type for function %s\n Expected: %s\nGot: %s\n".format(
        f.name, f.typeName, t1))
    }
  }

  /** Check function body */
  def checkFunctionBody(body: FunctionBody, env: Environment): Either[String, TypeDecl] = {
    body.assignments.foldLeft[Either[String, Environment]](Right(env)) { (e, x) =>
      e.flatMap(t => checkExpr(x.expr, t).map(t0 => t.addSymbol(x.varName, t0)))
    } match {
      case Left(reason) => Left(reason)
      case Right(env2) => checkExpr(body.expr, env2)
    }
  }

  /** Check expression. Return error message or expression type */
  private def checkExpr(expr: Expression, env: Environment): Either[String, TypeDecl] = {

    def numericType(t: TypeDecl) = t == NumberType || t == SeriesType

    expr match {
      case NumberLiteral(_) => Right(NumberType)
      case StringLiteral(_) => Right(StringType)

      case MinusOpExpr(e) =>
        if (checkExpr(e, env) == Right(NumberType)) {
          Right(NumberType)
        } else {
          Left("Type error for operation: -")
        }

      case BinaryOpExpr(e1, op, e2) =>
        val t1 = checkExpr(e1, env).right.getOrElse(SeriesType)
        val t2 = checkExpr(e2, env).right.getOrElse(SeriesType)
        if (numericType(t1) && numericType(t2)) {
          if(t1 == SeriesType || t2 == SeriesType) Right(SeriesType)
          else Right(NumberType)
        } else {
          Left("Type error for operation: " + op)
        }

      case NegOpExpr(e) =>
        if (checkExpr(e, env) == Right(SeriesType)) {
          Right(SeriesType)
        } else {
          Left("Type error for operation: !")
        }

      case BoolOpExpr(e1, op, e2) =>
        val t1 = checkExpr(e1, env).right.getOrElse(SeriesType)
        val t2 = checkExpr(e2, env).right.getOrElse(SeriesType)
        if (t1 == SeriesType && t2  == SeriesType) {
          Right(SeriesType)
        } else {
          Left("Type error for relation: " + op)
        }

      case RelationExpr(e1, op, e2) =>
        val t1 = checkExpr(e1, env).right.getOrElse(SeriesType)
        val t2 = checkExpr(e2, env).right.getOrElse(SeriesType)
        if (t1 == NumberType && t2  == NumberType) {
          Right(SeriesType)
        } else if (t1 == SeriesType && t2 == NumberType) {
          Right(SeriesType)
        } else if (t1 == NumberType && t2 == SeriesType) {
          Right(SeriesType)
        } else {
          Left("Type error for relation: " + op)
        }

      case VariableExpr(name) =>
        env.getSymbolType(name).toRight("variable type not defined: " + name)

      case IfExpr(e1, e2, e3) =>
        val c1 = checkExpr(e1, env)
        val c2 = checkExpr(e2, env)
        val c3 = checkExpr(e3, env)
        if (c1 == Right(SeriesType) && c2.isRight && c2 == c3) {
          c2
        } else if (c1 == Right(SeriesType) && c2 == Right(NumberType) && c2 == c3) {
          c1
        } else {
          Left("Type error for operation in if-then-else: " + printExpr(IfExpr(e1, e2, e3)))
        }

      case AppExpr(name, params) =>
        val xs = params.map(x => checkExpr(x, env).getOrElse(SeriesType))
        env.getFunction(name)
          .flatMap(f => if (checkFunctionParams(xs, f.params)) Some(f) else None)
          .map(_.returnType)
          .toRight("function type error: " + name)
    }
  }

  def checkFunctionParams(params1: Seq[TypeDecl], params2: Seq[FunParam]): Boolean = {
    params1 == params2.map(_.typeName)
  }

}