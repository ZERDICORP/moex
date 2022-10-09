package com.moex.app
package scheduler

import constant.XmlStatus
import dto.XmlDto
import service.XmlService

import akka.actor.typed.ActorSystem

import java.util.concurrent.{ExecutorService, Executors}
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 1:29 AM
 */

private class XmlParsingScheduler(implicit exc: ExecutionContext, actorSystem: ActorSystem[Nothing]) extends Runnable with XmlStatus {
  private val executorService: ExecutorService = Executors.newFixedThreadPool(100)

  private class Processor(xml: XmlDto) extends Runnable {
    override def run(): Unit = {
      XmlService.parse(xml)(exc, actorSystem)
    }
  }

  override def run(): Unit = {
    XmlService.findAllWithStatus(NEW)
      .onComplete {
        case Success(xmlList) =>
          xmlList.foreach { xml =>
            executorService.execute(new Processor(xml))
          }
        case Failure(e) => throw e
      }
  }
}
