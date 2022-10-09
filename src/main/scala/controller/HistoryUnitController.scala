package com.moex.app
package controller

import dto.HistoryUnitDto
import marshaller.HistoryUnitMarshaller
import service.HistoryUnitService

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 9:27 PM
 */

private class HistoryUnitController extends HistoryUnitMarshaller {
  val routes: Route = {
    (get & path("history" / LongNumber)) { id =>
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
