import com.github.sbt.jacoco.JacocoPlugin.autoImport.jacocoExcludes
import sbt.Keys.libraryDependencies

import scala.collection.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"
mainClass in (Compile, packageBin) := Some("ChessScala/MainClass")

lazy val root = (project in file("."))
  .settings(
    name := "ChessScala",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.16",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test",
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.1.0",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC7",
    libraryDependencies += "com.google.inject" % "guice" % "5.1.0",
    libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.1.0").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.7",
    libraryDependencies += ("com.softwaremill.sttp.client3" %% "core" % "3.8.13").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  )


jacocoReportSettings := JacocoReportSettings(
  "Jacoco Coverage Report",
  None,
  JacocoThresholds(),
  Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML), // note XML formatter
  "utf-8")

coverageEnabled := true

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _*) => MergeStrategy.discard
  case _                        => MergeStrategy.first
}
