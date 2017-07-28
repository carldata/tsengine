package carldata.sf.compiler

import carldata.sf.compiler.AST.Module
import carldata.sf.compiler.Executable.ExecCode

/**
  * Generate executable code from AST
  */
object CodeGenerator {

  def generate(ast: Module): ExecCode = {
    ExecCode(ast.funDecl)
  }
}
