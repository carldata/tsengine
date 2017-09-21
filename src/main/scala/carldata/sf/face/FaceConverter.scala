package carldata.sf.face

import carldata.sf.compiler.AST.{Expression, Module}
import carldata.sf.compiler.Parser

/**
  * Convert FACE AST to FlowScript compatible AST
  */
object FaceConverter {

  val template: String =
    """
      |def f(a: Number): Number = a
      |def main(ts: TimeSeries): TimeSeries = map(ts, f)
    """.stripMargin
  val templateAst: Module = Parser.parse(template).getOrElse(Module(Seq(), Seq()))

  def convert(face: Expression): Module = {
    templateAst
  }
}
