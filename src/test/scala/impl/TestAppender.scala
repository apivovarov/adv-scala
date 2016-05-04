package impl

import org.scalatest.FunSuite

/**
  */
class TestAppender extends FunSuite {

  test("testIntApp") {
    val li = List(1, 2, 3, 4, 5)
    assert(Appender.appendAll(li) === 15)
  }

  test("testStrApp") {
    val li = List("a", " ", "b", " ", "c")
    assert(Appender.appendAll(li) === "a b c")
  }

}
