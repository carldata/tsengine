package carldata.sf.compiler

/**
  * Helper object for definition of Result.
  * Status code which is return by compiler Checkers
  */
object Result {

  sealed trait Result {
    /** Join 2 results */
    def andThen(r2: => Result): Result
  }

  object Ok extends Result {
    override def andThen(r2: => Result): Result = r2
  }

  case class Err(reason: String) extends Result {
    override def andThen(r2: => Result): Result = this
  }
}
