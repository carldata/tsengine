package carldata.sf.face

import carldata.sf.compiler.AST.{BinaryOpExpr, Expression, NumberLiteral, VariableExpr}

import scala.util.parsing.combinator.RegexParsers

/**
  * Parser for FlowWorks FACE formulas
  */
object FaceParser extends RegexParsers {
  def number: Parser[NumberLiteral] = """\d+(\.\d*)?""".r ^^ { ds => NumberLiteral(ds.toFloat) }
  def variable: Parser[VariableExpr] = """\w[a-zA-Z0-9_]?""".r ^^ { id => VariableExpr(id) }
  def factor: Parser[Expression] = number | variable | "(" ~> expr <~ ")"
  def term  : Parser[Expression] = factor ~ rep( "*" ~ factor | "/" ~ factor) ^^ {
    case number ~ list => (number /: list) {
      case (x, "*" ~ y) => BinaryOpExpr(x, "*", y)
      case (x, "/" ~ y) => BinaryOpExpr(x, "/", y)
    }
  }

  def expr  : Parser[Expression] = term ~ rep("+" ~ term | "-" ~ term) ^^ {
    case number ~ list => list.foldLeft(number) { // same as before, using alternate name for /:
      case (x, "+" ~ y) => BinaryOpExpr(x, "+", y)
      case (x, "-" ~ y) => BinaryOpExpr(x, "-", y)
    }
  }

  def parse(input: String): Either[String, Expression] = parseAll(expr, input) match {
    case Success(result, _) => Right(result)
    case failure : NoSuccess => Left(failure.msg)
  }
}
