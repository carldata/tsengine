package com.carl.tsengine.compiler

import com.carl.tsengine.compiler.AST._
import org.scalatest._


class ParserTest extends FlatSpec with Matchers {

  "Parser" should "compile simple function expression" in {
    val code =
      """
        |module Test1
        |
        |def my_fun(a, xs) = a
      """.stripMargin
    val ast = Parser.parse(code)
    ast shouldBe
      Module("Test1",
        FunDecl("my_fun", Seq("a", "xs"),
          FunBody(Seq(), VarExpr("a"))))
  }

}