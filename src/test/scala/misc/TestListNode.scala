package misc

import org.scalatest.FunSuite

class TestListNode extends FunSuite {



  test("t1") {
    val empty: ListNode[Null] = ListNode(null, null)
    val strList: ListNode[String] = empty.prepend("hello").prepend("world")
    val anyList: ListNode[Any] = strList.prepend(12345)

    class A
    class B extends A
    class C extends B
    val bList: ListNode[B] = empty.prepend(new B())
    val cList: ListNode[B] = bList.prepend(new C())
    val aList: ListNode[A] = bList.prepend(new A())

  }

}
