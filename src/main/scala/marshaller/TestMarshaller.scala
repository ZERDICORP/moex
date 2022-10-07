package com.example.akka_http
package marshaller

import dto.TestDto

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

/**
 * @author zerdicorp
 * @project akka_http_example
 * @created 10/7/22 - 6:59 PM
 */

trait TestMarshaller {
  val testMarshaller: RootJsonFormat[TestDto] = jsonFormat3(TestDto.apply)
}