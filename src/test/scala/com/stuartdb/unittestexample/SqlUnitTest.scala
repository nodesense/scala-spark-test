package com.stuartdb.unittestexample

import org.apache.spark.sql.Row
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}

import scala.collection.mutable

class SqlUnitTest extends FunSuite with
  BeforeAndAfterEach with BeforeAndAfterAll with SparkSessionTestWrapper {

  @transient var sc: SparkContext = spark.sparkContext

  override def beforeAll(): Unit = {

    val envMap = Map[String,String](("Xmx", "512m"))

    val sparkConfig = new SparkConf()
    sparkConfig.set("spark.broadcast.compress", "false")
    sparkConfig.set("spark.shuffle.compress", "false")
    sparkConfig.set("spark.shuffle.spill.compress", "false")
    sparkConfig.set("spark.io.compression.codec", "lzf")

   spark.sql("DROP TABLE IF EXISTS  person ");
    // DROP the db if needed

  }

  override def afterAll(): Unit = {
    spark.sql("DROP TABLE IF EXISTS person ");
    sc.stop()
  }

  test("Test table creation and summing of counts") {
    val personRDD = sc.parallelize(Seq(Row("ted", 42, "blue"),
      Row("tj", 11, "green"),
      Row("andrew", 9, "green")))

    spark.sql("create table person (name string, age int, color string)")

    val emptyDataFrame = spark.sql("select * from person limit 0")

    val personDataFrame = spark.createDataFrame(personRDD, emptyDataFrame.schema)
    personDataFrame.registerTempTable("tempPerson")

    val ageSumDataFrame = spark.sql("select sum(age) from tempPerson")

    val localAgeSum = ageSumDataFrame.take(10)

    assert(localAgeSum(0).get(0) == 62, "The sum of age should equal 62 but it equaled " + localAgeSum(0).get(0))
  }
}