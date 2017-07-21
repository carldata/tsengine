package com.carl.sf

import java.io.File

import com.carl.sf.Runtime.{BoolValue, NumberValue}
import com.carl.sf.core.Core
import org.scalatest._

import scala.io.Source


/**
  * Run testcases from the folder testcases
  */
class ScriptTestCases extends FlatSpec with Matchers {

  "TestCase runner" should "run all tests in folder: testcases" in {
    // Load and compile scripts
    val xs = loadTestCases("testcases")
    val compiled = xs.map(x => (x._1, Compiler.compile(x._2, Seq(Core.header))))

    // First check for compilation errors
    val compileErrors =  compiled.filter(_._2.isLeft)
    val passed = if(compileErrors.nonEmpty) {
      printErrors(compileErrors)
      false
    } else {
      // All files were compiled. Time to run them
      val executed = compiled.map(x => (x._1, x._2.flatMap(ast => new Interpreter(ast, new Core()).run("assert", Seq()))))
      val runErrors =  executed.filter(_._2.isLeft)
      if(runErrors.nonEmpty){
        printErrors(runErrors)
        false
      } else {
        // Check if the result of each module is true
        val results = executed.map(x => (x._1, x._2.getOrElse(BoolValue(false))))
        val resultErrors = results.filter(_._2 == BoolValue(false))
        if (resultErrors.nonEmpty) {
          println("Returned false instead of true in files:")
          resultErrors.map(_._1).foreach(println)
          false
        } else {
          println(Console.GREEN + "All %d scripts passed.".format(xs.length) + Console.RESET)
          true
        }
      }
    }

    passed shouldBe true
  }

  def loadTestCases(folder: String): Seq[(String, String)] = {
    new File(folder)
      .listFiles
      .filter(x => x.isFile && x.getName.endsWith(".script"))
      .map(f => (f.getName, Source.fromFile(f).mkString))
  }

  def printErrors(errors: Seq[(String, Either[String,_])]): Unit = {
    errors.foreach { x =>
      println(Console.RED + "Error in file: " + x._1 + Console.RESET)
      println(x._2.left.getOrElse(""))
    }
  }
}