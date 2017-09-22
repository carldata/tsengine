package carldata.sf.face

import carldata.sf.compiler.AST._
import carldata.sf.compiler.Parser

/**
  * Convert FACE AST to FlowScript compatible AST
  */
object FaceConverter {

  def template(p: Seq[String], e: Seq[String]): String =
    """
      |def f(""".stripMargin + p.mkString(",") + """):Number = """ + e.mkString("") + """
      |def main(ts: TimeSeries): TimeSeries = map(ts, f)
    """.stripMargin

  def convert(face: Expression): Module = {
    def stringify(e: Expression): (Seq[String], Seq[String]) = {
      e match {
        case v: VariableExpr => (Seq(v.name + ":Number"), Seq(v.name))
        case n: NumberLiteral => (Seq(), Seq(n.v.toString))
        case b: BinaryOpExpr => {
          val t1 = stringify(b.e1)
          val t2 = stringify(b.e2)
          (Seq((t1._1 ++ t2._1).mkString(",")), Seq(t1._2.mkString, b.op.toString, t2._2.mkString))
        }
        case _ => (Seq(), Seq())
      }
    }

    val paramsAndExpr = stringify(face)
    Parser.parse(template(paramsAndExpr._1, paramsAndExpr._2)).getOrElse(Module(Seq(), Seq()))
  }
}
