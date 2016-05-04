package misc

import scala.annotation.tailrec

/**
  */
object Loop {

  @tailrec
  def repeatWhile(cond: => Boolean)(block: => Any): Unit = {
    if (cond) {
      block
      repeatWhile(cond)(block)
    }
  }

  def repeat(block: => Any) = new {
    @tailrec
    def until(cond: => Boolean): Unit = {
      block
      if (!cond) until(cond)
    }
  }
}
