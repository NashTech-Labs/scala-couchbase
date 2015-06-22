name := "Couchbase using Scala"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.couchbase.client" % "java-client" % "2.1.3",
  "net.liftweb" % "lift-json_2.11" % "2.6.2",
   "net.liftweb" % "lift-json-ext_2.11" % "2.6.2",
  "org.scalatest" %% "scalatest" % "2.2.2" % "test",
  "junit" % "junit" % "4.11" % "test"
)


