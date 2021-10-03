name := "LogAnalyzer"

version := "0.1.1"

scalaVersion := "2.12.15"

idePackagePrefix := Some("org.log.app")

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "3.1.2" % "provided",
    "org.apache.spark" %% "spark-sql" % "3.1.2" % "provided",
    "org.apache.spark" %% "spark-streaming" % "3.1.2" % "provided"
)
