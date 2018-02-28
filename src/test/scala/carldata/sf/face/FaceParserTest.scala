package carldata.sf.face

import carldata.sf.compiler.AST._
import org.scalatest._


/**
  * Test FACE parser
  */
class FaceParserTest extends FlatSpec with Matchers {

  "FACE Parser" should "parse simple expression" in {
    val code = "-2+3"
    val result = FaceParser.parse(code)
    val expected = BinaryOpExpr(NumberLiteral(-2), "+", NumberLiteral(3))
    result shouldBe Right(expected)
  }

  it should "parse variables" in {
    val code = "2*a*-3"
    val result = FaceParser.parse(code)
    val expected = BinaryOpExpr(BinaryOpExpr(NumberLiteral(2), "*", VariableExpr("a")), "*", NumberLiteral(-3))
    result shouldBe Right(expected)
  }

  it should "parse variables 2" in {
    val code = "0.2*A - 3.24*A + 0.5"
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

  it should "parse function" in {
    val code = "2+f()"
    val result = FaceParser.parse(code)
    val expected = BinaryOpExpr(NumberLiteral(2), "+", AppExpr("f", Seq()))
    result shouldBe Right(expected)
  }

  it should "parse function with two params" in {
    val code = "f(2,4)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("f", Seq(NumberLiteral(2), NumberLiteral(4)))
    result shouldBe Right(expected)
  }

  it should "parse nested power operator ^" in {
    val code = "A^2^3"
    val result = FaceParser.parse(code)
    val expected =
      AppExpr("pow", Seq(AppExpr("pow", Seq(VariableExpr("A"), NumberLiteral(2))),NumberLiteral(3)))
    result shouldBe Right(expected)
  }

  it should "parse nested function" in {
    val code = "f(g(2),2+A)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("f", Seq(AppExpr("g", Seq(NumberLiteral(2))), BinaryOpExpr(NumberLiteral(2), "+", VariableExpr("A"))))
    result shouldBe Right(expected)
  }

  it should "parse simple relation: equals" in {
    val code = "if(5==5,0,1)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("if", Seq(RelationExpr(NumberLiteral(5), "==", NumberLiteral(5)), NumberLiteral(0), NumberLiteral(1)))
    result shouldBe Right(expected)
  }

  it should "parse simple relation: less than" in {
    val code = "if(5<5,0,1)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("if", Seq(RelationExpr(NumberLiteral(5), "<", NumberLiteral(5)), NumberLiteral(0), NumberLiteral(1)))
    result shouldBe Right(expected)
  }


  it should "parse boolean expression ||" in {
    val code = "if(3 < 1 || 0 < 1,0,1)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("if", Seq(BoolOpExpr(RelationExpr(NumberLiteral(3), "<", NumberLiteral(1)), "||", RelationExpr(NumberLiteral(0), "<", NumberLiteral(1))), NumberLiteral(0), NumberLiteral(1)))
    result shouldBe Right(expected)
  }

  it should "parse boolean expression &&" in {
    val code = "if(1 == 1 && 0 < 1,0,1)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("if", Seq(BoolOpExpr(RelationExpr(NumberLiteral(1), "==", NumberLiteral(1)), "&&", RelationExpr(NumberLiteral(0), "<", NumberLiteral(1))), NumberLiteral(0), NumberLiteral(1)))
    result shouldBe Right(expected)
  }

  it should "parse boolean expression: negate relation" in {
    val code = "if(!(0 < 1),0,1)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("if", Seq(NegOpExpr(RelationExpr(NumberLiteral(0), "<", NumberLiteral(1))),NumberLiteral(0),NumberLiteral(1)))
    result shouldBe Right(expected)
  }

  it should "parse boolean expression: negate variable" in {
    val code = "if(!A && B, 1, 0)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("if", Seq(BoolOpExpr(NegOpExpr(VariableExpr("A")),"&&",VariableExpr("B")),NumberLiteral(1),NumberLiteral(0)))
    result shouldBe Right(expected)
  }

  it should "parse expression with null" in {
    val code = "if(A == NULL ,0,A)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("if", Seq(RelationExpr(VariableExpr("A"), "==", NumberLiteral(Double.NaN)), NumberLiteral(0), VariableExpr("A")))
    result.toString shouldBe Right(expected).toString
  }

  it should "parse expression with number in .x format" in {
    val code = "if(A == .05 ,0,A)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("if", Seq(RelationExpr(VariableExpr("A"), "==", NumberLiteral(0.05)), NumberLiteral(0), VariableExpr("A")))
    result.toString shouldBe Right(expected).toString
  }

  it should "parse expression with negative number in .x format" in {
    val code = "if(A == -.05 ,0,A)"
    val result = FaceParser.parse(code)
    val expected = AppExpr("if", Seq(RelationExpr(VariableExpr("A"), "==", NumberLiteral(-0.05)), NumberLiteral(0), VariableExpr("A")))
    result.toString shouldBe Right(expected).toString
  }

}