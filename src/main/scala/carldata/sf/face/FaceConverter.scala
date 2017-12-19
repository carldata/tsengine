package carldata.sf.face

import carldata.sf.compiler.AST._

/**
  * Convert FACE AST to FlowScript compatible AST
  */
object FaceConverter {

  def convert(face: Expression): Either[String, Module] = {
    val vs = if (face.isInstanceOf[NumberLiteral]) Set("x") else freeVariable(face)
    val expr = convertIf(face)
    val main = mkMain(expr, vs)
    expr match {
      case AppExpr(_, _) => main.map(f => Module(Seq(), Seq(mkFunction(expr, vs), f)))
      case _ => main.map(f => Module(Seq(), Seq(f)))
    }
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

  def mkFunction(e: Expression, s: Set[String]): FunctionDef = {
    FunctionDef("f", s.map(x => FunParam(x, ValueType("Number"))).toList, ValueType("Number"), FunctionBody(Seq.empty, e))
  }

  def mkMain(expr: Expression, s: Set[String]): Either[String, FunctionDef] = {
    if (s.isEmpty) {
      Left("Wrong number of parameters")
    }
    else {
      val e: Expression = expr match {
        case AppExpr(name, params) => AppExpr("map", List(VariableExpr(s.head), VariableExpr("f")))
        case _ => expr
      }
      Right(FunctionDef("main", s.map(x => FunParam(x, ValueType("TimeSeries"))).toList, ValueType("TimeSeries"), FunctionBody(Seq.empty, e)))
    }
  }
}
