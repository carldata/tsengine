package carldata.sf

import carldata.sf.Runtime.{BoolValue, NumberValue, StringValue}
import carldata.sf.core.Core
import org.scalatest._


/**
  * Mostly tests for Semantic Analysis module
  */
class InterpreterTest extends FlatSpec with Matchers {

  "Interpreter" should "not run program with wrong number of parameters" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq())
    }
    result.isRight shouldBe false
  }

  it should "run valid program" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(1), NumberValue(2)))
    }
    result.isRight shouldBe true
  }

    it should "return value of the parameter" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, xs: Number): Number = a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(1), NumberValue(2)))
    }
    result shouldBe Right(NumberValue(1))
  }

  it should "call external function" in {
    val code =
      """
        |module Test1
        |external def min(a: Number, b: Number): Number
        |
        |def main(a: Number, b: Number): Number = min(a, b)
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(11), NumberValue(2)))
    }
    result shouldBe Right(NumberValue(2))
  }

  it should "return value of the string literal" in {
    val code =
      """
        |module Test1
        |
        |def main(): String = 'hello'
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq())
    }
    result shouldBe Right(StringValue("hello"))
  }

  it should "return value of the number literal" in {
    val code =
      """
        |module Test1
        |
        |def main(): Number = 0.23
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq())
    }
    result shouldBe Right(NumberValue(0.23f))
  }

  it should "return value of the bool literal" in {
    val code =
      """
        |module Test1
        |
        |def main(): Bool = False
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq())
    }
    result shouldBe Right(BoolValue(false))
  }

  it should "return true for relation" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Bool = a == b
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(2), NumberValue(2)))
    }
    result shouldBe Right(BoolValue(true))
  }

  it should "return false for relation" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Bool = a != b
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(2), NumberValue(2)))
    }
    result shouldBe Right(BoolValue(false))
  }

  it should "execute other function defined in the script" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Bool = a != b
        |def test(): Bool = main(1, 2)
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("test", Seq())
    }
    result shouldBe Right(BoolValue(true))
  }

  it should "execute math expression" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Bool = a+b > a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(2), NumberValue(2)))
    }
    result shouldBe Right(BoolValue(true))
  }

  it should "calculate a+b*2" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Number = a+b*2
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(1), NumberValue(3)))
    }
    result shouldBe Right(NumberValue(7))
  }

  it should "calculate 2*b+a" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Number = 2*b+a
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(1), NumberValue(3)))
    }
    result shouldBe Right(NumberValue(7))
  }

  it should "calculate 2*(b+a)" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number, b: Number): Number = 2*(b+a)
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(1), NumberValue(3)))
    }
    result shouldBe Right(NumberValue(8))
  }

  it should "calculate -a*2" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number): Number = -a*2
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(3)))
    }
    result shouldBe Right(NumberValue(-6))
  }

  it should "calculate log" in {
    val code =
      """
        |module Test1
        |
        |def main(a: Number): Number = log10(a)
      """.stripMargin
    val result = Compiler.compile(code, Seq(Core.header)).flatMap { ast =>
      new Interpreter(ast, new Core()).run("main", Seq(NumberValue(1000)))
    }
    result shouldBe Right(NumberValue(3))
  }

}