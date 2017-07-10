package com.carl.sf.compiler

import com.carl.sf.compiler.AST.{FunParam, FunctionDef, Module, VariableExpr}
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

/**
  * This test check the following property:
  * AST pretty printed and then parsed should be the same.
  */
object ParserCheck extends Properties("Parser") {

  private val exprGen = for {
    varName <- Gen.identifier
  } yield VariableExpr(varName)

  private val paramsGen = for {
    name <- Gen.identifier
    typeName <- Gen.identifier
  } yield FunParam(name, Some(typeName))

  private val moduleGen = for {
    moduleName <- Gen.identifier
    funName <- Gen.identifier
    funParams <- Gen.listOf(paramsGen)
    funTypeName <- Gen.identifier
    bodyExpr <- exprGen
  } yield Module(moduleName, FunctionDef(funName, funParams, Some(funTypeName), bodyExpr))


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
