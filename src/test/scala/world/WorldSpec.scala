package world

import java.lang.{IllegalArgumentException => IAE}

import org.scalatest.{Matchers, WordSpec}

class WorldSpec extends WordSpec with Matchers {

  "World" should {
    "work" in {
      new World
    }
  }
}
