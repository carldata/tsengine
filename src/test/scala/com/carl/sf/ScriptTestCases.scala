package com.carl.sf

import java.io.File

import com.carl.sf.core.Core
import org.scalatest._

import scala.io.Source


/**
  * Run testcases from the folder testcases
  */
class ScriptTestCases extends FlatSpec with Matchers {

  "TestCase runner" should "run all tests in folder: testcases" in {
    // Load and compile scripts
    val xs = new File("testcases")
      .listFiles
      .filter(x => x.isFile && x.getName.endsWith(".script"))
      .map(f => (f.getName, Source.fromFile(f).mkString))
      .map(x => (x._1, Compiler.compile(x._2, Seq(Core.header))))

    // First check for compilation errors
    val errorFiles =  xs.filter(_._2.isLeft)
    if(errorFiles.length > 0) {
      errorFiles.foreach { x =>
        println(Console.RED + "Error in file: " + x._1 + Console.RESET)
        println(x._2.left.getOrElse(""))
      }
    } else {
      // All files were compiled. Time to run them
      println(Console.GREEN + "All %d scripts passed.".format(xs.length) + Console.RESET)
    }

    xs.count(_._2.isRight) shouldBe xs.length
  }

}