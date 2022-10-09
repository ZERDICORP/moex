package com.moex.app
package controller

import dto.SecurityDto
import marshaller.SecurityMarshaller
import service.SecurityService

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 9:27 PM
 */

private class SecurityController extends SecurityMarshaller {
  val routes: Route = {
    (get & path("security" / LongNumber)) { id =>
      onSuccess(SecurityService.findById(id)) { security =>
        if (security.isDefined) {
          complete(security)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (delete & path("security" / LongNumber)) { id =>
      onSuccess(SecurityService.deleteById(id)) { count =>
        if (count > 0) {
          complete(StatusCodes.OK)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (put & path("security") & entity(as[SecurityDto])) { securityDto =>
      onSuccess(SecurityService.update(securityDto)) { count =>
        if (count > 0) {
          complete(StatusCodes.OK)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (post & path("security") & entity(as[SecurityDto])) { securityDto =>
      // TODO: validate security name
      SecurityService.save(securityDto)
      complete(StatusCodes.OK)
    }
  }
}
