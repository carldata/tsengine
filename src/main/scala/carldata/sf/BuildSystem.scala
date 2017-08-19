package carldata.sf

/**
  * Simplify compilation process
  */
object BuildSystem {

  val headers = Seq(core.Math.header)
  val rt = Seq(new core.Math())

  def build(code: String): Either[String, Interpreter] = {
    Compiler.compile(code, headers).map { exec =>
      new Interpreter(exec, rt)
    }
  }

}
