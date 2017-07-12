package com.carl.sf.compiler

import com.carl.sf.compiler.AST._
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

/**
  * This test check the following property:
  * AST pretty printed and then parsed should be the same.
  * The size of the list of external function is reduced to make this tests faster
  */
object ParserCheck extends Properties("Parser") {

  private val varGen = for {
    varName <- Gen.identifier
  } yield VariableExpr(varName)

  private val appExprGen = for {
    name <- Gen.identifier
    params <- Gen.listOf(varGen)
  } yield AppExpr(name, params)

  private val exprGen: Gen[Expression] = for {
    appExpr <- appExprGen
    varExpr <- varGen
    expr <- Gen.oneOf(Seq(appExpr, varExpr))
  } yield expr

  private val paramsGen = for {
    name <- Gen.identifier
    typeName <- Gen.identifier
  } yield FunParam(name, typeName)

  private val externFunGen = for {
    funName <- Gen.identifier
    funParams <- Gen.choose(0, 10) flatMap { sz => Gen.listOfN(sz, paramsGen) }
    funTypeName <- Gen.identifier
  } yield ExternalFun(funName, funParams, funTypeName)

  private val funDefGen = for {
    funName <- Gen.identifier
    funParams <- Gen.choose(0, 10) flatMap { sz => Gen.listOfN(sz, paramsGen) }
    funTypeName <- Gen.identifier
    bodyExpr <- exprGen
  } yield FunctionDef(funName, funParams, funTypeName, bodyExpr)

  private val moduleGen = for {
    moduleName <- Gen.identifier
    efs <- Gen.choose(0, 10) flatMap { sz => Gen.listOfN(sz, externFunGen) }
    funDefs <- Gen.choose(0, 10) flatMap { sz => Gen.listOfN(sz, funDefGen) }
  } yield Module(moduleName, efs, funDefs)


  property("parse") = forAll(moduleGen) { module: Module =>
    val code = AST.prettyPrint(module)
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
