package com.carl.sf.compiler

import com.carl.sf.compiler.AST.Module
import com.carl.sf.compiler.Executable.ExecCode

/**
  * Generate executable code from AST
  */
object CodeGenerator {

  def generate(ast: Module): ExecCode = {
    ExecCode(ast.funDecl)
  }
}
