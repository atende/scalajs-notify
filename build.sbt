lazy val root = project.in(file(".")).
  enablePlugins(ScalaJSPlugin)

name := """notify"""

version := "1.0"

scalaVersion := "2.11.6"

jsDependencies += RuntimeDOM

libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.1" % "test"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.8.0"

testFrameworks += new TestFramework("utest.runner.Framework")

persistLauncher in Compile := true

persistLauncher in Test := false

scalaJSStage in Global := FastOptStage

