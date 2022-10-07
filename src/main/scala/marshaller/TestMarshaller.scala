package com.moex.app
package marshaller

import dto.TestDto

import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

/**
 * @author zerdicorp
 * @project moex
 * @created 10/7/22 - 6:59 PM
 */

trait TestMarshaller {
  val testMarshaller: RootJsonFormat[TestDto] = jsonFormat3(TestDto.apply)
}
