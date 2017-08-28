package carldata.sf

/**
  * Sandbox for testing implementation.
  */
object Main {

  def main(args: Array[String]): Unit = {
    val code =
      """
        |def main(a: Number, b: Number): Number = a+b
      """.stripMargin
    val result = Compiler.compile(code, Seq()).flatMap { ast =>
      Interpreter(ast).run("main", Seq(2, 2))
    }
    println(result)
  }
}
