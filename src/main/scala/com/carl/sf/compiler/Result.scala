package com.carl.sf.compiler

/**
  * Helper object for definition of Result.
  * Status code which is return by compiler Checkers
  */
object Result {

  sealed trait Result
  object Ok extends Result
  case class Err(reason: String) extends Result
}
