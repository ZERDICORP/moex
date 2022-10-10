package com.moex.app
package marshaller

import dto.HistoryUnitDto
import marshaller.format.LocalDateJsonFormat

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

/**
 * @author zerdicorp
 * @project moex
 * @created 10/7/22 - 6:59 PM
 */

trait HistoryUnitMarshaller extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val historyUnitMarshaller_localDateJsonFormat: LocalDateJsonFormat = new LocalDateJsonFormat
  implicit val historyUnitMarshaller: RootJsonFormat[HistoryUnitDto] = jsonFormat21(HistoryUnitDto.apply)
}
