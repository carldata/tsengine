package carldata.sf.compiler

import carldata.sf.compiler.AST.FunctionDef

/**
  * Executable code
  */
object Executable {

  case class ExecCode(functions: Seq[FunctionDef])
}
