/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package misc

import scala.collection.immutable.Seq

case class Queue[+A] (elements: Seq[A]) {

  def enque[T >: A](elem: T): Queue[T] = new Queue(elem +: elements)

  def dequeue: (A, Queue[A]) =
    elements match {
      case element +: elements => (element, new Queue(elements))
      case _       => throw new UnsupportedOperationException("Cannot dequeue from an empty queue")
    }
}

class Vehicle
class Car extends Vehicle
class FastCar extends Car

class ZZ {

  val a: Queue[Car] = Queue[Car](Seq(new Car()))

  val a2: Queue[Car] = a.enque(new FastCar)
  val a3: Queue[Vehicle] = a.enque(new Vehicle)
  val a4: Queue[Any] = a.enque("fff")
}
