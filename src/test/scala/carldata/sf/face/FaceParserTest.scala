package carldata.sf.face

import carldata.sf.compiler.AST.{BinaryOpExpr, NumberLiteral, VariableExpr}
import org.scalatest._


/**
  * Test for checking that we can parse specific syntax
  */
class FaceParserTest extends FlatSpec with Matchers {

  "FACE Parser" should "parse simple expression" in {
    val code =
      "2+3"
    val result = FaceParser.parse(code)
    val expected = BinaryOpExpr(NumberLiteral(2), "+", NumberLiteral(3))
    result shouldBe Right(expected)
  }

  it should "parse variables" in {
    val code =
      "2*a"
    val result = FaceParser.parse(code)
    val expected = BinaryOpExpr(NumberLiteral(2), "*", VariableExpr("a"))
    result shouldBe Right(expected)
  }

}