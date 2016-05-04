package misc

case class ListNode[+T](h: T, t: ListNode[T]) {
  def head: T = h

  def tail: ListNode[T] = t

  def prepend[U >: T](elem: U): ListNode[U] =
    ListNode(elem, this)
}
