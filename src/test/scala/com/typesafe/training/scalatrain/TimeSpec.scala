/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import java.lang.{IllegalArgumentException => IAE}

import com.typesafe.training.scalatrain.scalatrain.{Aaa, AaaOrdering, StringImprovements, TimeOrdering}
import org.scalatest.{Matchers, WordSpec}

import scala.collection.immutable.TreeSet

class TimeSpec extends WordSpec with Matchers {

  "Creating a Time" should {
    "throw an IllegalArgumentException for hours not within 0 and 23" in {
      an[IAE] should be thrownBy Time(-1)
      an[IAE] should be thrownBy Time(24)
    }
    "throw an IllegalArgumentException for minutes not within 0 and 59" in {
      an[IAE] should be thrownBy Time(minutes = -1)
      an[IAE] should be thrownBy Time(minutes = 60)
    }
  }

  "The default arguments for hours and minutes" should {
    "be equal to 0" in {
      val time = Time()
      time.hours shouldEqual 0
      time.minutes shouldEqual 0
    }
  }

  "asMinutes" should {
    "be initialized correctly" in {
      Time(1, 40).asMinutes shouldEqual 100
    }
  }

  "Calling minus or -" should {
    "return the correct difference in minutes" in {
      Time(1, 40) minus Time(1, 10) shouldEqual 30
      Time(1, 40) - Time(1, 10) shouldEqual 30
    }
  }

  "Calling toString" should {
    "return a properly formatted string representation" in {
      Time(9, 30).toString shouldEqual "09:30"
    }
  }

  "Time Comparison" should {
    "compare times" in {
      val t1 = Time(1, 10)
      val t2 = Time(3, 5)
      t1 should be < t2
    }
  }

  "Time Comparison 2" should {
    "compare times" in {
      val t1 = Time(3, 5)
      val t2 = Time(3, 5)
      t1 shouldEqual t2
    }
  }

  "isInc" should {
    "return true" in {
      scalatrain.isInc(List(Time(3, 5), Time(3, 5), Time(3, 6), Time(3, 6), Time(4, 5))) shouldBe true
    }

    "return false" in {
      scalatrain.isInc(List(Time(3, 5), Time(3, 5), Time(3, 6), Time(3, 6), Time(3, 5))) shouldBe false
    }
  }

  "isIncFL" should {
    "return true" in {
      scalatrain.isIncFL(List(Time(3, 5), Time(3, 5), Time(3, 6), Time(3, 6), Time(4, 5))) shouldBe true
    }

    "return false" in {
      scalatrain.isIncFL(List(Time(3, 5), Time(3, 5), Time(3, 6), Time(3, 6), Time(3, 5))) shouldBe false
    }
  }

  "isIncSL" should {
    "return true" in {
      scalatrain.isIncSL(List(Time(3, 5), Time(3, 5), Time(3, 6), Time(3, 6), Time(4, 5))) shouldBe true
    }

    "return false" in {
      scalatrain.isIncSL(List(Time(3, 5), Time(3, 5), Time(3, 6), Time(3, 6), Time(3, 5))) shouldBe false
    }
  }

  "TreeSet" should {
    "accept Aaa" in {
      val s = TreeSet(Aaa(200), Aaa(100))
      s.toList shouldEqual List(Aaa(100), Aaa(200))
    }
  }

  "String" should {
    "have inc method" in {
      "ABC".inc(4) shouldEqual "EFG"
    }
  }
}
