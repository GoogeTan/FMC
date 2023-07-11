ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.3.0"
ThisBuild / organization := "me.zahara"

lazy val root = (project in file("."))
  .settings(
    name := "fmc",
    idePackagePrefix := Some("me.zahara.fmc"),
  )
libraryDependencies += "org.typelevel" %% "cats-core" % "2.9.0"
libraryDependencies += "io.github.iltotore" %% "iron" % "2.0.0"
scalacOptions += "-Ykind-projector:underscores"