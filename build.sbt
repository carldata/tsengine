name := "flow-script"

version := "0.1.0"

organization := "com.carl"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  // Scala native libraries

  // Java dependencies
  "org.antlr" % "antlr4-runtime" % "4.7",

  // Test dependencies
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)
