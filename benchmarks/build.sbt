
scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.antlr" % "antlr4-runtime" % "4.7",
  "com.storm-enroute" %% "scalameter-core" % "0.8.2"
)

unmanagedBase := baseDirectory.value / "../target/scala-2.12/"