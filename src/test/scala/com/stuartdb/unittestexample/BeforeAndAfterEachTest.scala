package com.stuartdb.unittestexample


import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import org.scalatest.FunSpec

class BeforeAndAfterEachTest extends FunSpec with
  BeforeAndAfterAll with BeforeAndAfterEach {

  override def beforeAll(): Unit = {
    // this code here run before starting any test
    // db/test data, unit of work under testing
    println("**beforeAll called")
  }


  override def beforeEach(): Unit = {
    // this code here run before starting each test case
    // test / test object needed for test cases
    println("**beforeEach called")
  }


  override def afterEach(): Unit = {
    // this code here run after each test
    println("**afterEach called")
  }

  override def afterAll(): Unit = {
    // this code here run after completing all tests
    println("**afterAll called")
  }


  it("should adds two numbers") {
    println("**should adds two numbers\"")
    assert(1 + 1 === 2)

  }



  it("should subtract two numbers") {
    println("**should adds two numbers\"")
    assert(1 - 1 === 0)

  }



}

