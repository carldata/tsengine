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
        |def f(a: Number): Number = a
        |def main(a: TimeSeries): TimeSeries = map(a, f)
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
        |def f(a: Number, b: Number): Number = 2 * a + 4 / b
        |def main(a: TimeSeries, b: TimeSeries): TimeSeries = join_with(a, b, f)
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
        |def f(a: Number, b: Number, c: Number): Number = a + b * c
        |def main(a: TimeSeries, b: TimeSeries, c: TimeSeries): TimeSeries = join_with3(a, b, c, f)
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
        |def f(a: Number, b: Number, c: Number, d: Number): Number = a + b + c * d
        |def main(a: TimeSeries, b: TimeSeries, c: TimeSeries, d: TimeSeries): TimeSeries = join_with4(a, b, c, d, f)
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST).right.get
    result shouldBe expected
  }

  it should "give error message on too much od params" in {
    val face = "2 * a + 4 / b + c + d + e"
    val flowScript =
      """
        |def f(a: Number, b: Number): Number = 2 * a + 4 / b
        |def main(a: TimeSeries, b: TimeSeries): TimeSeries = join_with(a, b, f)
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    FaceConverter.convert(faceAST).isLeft

  }

}

