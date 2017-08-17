package carldata.sf.compiler

import carldata.sf.compiler.AST._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

/**
  * This test check the following property:
  * AST pretty printed and then parsed should be the same.
  * The size of the list of external function is reduced to make this tests faster
  */
object ParserCheck extends Properties("Parser") {

  val keywords: Set[String] = Set("def", "if", "then", "else", "let", "in")

  /** Keywords can't be used as identifiers */
  private val idGen = for {
    varName <- Gen.identifier
  } yield if(keywords.contains(varName)) "df" else varName

  private val varGen = for {
    varName <- idGen
  } yield VariableExpr(varName)

  private val unaryExprGen = for {
    varName <- varGen
  } yield MinusOpExpr(varName)

  private val binaryExprGen = for {
    e1 <- varGen
    op <- Gen.oneOf("+", "-", "*", "/")
    e2 <- varGen
  } yield BinaryOpExpr(e1, op, e2)

  private val negExprGen = for {
    varName <- varGen
  } yield NegOpExpr(varName)

  private val boolExprGen = for {
    e1 <- varGen
    op <- Gen.oneOf("&&", "||")
    e2 <- varGen
  } yield BoolOpExpr(e1, op, e2)

  private val relExprGen = for {
    e1 <- varGen
    op <- Gen.oneOf("==", "!=", ">", "<", ">=", "<=")
    e2 <- varGen
  } yield RelationExpr(e1, op, e2)

  private val ifExprGen = for {
    e1 <- varGen
    e2 <- varGen
    e3 <- varGen
  } yield IfExpr(e1, e2, e3)

  private val appExprGen = for {
    name <- idGen
    params <- Gen.listOf(varGen)
  } yield AppExpr(name, params)

  private val strLiteralGen = for {
    varName <- Gen.alphaNumStr
  } yield StringLiteral(varName)

  private val numLiteralGen = for {
    v <- Gen.posNum[Float]
  } yield NumberLiteral(v)

  private val boolLiteralGen = for {
    b <- Gen.oneOf(true, false)
  } yield BoolLiteral(b)

  private val literalGen = Gen.oneOf(strLiteralGen, numLiteralGen, boolLiteralGen)


  private val exprGen: Gen[Expression] = for {
    appExpr <- appExprGen
    varExpr <- varGen
    literal <- literalGen
    binaryOpExpr <- binaryExprGen
    boolOpExpr <- boolExprGen
    relExpr <- relExprGen
    unaryExpr <- unaryExprGen
    negExpr <- negExprGen
    ifExpr <- ifExprGen
    expr <- Gen.oneOf(Seq(appExpr, varExpr, literal, binaryOpExpr, boolOpExpr, relExpr, unaryExpr, negExpr, ifExpr))
  } yield expr

  private val paramsGen = for {
    name <- idGen
    typeName <- idGen
  } yield FunParam(name, typeName)

  private val externFunGen = for {
    funName <- idGen
    funParams <- Gen.choose(0, 10) flatMap { sz => Gen.listOfN(sz, paramsGen) }
    funTypeName <- idGen
  } yield ExternalFun(funName, funParams, funTypeName)

  private val funDefGen = for {
    funName <- idGen
    funParams <- Gen.choose(0, 10) flatMap { sz => Gen.listOfN(sz, paramsGen) }
    funTypeName <- idGen
    bodyExpr <- exprGen
  } yield FunctionDef(funName, funParams, funTypeName, FunctionBody(Seq(), bodyExpr))

  private val moduleGen = for {
    efs <- Gen.choose(0, 10) flatMap { sz => Gen.listOfN(sz, externFunGen) }
    funDefs <- Gen.choose(1, 10) flatMap { sz => Gen.listOfN(sz, funDefGen) }
  } yield Module(efs, funDefs)


  property("parse") = forAll(moduleGen) { module: Module =>
    val code = AST.printModule(module)
    Parser.parse(code) match {
      case Left(error) =>
        println("EXPECTED:\n%s\n".format(module.toString))
        println("CODE:\n%s\n".format(code))
        println("PARSER ERROR:\n%s\n".format(error))
        false

      case Right(m) =>
        if(m != module) {
          println("EXPECTED:\n%s\n".format(module.toString))
          println("CODE:\n%s\n".format(code))
          println("AST:\n%s\n".format(m.toString))
          false
        }
        else {
          true
        }
    }
  }

}
