name := "console.app"
organization := "com.example"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.5",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.7.8",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.7.8",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.7.8",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8" % "2.7.8",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.7.8",
  "org.apache.httpcomponents" % "httpclient" % "4.5.3",
  "junit" % "junit" % "4.12" % "test"
)