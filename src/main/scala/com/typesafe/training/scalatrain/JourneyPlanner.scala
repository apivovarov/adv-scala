/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

class JourneyPlanner(trains: Set[Train]) {

  val stations: Set[Station] =
  // Could also be expressed in short notation: trains flatMap (_.stations)
    trains.flatMap(train => train.stations)

  def trainsAt(station: Station): Set[Train] =
  // Could also be expressed in short notation: trains filter (_.stations contains station)
    trains.filter(train => train.stations contains station)

  def stopsAt(station: Station): Set[(Time, Train)] =
    for {
      train <- trains
      timeAndStation <- train.schedule if timeAndStation._2 == station
    } yield (timeAndStation._1, train)

  def isShortTrip(f: Station, t: Station): Boolean = {
    trains.exists(t => t.stations.dropWhile(s => s != f) match {
      case `f` +: `t` +: _ => true
      case `f` +: _ +: `t` +: _ => true
      case _ => false
    })
  }
}
