lazy val root = (project in file(".")).
  settings(
    name := "scala-graph",
    organization := "com.divaszivis",
    version := "1.0",
    scalaVersion := "2.12.4",
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.1" % "test"
	)
)
