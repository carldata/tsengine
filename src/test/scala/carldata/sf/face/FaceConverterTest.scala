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
    val result = FaceConverter.convert(faceAST)
    result shouldBe expected
  }

  "FACE Converter" should "convert complex expression" in {
    val face = "2 * a + 4 / b"
    val flowScript =
      """
        |def f(a: Number, b: Number): Number = 2 * a + 4 / b
        |def main(a: TimeSeries, b: TimeSeries): TimeSeries = join_with(a, b, f)
      """.stripMargin

    val faceAST = FaceParser.parse(face).right.get
    val expected = Parser.parse(flowScript).right.get
    val result = FaceConverter.convert(faceAST)
    result shouldBe expected
  }

}

