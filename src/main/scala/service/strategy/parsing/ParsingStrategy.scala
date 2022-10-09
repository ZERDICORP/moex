package com.moex.app
package service.strategy.parsing

import akka.actor.typed.ActorSystem

import scala.concurrent.ExecutionContext
import scala.xml.Node

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 2:54 AM
 */

trait ParsingStrategy {
  def accepts(xmlType: String): Boolean

  def parse(xml: Node)(implicit exc: ExecutionContext, actorSystem: ActorSystem[Nothing]): Unit
}
