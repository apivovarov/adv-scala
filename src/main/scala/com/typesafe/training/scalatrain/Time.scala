/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import scala.annotation.tailrec

package object scalatrain {

  implicit object TimeOrdering extends Ordering[Time] {
    override def compare(x: Time, y: Time): Int = x - y
  }

  def isInc[A: Ordering](times: Seq[A]): Boolean = {
    val comp = implicitly[Ordering[A]]
    @tailrec
    def isIncInt(prev: A, times: Seq[A], isInc: Boolean): Boolean = {
      if (!isInc) false
      else {
        times match {
          case Nil => true
          case h :: tail => isIncInt(h, tail, comp.gteq(h, prev))
        }
      }
    }

    times match {
      case h :: tail => isIncInt(h, tail, true)
      case _ => true
    }
  }

  def isIncFL[A](times: Seq[A])(implicit comp: Ordering[A]): Boolean = {
    val z: (Boolean, A) = (true, times.head);
    times.foldLeft(z) {
      case ((res, prev), cur) => (res && comp.gteq(cur, prev), cur)
    }._1
  }

  def isIncSL[A: Ordering](times: Seq[A]): Boolean = {
    val comp = implicitly[Ordering[A]]
    times.sliding(2).forall {
      case h +: t +: _ => comp.lteq(h, t)
      case _ => true
    }
  }
}

object Time {
  def fromMinutes(minutes: Int): Time =
    Time(minutes / 60, minutes % 60)
}

case class Time(hours: Int = 0, minutes: Int = 0) {
  require(hours >= 0 && hours <= 23, "hours must be within 0 and 23")
  require(minutes >= 0 && minutes <= 59, "minutes must be within 0 and 59")

  val asMinutes: Int =
    hours * 60 + minutes

  override lazy val toString: String =
    f"$hours%02d:$minutes%02d"

  def minus(that: Time): Int =
    this.asMinutes - that.asMinutes

  def -(that: Time): Int =
    minus(that)
}
