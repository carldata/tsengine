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
        |def f(a: Number): Number = CIRCLE(a,1)
        |def main(a: TimeSeries): TimeSeries = map(a, f)
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
        |def f(a: Number): Number = h(g(a-2.41),1)
        |def main(a: TimeSeries): TimeSeries = map(a, f)
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
    val face = "IF(a > 10 && 0 < 1,5,0)"
    val flowScript =
      """
        |def main(a: TimeSeries): TimeSeries = if a > 10 && 0 < 1 then 5 else 0
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
        |def main(a: TimeSeries): TimeSeries =  if !a < 1 then 0 else 1
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
        |def main(x: TimeSeries): TimeSeries = 5.4
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }
}

