package com.moex.app
package marshaller

import dto.SecurityDto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
 * @author zerdicorp
 * @project moex
 * @created 10/7/22 - 6:59 PM
 */

trait SecurityMarshaller extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val securityMarshaller: RootJsonFormat[SecurityDto] = jsonFormat13(SecurityDto.apply)
}
