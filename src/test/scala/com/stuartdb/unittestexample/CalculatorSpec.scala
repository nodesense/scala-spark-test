package com.stuartdb.unittestexample

import org.scalatest.FunSpec //Functional Specification

// Unit Test, white box test
// Semi integration test
// org.scalatest

// BDD - Behaviour driven development

// Test Suite/ describe keyword, optional
// Test case / it (it english word)
    // it should add two numbers
   // it should add two negative numbers
// assert are conditions if the conditions or spec fails, throw exception

class CalculatorSpec extends FunSpec {

  it("should adds two numbers") {
     // asset (actual === expected)
    assert(Calculator.addNumbers(3, 4) === 7)

  }


}