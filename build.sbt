name := """play-java-starter-example"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += guice

// Test Database
libraryDependencies += "com.h2database" % "h2" % "1.4.197"
libraryDependencies ++= Seq(evolutions, jdbc)
// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.12"
libraryDependencies ++= Seq(
  "io.ebean" % "ebean" % "11.32.1",
  "org.junit.platform" % "junit-platform-runner" % "1.3.1" % Test,
  "org.junit.jupiter" % "junit-jupiter-api" % "5.3.1" % Test,
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.3.1" % Test,
  "org.junit.jupiter" % "junit-jupiter-params" % "5.3.1" % Test,
  "org.junit.vintage" % "junit-vintage-engine" % "5.3.1" % Test,
  "org.mockito" % "mockito-core" % "2.21.0" % Test,
  "junit" % "junit" % "4.12" % Test)

// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
