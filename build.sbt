organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.12.4"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test

lagomCassandraEnabled in ThisBuild := false
lagomKafkaEnabled in ThisBuild := false


lazy val `boom` = (project in file("."))
  .aggregate(`boom-api`, `boom-impl`, `endpoint-api`, `endpoint-impl`)

lazy val `boom-api` = (project in file("boom-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `boom-impl` = (project in file("boom-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`boom-api`)

lazy val `endpoint-api` = (project in file("endpoint-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `endpoint-impl` = (project in file("endpoint-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`boom-api`, `endpoint-api`)


