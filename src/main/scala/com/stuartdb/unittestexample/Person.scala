package com.stuartdb.unittestexample

class Person(firstName: String, lastName: String) {

  def fullName(): String = {
    firstName + " " + lastName
  }

}
