package com.carl.tsengine.compiler

import com.carl.tsengine.compiler.AST.{FunExpr, ScriptAST}
import org.scalatest._


class ParserTest extends FlatSpec with Matchers {

  "Parser" should "compile simple function expression" in {
    val code =
      """
        | empty_series()
      """.stripMargin
    val ast = Parser.parse(code)
    ast shouldBe ScriptAST(Seq(), FunExpr("empty_series", Seq()))
  }
}