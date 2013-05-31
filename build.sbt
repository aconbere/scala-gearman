name := "gearman"

version := "0.0.1-SNAPSHOT"

crossScalaVersions := Seq("2.9.2", "2.9.3", "2.10.0")

organization := "org.conbere"

licenses := Seq("BSD-style" -> url("http://www.opensource.org/licenses/bsd-license.php"))

homepage := Some(url("http://github.com/aconbere/scala-gearman"))

scalaVersion := "2.10.0"

sbtVersion := "0.12.3"

scalacOptions ++= Seq(
  "-deprecation",
  "-feature"
)

fork in run := true

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

testOptions in Test += Tests.Argument("-oDF")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.9.1" % "test"
, "junit" % "junit" % "4.10" % "test"
, "com.typesafe.akka" %% "akka-actor" % "2.1.0"
, "org.apache.logging.log4j" % "log4j-core" % "2.0-beta3"
, "com.typesafe" %% "scalalogging-log4j" % "1.0.1"
)
