package com.moex.app
package marshaller

import dto.EmitentDto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 11:32 PM
 */

trait EmitentMarshaller extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val emitentMarshaller: RootJsonFormat[EmitentDto] = jsonFormat4(EmitentDto.apply)
}
