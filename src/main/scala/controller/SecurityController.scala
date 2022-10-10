package com.moex.app
package controller

import dto.SecurityDto
import marshaller.SecurityMarshaller
import service.SecurityService
import validation.SecurityValidator

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives.{complete, _}
import akka.http.scaladsl.server.Route

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 9:27 PM
 */

private class SecurityController extends SecurityMarshaller {
  val routes: Route = {
    (get & path("securities")) {
      complete(SecurityService.findAll())
    } ~ (get & path("securities" / LongNumber)) { id =>
      onSuccess(SecurityService.findById(id)) { security =>
        if (security.isDefined) {
          complete(security)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (delete & path("securities" / LongNumber)) { id =>
      onSuccess(SecurityService.deleteById(id)) { count =>
        if (count > 0) {
          complete(StatusCodes.OK)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (put & path("securities") & entity(as[SecurityDto])) { securityDto =>
      onSuccess(SecurityService.update(securityDto)) { count =>
        if (count > 0) {
          complete(StatusCodes.OK)
        } else {
          complete(StatusCodes.NotFound)
        }
      }
    } ~ (post & path("securities") & entity(as[SecurityDto])) { securityDto =>
      SecurityValidator.validate(securityDto).fold(
        error => complete(StatusCodes.BadRequest -> error),
        validatedSecurityDto => {
          SecurityService.save(validatedSecurityDto)
          complete(StatusCodes.OK)
        }
      )
    }
  }
}
