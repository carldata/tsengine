package com.carl.sf.compiler

import com.carl.sf.compiler.AST.FunctionDef

/**
  * Executable code
  */
object Executable {

  case class ExecCode(functions: Seq[FunctionDef])
}
