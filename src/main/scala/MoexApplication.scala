package com.example.akka_http

import controller.ParentController

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http

/**
 * @author zerdicorp
 * @project akka_http_example
 * @created 10/6/22 - 11:02 AM
 */

object MoexApplication extends App {
  implicit val actorSystem: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "akka-http")
  Http()
    .newServerAt("localhost", 8080)
    .bind(ParentController.routes)
}