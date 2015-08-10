name := """quant-store-web"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)



scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "com.hazelcast" % "hazelcast" % "3.4.2",
  "com.hazelcast" % "hazelcast-client" % "3.4.2"
)




