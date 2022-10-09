package com.moex.app
package scheduler.manager

import scheduler.XmlParsingScheduler

import akka.actor.typed.{ActorSystem, Scheduler}

import java.util.concurrent.TimeUnit
import scala.concurrent.ExecutionContext
import scala.concurrent.duration.Duration

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 1:15 AM
 */

object SchedulerManager {
  private val delay: Long = 1
  private val initialDelay: Long = 0
  private val delayTimeUnit: TimeUnit = TimeUnit.SECONDS

  def startWith(implicit actorSystem: ActorSystem[Nothing]): Unit = {
    val scheduler: Scheduler = actorSystem.scheduler
    implicit val executor: ExecutionContext = actorSystem.executionContext

    List {
      new XmlParsingScheduler
    }.foreach {
      scheduler.scheduleWithFixedDelay(Duration(initialDelay, delayTimeUnit), Duration(delay, delayTimeUnit))(_)
    }
  }
}
