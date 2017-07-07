package com.carl.sf.compiler

import com.carl.sf.compiler.AST.{FunctionDef, Module, Term, VariableTerm}
import com.carl.sf.compiler.gen.FlowScriptParser._
import com.carl.sf.compiler.gen.{FlowScriptLexer, FlowScriptParser}
import org.antlr.v4.runtime._

import scala.collection.JavaConverters._

/**
  * Script parser.
  * This parser uses ANTLR4 generated Parser and Lexer.
  * What this object adds is conversion of ANTLR AST into FlowScript AST.
  */
object Parser {

  def parse(code: String): Either[String, Module] = {
    val input = CharStreams.fromString(code)
    val lexer = new FlowScriptLexer(input)
    val tokens = new CommonTokenStream(lexer)
    val parser = new FlowScriptParser(tokens)
    // Don't show parser error on the console
    parser.removeErrorListeners()
    val errorListener = new SyntaxErrorListener()
    parser.addErrorListener(errorListener)
    // Now we are ready to run ANTLR parser
    val compilationUnit = parser.compilationUnit()
    // Parser succeeded only if error list is empty
    if(errorListener.getErrors.size() > 0)
      Left(errorListener.getErrors.firstElement())
    else
      Right(convertCompilationUnit(compilationUnit))
  }

  /** Convert ANTLR Context into AST Module node */
  def convertCompilationUnit(ctx: CompilationUnitContext): Module = {
    val moduleName = ctx.moduleDeclaration().Identifier().getText
    val funDecl = convertFunDef(ctx.functionDefinition())
    Module(moduleName, funDecl)
  }

  /** Convert ANTLR Context into Function Definition node */
  def convertFunDef(ctx: FunctionDefinitionContext): FunctionDef = {
    val funName = ctx.Identifier().getText
    val params = if(ctx.paramList() == null) {
      Seq()
    } else {
      ctx.paramList().param().asScala.map(pctx => pctx.Identifier().getText)
    }
    val body = convertTerm(ctx.term())
    FunctionDef(funName, params, body)

  }

  /** Convert ANTLR Context into Term node */
  def convertTerm(ctx: TermContext): Term = {
    VariableTerm(ctx.variableTerm().Identifier().getText)
  }

}
