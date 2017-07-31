
name := "flow-script"

version := "0.5.3"

organization := "io.github.carldata"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  // Scala native libraries

  // Java dependencies
  "org.antlr" % "antlr4-runtime" % "4.7",

  // Test dependencies
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

scalacOptions := Seq("-unchecked", "-deprecation")

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

homepage := Some(url("https://github.com/carldata/script-flow"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/carldata/script-flow"),
    "scm:git@github.com:carldata/script-flow.git"
  )
)

developers := List(
  Developer(
    id = "klangner",
    name = "Krzysztof Langner",
    email = "klangner@gmail.com",
    url = url("http://github/klangner")
  )
)

useGpg := true

pomIncludeRepository := { _ => false }

