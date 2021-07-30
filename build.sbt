name := "unittest-example"

version := "0.1"

scalaVersion := "2.11.12"
val spark_libs = 
  sys.env.getOrElse("SPARK_JARS", "/Users/stuartlynn/miniconda3/envs/dbconnect/lib/python3.5/site-packages/pyspark/jars")
unmanagedBase := new java.io.File(spark_libs)


val sparkVersion = "2.4.5"
libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion

libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion

libraryDependencies += "org.apache.spark" %% "spark-hive" % sparkVersion


libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "com.amazon.deequ" % "deequ" % "1.0.2"
// https://mvnrepository.com/artifact/com.github.mrpowers/spark-fast-tests
libraryDependencies += "com.github.mrpowers" %% "spark-fast-tests" % "0.23.0" % Test
// https://mvnrepository.com/artifact/mrpowers/spark-daria

// https://mvnrepository.com/artifact/com.github.mrpowers/spark-daria
// libraryDependencies += "com.github.mrpowers" % "spark-daria" % "v0.39.0" % Test

// https://mvnrepository.com/artifact/org.scalamock/scalamock
libraryDependencies += "org.scalamock" %% "scalamock" % "5.1.0" % Test


mainClass := Some("pipeline")
