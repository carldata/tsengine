package carldata.sf.face

import carldata.sf.compiler.AST._

import scala.util.parsing.combinator.RegexParsers

/**
  * Parser for FlowWorks FACE formulas
  */
object FaceParser extends RegexParsers {

  def identifier: Parser[String] = """\w[a-zA-Z0-9_]*""".r
  def function: Parser[AppExpr] = identifier ~ "(" ~ funParams ~")" ^^ {
    x => AppExpr(x._1._1._1,x._1._2)
  }

  def funParams: Parser[Seq[Expression]] = repsep(addOrBoolExpr, ",")
  def number: Parser[NumberLiteral] = """[+-]?([0-9]*[.])?[0-9]+""".r ^^ { ds =>  NumberLiteral(ds.toDouble) }
  def nullVariable: Parser[NumberLiteral] = ("null" | "NULL") ^^ { _ => NumberLiteral(Double.NaN)}
  def variable: Parser[VariableExpr] = identifier ^^ { id => VariableExpr(id) }
  def negativeExpr: Parser[MinusOpExpr] = "-" ~ factor ^^ { id => MinusOpExpr(id._2) }
  def factor: Parser[Expression] = function | number | nullVariable | variable | negativeExpr | "(" ~> addOrBoolExpr <~ ")"
  def powExpr  : Parser[Expression] = factor ~ rep("^" ~ factor) ^^ {
    case number ~ list => list.foldLeft(number) {
      case (x, "^" ~ y) => AppExpr("pow", Seq(x,y))
    }
  }
  def powOrFactor: Parser[Expression] = powExpr | factor
  def multiExpr  : Parser[Expression] = powOrFactor ~ rep( "*" ~ powOrFactor | "/" ~ powOrFactor) ^^ {
    case number ~ list => (number /: list) {
      case (x, "*" ~ y) => BinaryOpExpr(x, "*", y)
      case (x, "/" ~ y) => BinaryOpExpr(x, "/", y)
    }
  }

  def addExpr  : Parser[Expression] = multiExpr ~ rep("+" ~ multiExpr | "-" ~ multiExpr) ^^ {
    case number ~ list => list.foldLeft(number) { // same as before, using alternate name for /:
      case (x, "+" ~ y) => BinaryOpExpr(x, "+", y)
      case (x, "-" ~ y) => BinaryOpExpr(x, "-", y)
    }
  }

  def relationExpr  : Parser[Expression] = addExpr ~ ("==" | ">=" | "<="  | "!=" | "<" | ">" ) ~ addExpr ^^ {
      x => RelationExpr(x._1._1, x._1._2,x._2)
  }

  def addOrRelationExpr: Parser[Expression] = negationExpr | relationExpr | addExpr

  def boolExpr : Parser[Expression] = addOrRelationExpr ~ rep(("&&" | "||" ) ~ addOrRelationExpr) ^^ {
    case expr ~ list => list.foldLeft(expr) {
      case (x, "&&" ~ y) => BoolOpExpr(x, "&&", y)
      case (x, "||" ~ y) => BoolOpExpr(x, "||", y)
    }
  }

  def negationExpr : Parser[Expression] = "!" ~ addOrRelationExpr ^^ {
    x => NegOpExpr(x._2)
  }

  def addOrBoolExpr: Parser[Expression] = boolExpr | negationExpr | addOrRelationExpr

  def parse(input: String): Either[String, Expression] = parseAll(addOrBoolExpr, input) match {
    case Success(result, _) => Right(result)
    case failure : NoSuccess => Left(failure.msg)
  }
}
