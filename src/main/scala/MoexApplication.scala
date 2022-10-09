package com.moex.app

import controller.ParentController

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import com.moex.app.scheduler.manager.SchedulerManager

/**
 * @author zerdicorp
 * @project moex
 * @created 10/6/22 - 11:02 AM
 */

object MoexApplication extends App {
  implicit val actorSystem: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "moex")

  SchedulerManager startWith actorSystem

  Http()
    .newServerAt("localhost", 8080)
    .bind(ParentController.routes)
}