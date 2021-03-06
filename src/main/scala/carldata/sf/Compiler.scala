package carldata.sf

import java.time.Duration

import carldata.sf.compiler.AST._
import carldata.sf.compiler.Executable.ExecCode
import carldata.sf.compiler._
import carldata.sf.core._

import scala.collection.mutable.ListBuffer

/**
  * Compiler for FlowScript. It consists of the following phases:
  *  1. Parser and Lexer implemented with the help of ANTLR
  *  2. Semantic Analysis
  *   2.1. Variable Checker
  *   2.2. Type Checker
  *  3. Code generation
  */
object Compiler {

  val headers: Seq[String] = Seq(MathModule.header, DateTimeModule.header, TimeSeriesModule.header,
    HydrologyModule.header, DBModule.header)

  /** Make Exec using default library module */
  def make(code: String): Either[String, ExecCode] = Compiler.compile(code, headers)

  /**
    * Compile given code and zero or more library modules.
    * Library modules are used to add definition of external function and types
    */
  def compile(code: String, libs: Seq[String]): Either[String, ExecCode] = {
    val ast = Parser.parse(code)
    val ast2 = libs.map(Parser.parse).foldLeft(ast)(joinAst)

    ast2.flatMap(SymbolChecker.check)
      .flatMap(TypeChecker.check)
      .map(CodeGenerator.generate)
  }

  /** Helper function for joining parser results */
  def joinAst(ast1: Either[String, Module], ast2: Either[String, Module]): Either[String, Module] = {
    ast1.flatMap(a => ast2.map(b => AST.mergeModules(a, b)))
  }

  /** Find longest duration in AST */
  def getDuration(ast: Module): Duration = {
    val dict = Seq("minutes", "hours", "days", "weeks", "months", "years")

    def execute(r: Runtime, name: String, params: Seq[Double]): Duration = {
      r.executeFunction(name, params).get.asInstanceOf[Duration]
    }

    def freeDuration(e: Expression, d: Duration): Duration = {
      e match {
        case b: BinaryOpExpr => Seq(freeDuration(b.e1, d), freeDuration(b.e2, d)).max
        case l: BoolOpExpr => Seq(freeDuration(l.e1, d), freeDuration(l.e2, d)).max
        case r: RelationExpr => Seq(freeDuration(r.e1, d), freeDuration(r.e2, d)).max
        case a: AppExpr => {
          if (dict.contains(a.name)) {
            Seq(execute(new DateTimeModule(), a.name, a.params.map(x => AST.printExpr(x).toDouble)), d).max
          }
          else
            a.params.map(x => freeDuration(x, d)).max
        }
        case n: NegOpExpr => freeDuration(n.expr, d)
        case _ => Duration.ZERO
      }
    }

    ast.funDecl.map(x => x.body.expr)
      .map(x => freeDuration(x, Duration.ZERO)).max
  }


}
