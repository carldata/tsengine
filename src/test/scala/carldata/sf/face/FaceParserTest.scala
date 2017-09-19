package carldata.sf.face

import carldata.sf.compiler.AST.{AppExpr, BinaryOpExpr, NumberLiteral, VariableExpr}
import org.scalatest._


/**
  * Test FACE parser
  */
class FaceParserTest extends FlatSpec with Matchers {

  "FACE Parser" should "parse simple expression" in {
    val code = "2+3"
    val result = FaceParser.parse(code)
    val expected = BinaryOpExpr(NumberLiteral(2), "+", NumberLiteral(3))
    result shouldBe Right(expected)
  }

  it should "parse variables" in {
    val code = "2*a*3"
    val result = FaceParser.parse(code)
    val expected = BinaryOpExpr(BinaryOpExpr(NumberLiteral(2), "*", VariableExpr("a")), "*", NumberLiteral(3))
    result shouldBe Right(expected)
  }

  it should "parse variables 2" in {
    val code ="0.2*A - 3.24*A + 0.5"
    val result = FaceParser.parse(code)
    result.isRight shouldBe true
  }

  it should "parse power operator ^" in {
    val code = "2+B*A^3+4"
    val result = FaceParser.parse(code)
    val expected =
      BinaryOpExpr(
        BinaryOpExpr(
          NumberLiteral(2),
          "+",
          BinaryOpExpr(
            VariableExpr("B"),
            "*",
            AppExpr("pow", Seq(VariableExpr("A"), NumberLiteral(3)))
          )
        ),
        "+",
        NumberLiteral(4)
      )
    result shouldBe Right(expected)
  }

}