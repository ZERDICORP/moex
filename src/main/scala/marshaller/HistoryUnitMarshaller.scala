package com.moex.app
package marshaller

import dto.HistoryUnitDto

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat}

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author zerdicorp
 * @project moex
 * @created 10/7/22 - 6:59 PM
 */

trait HistoryUnitMarshaller extends SprayJsonSupport with DefaultJsonProtocol {
  implicit object LocalDateJsonFormat extends RootJsonFormat[LocalDate] {
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override def write(obj: LocalDate): JsString = {
      JsString(dateFormatter.format(obj))
    }

    override def read(json: JsValue): LocalDate = {
      json match {
        case JsString(s) => LocalDate.parse(s, dateFormatter)
        case _ => throw DeserializationException("Error info you want here ...")
      }
    }
  }

  implicit val historyMarshaller: RootJsonFormat[HistoryUnitDto] = jsonFormat21(HistoryUnitDto.apply)
}
