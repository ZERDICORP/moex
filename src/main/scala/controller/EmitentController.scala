package com.moex.app
package controller

import dto.EmitentDto
import marshaller.EmitentMarshaller
import service.EmitentService

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 9:27 PM
 */

private class EmitentController extends EmitentMarshaller {
  val routes: Route = {
    (get & path("emitent" / LongNumber)) { id =>
      onSuccess(EmitentService.findById(id)) { emitent =>
        if (emitent.isDefined) {
          complete(emitent)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (delete & path("emitent" / LongNumber)) { id =>
      onSuccess(EmitentService.deleteById(id)) { count =>
        if (count > 0) {
          complete(StatusCodes.OK)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (put & path("emitent") & entity(as[EmitentDto])) { emitentDto =>
      onSuccess(EmitentService.update(emitentDto)) { count =>
        if (count > 0) {
          complete(StatusCodes.OK)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (post & path("emitent") & entity(as[EmitentDto])) { emitentDto =>
      EmitentService.save(emitentDto)
      complete(StatusCodes.OK)
    }
  }
}
