package carldata.sf.face

import carldata.sf.compiler.AST._

/**
  * Convert FACE AST to FlowScript compatible AST
  */
object FaceConverter {

  def convert(face: Expression): Module = {
    val v = freeVariable(face)
    Module(Seq(), Seq(mkFunction(face, v), mkMain(v)))
  }

  def freeVariable(e: Expression): Set[String] = {
    e match {
      case v: VariableExpr => Set(v.name)
      case b: BinaryOpExpr => freeVariable(b.e1) ++ freeVariable(b.e2)
      case _ => Set()
    }
  }

  def mkFunction(e: Expression, s: Set[String]): FunctionDef = {
    FunctionDef("f", s.map(x => FunParam(x, ValueType("Number"))).toList, ValueType("Number"), FunctionBody(Seq.empty, e))
  }

  def mkMain(s: Set[String]): FunctionDef = {
    val e: Expression = if (s.size == 1) {
      AppExpr("map", List(VariableExpr(s.head), VariableExpr("f")))
    }
    else AppExpr("join_with", s.map(x => VariableExpr(x)).toList :+ VariableExpr("f"))
    FunctionDef("main", s.map(x => FunParam(x, ValueType("TimeSeries"))).toList, ValueType("TimeSeries"), FunctionBody(Seq.empty, e))
  }

}
