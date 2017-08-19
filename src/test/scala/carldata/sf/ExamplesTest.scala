package carldata.sf

import java.io.File

import carldata.sf.Runtime.BoolValue
import org.scalatest._

import scala.io.Source


/**
  * Run testcases from the folder testcases
  */
class ExamplesTest extends FlatSpec with Matchers {

  "TestCase runner" should "run all tests in folder: testcases" in {
    // Load and compile scripts
    val xs = loadTestCases("examples")
    val compiled = xs.map(x => (x._1, Compiler.make(x._2)))

    // First check for compilation errors
    val compileErrors = compiled.filter(_._2.isLeft)
    val passed = if (compileErrors.nonEmpty) {
      printErrors(compileErrors)
      false
    } else {
      // All files were compiled. Time to run them
      val executed = compiled.map { x =>
        (x._1, x._2.flatMap { exec => Interpreter(exec).run("assert", Seq())
        })
      }
      val runErrors = executed.filter(_._2.isLeft)
      if (runErrors.nonEmpty) {
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
      .filter(x => x.isDirectory)
      .flatMap { d =>
        d.listFiles.
          filter(x => x.isFile && x.getName.endsWith(".script"))
          .map(f => (f.getName, Source.fromFile(f).mkString))
      }
  }

  def printErrors(errors: Seq[(String, Either[String, _])]): Unit = {
    errors.foreach { x =>
      println(Console.RED)
      println("Error in file: " + x._1)
      println(x._2.left.getOrElse(""))
      println(Console.RESET)
    }
  }
}