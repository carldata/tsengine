package carldata.sf.face

import carldata.sf.compiler.AST._

/**
  * Convert FACE AST to FlowScript compatible AST
  */
object FaceConverter {

  def convert(face: Expression): Either[String, Module] = {
    val vs = if (face.isInstanceOf[NumberLiteral]) Set("x") else freeVariable(face)
    val expr = convertIf(face)
    mkMain(expr, vs).map(main => mkModule(expr, main, vs))
  }


  def freeVariable(e: Expression): Set[String] = {
    e match {
      case v: VariableExpr => Set(v.name)
      case b: BinaryOpExpr => freeVariable(b.e1) ++ freeVariable(b.e2)
      case l: BoolOpExpr => freeVariable(l.e1) ++ freeVariable(l.e2)
      case r: RelationExpr => freeVariable(r.e1) ++ freeVariable(r.e2)
      case a: AppExpr => a.params.flatMap(x => freeVariable(x)).toSet
      case n: NegOpExpr => freeVariable(n.expr)
      case _ => Set()
    }
  }

  def convertIf(expr: Expression): Expression = {
    expr match {
      case b: BinaryOpExpr => BinaryOpExpr(convertIf(b.e1), b.op, convertIf(b.e2))
      case a: AppExpr =>
        if (a.name.toLowerCase == "if" && a.params.size == 3) IfExpr(a.params.head, a.params(1), a.params(2))
        else AppExpr(a.name, a.params.map(convertIf))
      case e => e
    }
  }

  def mkModule(expr: Expression, main: FunctionDef, vs: Set[String]): Module = {
    expr match {
      case AppExpr(_, _) =>
        Module(Seq(), Seq(mkFunction(expr, vs), main))
      case NumberLiteral(_) =>
        val tsName = main.params.map(_.name).headOption.getOrElse("")
        val body = FunctionBody(Seq(), AppExpr("const", Seq(VariableExpr(tsName), expr)))
        Module(Seq(), Seq(FunctionDef(main.name, main.params, main.typeName, body)))
      case IfExpr(_, _, _) =>
        Module(Seq(), Seq(FunctionDef(main.name, main.params, main.typeName, FunctionBody(Seq(), expr))))
      case _ =>
        Module(Seq(), Seq(main))
    }
  }

  def mkFunction(e: Expression, s: Set[String]): FunctionDef = {
    FunctionDef("f", s.map(x => FunParam(x, NumberType)).toList, NumberType, FunctionBody(Seq.empty, e))
  }

  def mkMain(expr: Expression, s: Set[String]): Either[String, FunctionDef] = {
    if (s.isEmpty) {
      Left("Wrong number of parameters")
    }
    else {
      val e: Expression = expr match {
        case AppExpr(_, _) => AppExpr("map", List(VariableExpr(s.head), VariableExpr("f")))
        case IfExpr(_, _, _) => AppExpr("map", List(VariableExpr(s.head), VariableExpr("f")))
        case NumberLiteral(_) => AppExpr("map", List(VariableExpr(s.head), VariableExpr("f")))
        case _ => expr
      }
      Right(FunctionDef("main", s.map(x => FunParam(x, SeriesType)).toList, SeriesType, FunctionBody(Seq.empty, e)))
    }
  }
}
