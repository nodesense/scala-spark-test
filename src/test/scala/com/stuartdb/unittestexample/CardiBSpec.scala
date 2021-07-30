package com.stuartdb.unittestexample

import org.scalatest.FunSpec

// describe is a test suite, a test suite can have many test cases (it(
// describe act like an group for test cases
class CardiBSpec extends FunSpec {

  describe("realName") {

    it("returns her birth name") {
      // actual === expected
      assert(CardiB.realName() === "Belcalis Almanzar")
    }

  }



  describe("iLike") {


      it("works with a single argument") {
      assert(CardiB.iLike("dollars") === "I like dollars")
    }

    it("works with multiple arguments") {
      assert(CardiB.iLike("dollars", "diamonds") === "I like dollars, diamonds")
    }

    it("throws an error if no arg is passed") {
      // example test case for testing exception
      assertThrows[java.lang.IllegalArgumentException]{
        CardiB.iLike()
      }
    }

    it("does not compile with integer arguments") {

      assertDoesNotCompile("""CardiB.iLike(10, 20)""")
    }

  }

}
