name := """play-scala-slick-3.0"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "com.typesafe.slick" %% "slick" % "3.0.0",
  "com.typesafe.slick" %% "slick-testkit" % "3.0.0",
  "org.slf4j" % "slf4j-nop" % "1.7.12",
  "com.zaxxer" % "HikariCP" % "2.3.8",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.postgresql" % "postgresql" % "9.4-1200-jdbc41"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
