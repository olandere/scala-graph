lazy val root = (project in file(".")).
  settings(
    name := "scala-graph",
    organization := "com.divaszivis",
    version := "1.0",
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.2.1" % "test"
	)
)
