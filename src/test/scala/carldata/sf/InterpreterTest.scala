package carldata.sf

import carldata.sf.Runtime.{BoolValue, NumberValue, StringValue}
import carldata.sf.core.Math
import org.scalatest._


/**
  * Mostly tests for Semantic Analysis module
  */
class InterpreterTest extends FlatSpec with Matchers {

  "Interpreter" should "not run program with wrong number of parameters" in {
    val code =
      """
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq())
    }
    result.isRight shouldBe false
  }

  it should "run valid program" in {
    val code =
      """
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(1), NumberValue(2)))
    }
    result.isRight shouldBe true
  }

    it should "return value of the parameter" in {
    val code =
      """
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(1), NumberValue(2)))
    }
    result shouldBe Right(NumberValue(1))
  }

  it should "call external function" in {
    val code =
      """
        |external def min(a: Number, b: Number): Number
        |
        |def main(a: Number, b: Number): Number = min(a, b)
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(11), NumberValue(2)))
    }
    result shouldBe Right(NumberValue(2))
  }

  it should "return value of the string literal" in {
    val code =
      """
        |def main(): String = 'hello'
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq())
    }
    result shouldBe Right(StringValue("hello"))
  }

  it should "return value of the number literal" in {
    val code =
      """
        |def main(): Number = 0.23
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq())
    }
    result shouldBe Right(NumberValue(0.23f))
  }

  it should "return value of the bool literal" in {
    val code =
      """
        |def main(): Bool = False
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq())
    }
    result shouldBe Right(BoolValue(false))
  }

  it should "return true for relation" in {
    val code =
      """
        |def main(a: Number, b: Number): Bool = a == b
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(2), NumberValue(2)))
    }
    result shouldBe Right(BoolValue(true))
  }

  it should "return false for relation" in {
    val code =
      """
        |def main(a: Number, b: Number): Bool = a != b
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(2), NumberValue(2)))
    }
    result shouldBe Right(BoolValue(false))
  }

  it should "execute other function defined in the script" in {
    val code =
      """
        |def main(a: Number, b: Number): Bool = a != b
        |def test(): Bool = main(1, 2)
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("test", Seq())
    }
    result shouldBe Right(BoolValue(true))
  }

  it should "execute math expression" in {
    val code =
      """
        |def main(a: Number, b: Number): Bool = a+b > a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(2), NumberValue(2)))
    }
    result shouldBe Right(BoolValue(true))
  }

  it should "calculate a+b*2" in {
    val code =
      """
        |def main(a: Number, b: Number): Number = a+b*2
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(1), NumberValue(3)))
    }
    result shouldBe Right(NumberValue(7))
  }

  it should "calculate 2*b+a" in {
    val code =
      """
        |def main(a: Number, b: Number): Number = 2*b+a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(1), NumberValue(3)))
    }
    result shouldBe Right(NumberValue(7))
  }

  it should "calculate 2*(b+a)" in {
    val code =
      """
        |def main(a: Number, b: Number): Number = 2*(b+a)
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(1), NumberValue(3)))
    }
    result shouldBe Right(NumberValue(8))
  }

  it should "calculate -a*2" in {
    val code =
      """
        |def main(a: Number): Number = -a*2
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(3)))
    }
    result shouldBe Right(NumberValue(-6))
  }

  it should "calculate log" in {
    val code =
      """
        |def main(a: Number): Number = log10(a)
      """.stripMargin
    val result = Compiler.compile(code, Seq(Math.header)).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(1000)))
    }
    result shouldBe Right(NumberValue(3))
  }

  it should "calculate && and || expression" in {
    val code =
      """
        |def main(a: Number, b: Bool): Bool = (a == 10) && (False || !b)
      """.stripMargin
    val result = Compiler.compile(code, Seq(Math.header)).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(10), BoolValue(false)))
    }
    result shouldBe Right(BoolValue(true))
  }

  it should "calculate if-then-else" in {
    val code =
      """
        |def main(a: Number, b: String): String = if a > 10 then "ok" else b
      """.stripMargin
    val result = Compiler.compile(code, Seq(Math.header)).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(12), StringValue("not ok")))
    }
    result shouldBe Right(StringValue("ok"))
  }

  it should "check let-in" in {
    val code =
      """
        |def main(a: Number): Number =
        | let
        |   x = 3
        |   y = x + x
        | in
        |   a*y
      """.stripMargin
    val result = Compiler.compile(code, Seq(Math.header)).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(5)))
    }
    result shouldBe Right(NumberValue(30))
  }

  it should "show runtime error message" in {
    val code =
      """
        |external def test(a: Number): Number
        |
        |def main(a: Number): Number = test(a)
      """.stripMargin
    val result = Compiler.compile(code, Seq(Math.header)).flatMap { ast =>
      new Interpreter(ast, new Math()).run("main", Seq(NumberValue(12)))
    }
    result.left.getOrElse("").substring(0, 7) shouldBe "Runtime"
  }

}