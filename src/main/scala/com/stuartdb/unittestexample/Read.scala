package com.stuartdb.unittestexample


import org.apache.spark.sql.{DataFrame, SparkSession}

object Read   {

  def readFromCsvFileWithSchema(spark: SparkSession, location: String, schema: String): DataFrame =
    spark.read
      .option("header", "true")
      .schema(schema)
      .csv(getClass.getResource(location).getPath)

  def readFromCsvFile(spark: SparkSession, location: String): DataFrame =
    spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(location)

  def readTableFromHive(spark: SparkSession, database: String, tableName: String): DataFrame =
    spark.sql(s"select * from $database.$tableName")
}