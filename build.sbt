name := """Play Chat Room Example"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.webjars" % "bootstrap" % "3.2.0",
  "org.webjars" % "jquery" % "1.11.1"
)

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

scalacOptions += "-target:jvm-1.7"
