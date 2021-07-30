package com.stuartdb.unittestexample

import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfterAll, FunSuite}

class StubSpecEcample  extends FunSuite with MockFactory with BeforeAndAfterAll {
  trait HttpResult

  case class HttpOKResult(status: Int, content: String) extends HttpResult

  case class HttpClientErrorResult(status: Int, content: String) extends HttpResult

  case class HttpErrorResult(status: Int, content: String) extends HttpResult

  trait ProductService {
    def getProduct(id: Int): HttpResult
  }

  class YourProjectProductService extends ProductService {
    // very expensive cloud setup, your real code
    // where we cannot use it for testing
    override def getProduct(id: Int): HttpResult = ??? // not implemented
  }

  override def beforeAll(): Unit = {
    // this code here run before starting any test
    // db/test data, unit of work under testing
    println("**beforeAll called")

  }

  // Real function under test
  def getProduct(id: Int, productService: ProductService): HttpResult = {
    productService.getProduct(id) match {
      // case  r: HttpOKResult => HttpOKResult(r.status, """{"price": 90}""")
      case r: HttpOKResult => HttpOKResult(r.status, r.content)
      case r: HttpResult => r
      case _ => HttpErrorResult(503, "Bad")
    }
  }

  test("GetProductResult Test") {

    // productService a stub, has fake implementation
    val productService = stub[ProductService]
    // behaviour, implement the methods for the stubs
    // 10 is product id which is there in db
    (productService.getProduct _) when (10) returns (HttpOKResult(200, """{"price": 100}"""))
    (productService.getProduct _) when (10000) returns (HttpClientErrorResult(404, """{"errorCode": 4234343}"""))
    (productService.getProduct _) when (-1) returns (HttpErrorResult(500, {
      // any spark code, create a dataframe with mock data, return data frame
      """{"errorCode": -42342342323}"""
    }))


    // getProduct neeed YourProjectProductService however
    // we used a stub
    assert(getProduct(10, productService) == HttpOKResult(200, """{"price": 100}"""), " Here we test with mock ")
    assert(getProduct(10000, productService) == HttpClientErrorResult(404, """{"errorCode": 4234343}"""), " Here we test with mock ")
    assert(getProduct(-1, productService) == HttpErrorResult(500, """{"errorCode": -42342342323}"""), " Here we test with mock ")

  }

  // trait DataService {
    // def loadDataFrame(...)
  // }

  //object HiveService extends DataService {
    // loadDataFrame(...) = {
    // goes to real hive and fetch data
    //}
  // }

  // def processData(dataService: DataService) {
  //  dataService.loadDataFrame(...)
  //}


}

// top to bottom way


// program begin
//..
//..
// program end