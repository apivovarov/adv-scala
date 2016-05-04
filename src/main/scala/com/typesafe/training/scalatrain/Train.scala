/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import scala.collection.immutable.Seq
import scala.collection.breakOut

case class Train(kind: String, number: Int, schedule: Seq[(Time, Station)]) {
  require(schedule.size >= 2, "schedule must contain at least two elements")
  // TODO Verify that `schedule` is strictly increasing in time

  val stations: Seq[Station] =
    // Could also be expressed in short notation: schedule map (_._2)
    schedule.map(stop => stop._2)

  val backToBackStations: Seq[(Station, Station)] = stations.zip(stations.tail)

  val departureTimes: Map[Station, Time] = schedule.map(_.swap)(breakOut)
}

case class Station(name: String)

sealed abstract class TrainInfo {
  def number: Int
}

case class InterCityExpress(number: Int, hasWifi: Boolean = false) extends TrainInfo
case class RegionalExpress(number: Int) extends TrainInfo
case class BavarianRegional(number: Int) extends TrainInfo

case class Hop(from: Station, to: Station, train: Train) {
  require(from != to)
  require(train.backToBackStations.contains(Seq(from, to)))

}
