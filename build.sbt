
name := "flow-script"

version := "0.7.8"

organization := "io.github.carldata"

scalaVersion := "2.12.3"

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  // Scala native libraries
  "io.github.carldata" %% "timeseries" % "0.4.5",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6",

  // Java dependencies
  "org.antlr" % "antlr4-runtime" % "4.7",

  // Test dependencies
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

scalacOptions := Seq("-unchecked", "-deprecation")

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

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

