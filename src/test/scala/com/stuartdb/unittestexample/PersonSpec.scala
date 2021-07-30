package com.stuartdb.unittestexample

import org.scalatest.FreeSpec

class PersonSpec extends FreeSpec {

  "fullName" - {

    "returns the first name and last name concatenated" in {

      val lilXan = new Person("Lil", "Xan")
      assert(lilXan.fullName() === "Lil Xan")

    }

  }

}
