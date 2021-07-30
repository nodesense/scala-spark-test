package com.stuartdb.unittestexample
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}

import org.scalamock.scalatest.MockFactory

class MockSpecExample extends FunSuite with MockFactory {

  trait ProductService {
    def getProduct(id: Int): String;
  }

  object ProductServiceImpl extends ProductService {
    // actual code in main packages
    def getProduct(id: Int) = {
      println("calling web api .......")
      s"Product$id"
    } // this goes to Web Service  etc
  }

  // actual code, a function under test
  def getAndStoreProduct(id: Int, ps: ProductService) = {


       ps.getProduct(id) // make actula call to api

  }

  //getAndStoreProduct(10, ProductServiceImpl)

  test("Mock Func") {
    val mockProductService = mock[ProductService]
    // this should accept id 10, return YourProduct
    (mockProductService.getProduct _).expects(10).returning("YourProduct"). once()

    val result = getAndStoreProduct(10, mockProductService)
     // MOCK is not good for more use cases, more implementation
    // MOCK is good for single implementation, not for wider result type etc
    // getAndStoreProduct(20, mockProductService)

    assert(result == "YourProduct", " Here we test with mock ")

    //(mockProductService.getProduct _).verify(10).once()
  }

}
