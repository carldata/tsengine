package com.carl.tsengine.compiler

import com.carl.tsengine.compiler.AST.{FunBody, FunDecl, Module, VarExpr}
import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.forAll

/**
  * This test check the following property:
  * AST pretty printed and then parsed should be the same.
  */
object ParserCheck extends Properties("Parser") {

  private val moduleGen = for {
    moduleName <- Gen.identifier
  } yield Module(moduleName, FunDecl("my_fun", Seq("a", "xs"), FunBody(Seq(), VarExpr("a"))))


  property("prettyPrint -> parse") = forAll(moduleGen) { module: Module =>
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
        else { true }
    }
  }

}
