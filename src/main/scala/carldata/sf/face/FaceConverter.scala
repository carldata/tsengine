package carldata.sf.face

import carldata.sf.compiler.AST._

/**
  * Convert FACE AST to FlowScript compatible AST
  */
object FaceConverter {

  def convert(face: Expression): Either[String, Module] = {
    val v = freeVariable(face)
    val main = mkMain(v)
    if (main.isRight)
      Right(Module(Seq(), Seq(mkFunction(face, v), main.right.get)))
    else Left(main.left.get)
  }

  def freeVariable(e: Expression): Set[String] = {
    e match {
      case v: VariableExpr => Set(v.name)
      case b: BinaryOpExpr => freeVariable(b.e1) ++ freeVariable(b.e2)
      case a: AppExpr => a.params.flatMap(x=>freeVariable(x)).toSet
      case _ => Set()
    }
  }

  def mkFunction(e: Expression, s: Set[String]): FunctionDef = {
    FunctionDef("f", s.map(x => FunParam(x, ValueType("Number"))).toList, ValueType("Number"), FunctionBody(Seq.empty, e))
  }

  def mkMain(s: Set[String]): Either[String, FunctionDef] = {
    if (s.isEmpty || s.size > 4) {
      Left("Wrong number of parameters")
    }
    else {
      val e: Expression = s.size match {
        case 1 => AppExpr("map", List(VariableExpr(s.head), VariableExpr("f")))
        case 2 => AppExpr("join_with", s.map(x => VariableExpr(x)).toList :+ VariableExpr("f"))
        case 3 => AppExpr("join_with3", s.map(x => VariableExpr(x)).toList :+ VariableExpr("f"))
        case 4 => AppExpr("join_with4", s.map(x => VariableExpr(x)).toList :+ VariableExpr("f"))
      }
      Right(FunctionDef("main", s.map(x => FunParam(x, ValueType("TimeSeries"))).toList, ValueType("TimeSeries"), FunctionBody(Seq.empty, e)))
    }
  }
}
