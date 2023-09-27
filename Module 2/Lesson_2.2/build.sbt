version in ThisBuild := "0.1.0-SNAPSHOT"

scalaVersion in ThisBuild := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "Lesson_2.2",
    libraryDependencies += "net.ruippeixotog" %% "scala-scraper" % "3.0.0"
  )