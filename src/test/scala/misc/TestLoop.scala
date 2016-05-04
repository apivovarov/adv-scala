package misc

import org.scalatest.FunSuite

class TestLoop extends FunSuite {

  test("repeatWhile") {
    var cnt = 5
    Loop.repeatWhile(cnt > 0) {
      println(cnt)
      cnt = cnt - 1
    }
  }

  test("repeat") {
    var cnt = 5
    Loop.repeat {
      println(cnt)
      cnt = cnt - 1
    } until(cnt <= 0)
  }
}
