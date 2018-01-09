package carldata.sf.face

import carldata.sf.compiler.Parser
import org.scalatest._


/**
  * Test FACE to FlowScript converter
  */
class FaceConverterTest extends FlatSpec with Matchers {

  "FACE Converter" should "convert single variable" in {
    val face = "a"
    val flowScript =
      """
        |def main(a: TimeSeries): TimeSeries = a
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert negative single variable" in {
    val face = "-x"
    val flowScript =
      """
        |def main(x: TimeSeries): TimeSeries = -x
      """.stripMargin
    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert functions with numbers" in {
    val face = "CIRCLE(1,2)"
    val flowScript =
      """
        |def main(x: TimeSeries): TimeSeries = CIRCLE(1,2)
      """.stripMargin
    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert nested power function" in {
    val face = "x^2^3"
    val flowScript =
      """
        |def main(x: TimeSeries): TimeSeries = pow(pow(x,2),3)
      """.stripMargin
    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert negative statement" in {
    val face = "-(x+3)"
    val flowScript =
      """
        |def main(x: TimeSeries): TimeSeries = -(x+3)
      """.stripMargin
    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert expression with 2 variables" in {
    val face = "2 * a + 4 / b"
    val flowScript =
      """
        |def main(a: TimeSeries, b: TimeSeries): TimeSeries = 2 * a + 4 / b
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert expression with 3 variables" in {
    val face = "a + b * c"
    val flowScript =
      """
        |def main(a: TimeSeries, b: TimeSeries, c: TimeSeries): TimeSeries = a + b * c
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert expression with 4 variables" in {
    val face = "a + b + c * d"
    val flowScript =
      """
        |def main(a: TimeSeries, b: TimeSeries, c: TimeSeries, d: TimeSeries): TimeSeries = a + b + c * d
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert functions" in {
    val face = "CIRCLE(a,1)"
    val flowScript =
      """
        |def main(a: TimeSeries): TimeSeries = CIRCLE(a,1)
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected

  }

  it should "convert nested functions" in {
    val face = "h(g(a-2.41),1)"
    val flowScript =
      """
        |def main(a: TimeSeries): TimeSeries = h(g(a-2.41),1)
      """.stripMargin
    val faceAST = FaceParser.parse(face).right.get
    val result = FaceConverter.convert(faceAST).right.get
    val expected = Parser.parse(flowScript).right.get
    result shouldBe expected

  }

  it should "convert if expression" in {
    val face = "IF(a > 10, 5, 0)"
    val flowScript =
      """
        |def main(a: TimeSeries): TimeSeries = if a > 10 then 5 else 0
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert nested if expression" in {
    val face = "IF(a > 1 && a < 10, 5,0)"
    val flowScript =
      """
        |def main(a: TimeSeries): TimeSeries = if a > 1 && a < 10 then 5 else 0
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert nested if expression 2" in {
    val face = "IF(a > 1 && a < 10 && b < 10, 5,0)"
    val flowScript =
      """
        |def main(a: TimeSeries, b: TimeSeries): TimeSeries = if a > 1 && a < 10 && b < 10 then 5 else 0
      """.stripMargin
    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert expression with negation" in {
    val face = "if(!(a < 1),0,1)"
    val flowScript =
      """
        |def main(a: TimeSeries): TimeSeries = if !(a < 1) then 0 else 1
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert expression with single number" in {
    val face = "5.4"
    val flowScript =
      """
        |def main(x: TimeSeries): TimeSeries = const(x, 5.4)
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "convert expression with 2 numbers" in {
    val face = "2 + 5"
    val flowScript =
      """
        |def main(x: TimeSeries): TimeSeries = const(x, 2 + 5)
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }
}

