package com.moex.app
package marshaller.format

import spray.json.{DeserializationException, JsString, JsValue, RootJsonFormat}

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author zerdicorp
 * @project moex
 * @created 10/10/22 - 6:16 PM
 */

class LocalDateJsonFormat extends RootJsonFormat[LocalDate] {
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
