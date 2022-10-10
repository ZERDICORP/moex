package com.moex.app
package controller

import dto.{HistoryUnitDto, JoinedHistoryUnitDto}
import marshaller.{HistoryUnitMarshaller, JoinedHistoryUnitMarshaller}
import service.HistoryUnitService

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 9:27 PM
 */

private class HistoryUnitController extends HistoryUnitMarshaller with JoinedHistoryUnitMarshaller {
  val routes: Route = {
    (get & path("history" / "joined") &
      parameters("orderBy".as[String], "emitent_title".as[String], "tradedate".as[String])) {
      (orderBy, emitentTitle, tradedate) =>
        onSuccess(HistoryUnitService.findAllJoined(orderBy, emitentTitle, tradedate)) { seq =>
          complete(seq.map(
            item => JoinedHistoryUnitDto(item._1, item._2, item._3, item._4, item._5, item._6, item._7, item._8)))
        }
    } ~ (get & path("history")) {
      complete(HistoryUnitService.findAll())
    } ~ (get & path("history" / LongNumber)) { id =>
      onSuccess(HistoryUnitService.findById(id)) { historyUnit =>
        if (historyUnit.isDefined) {
          complete(historyUnit)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (delete & path("history" / LongNumber)) { id =>
      onSuccess(HistoryUnitService.deleteById(id)) { count =>
        if (count > 0) {
          complete(StatusCodes.OK)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (put & path("history") & entity(as[HistoryUnitDto])) { historyUnitDto =>
      onSuccess(HistoryUnitService.update(historyUnitDto)) { count =>
        if (count > 0) {
          complete(StatusCodes.OK)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (post & path("history") & entity(as[HistoryUnitDto])) { historyUnitDto =>
      HistoryUnitService.save(historyUnitDto)
      complete(StatusCodes.OK)
    }
  }
}
