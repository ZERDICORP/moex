package com.moex.app
package marshaller

import dto.JoinedHistoryUnitDto
import marshaller.format.LocalDateJsonFormat

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
 * @author zerdicorp
 * @project moex
 * @created 10/10/22 - 6:16 PM
 */

trait JoinedHistoryUnitMarshaller extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val joinedHistoryUnitMarshaller_localDateJsonFormat: LocalDateJsonFormat = new LocalDateJsonFormat
  implicit val joinedHistoryUnitMarshaller: RootJsonFormat[JoinedHistoryUnitDto] = jsonFormat8(
    JoinedHistoryUnitDto.apply)
}
