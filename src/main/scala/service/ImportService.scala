package com.moex.app
package service

import constant.XmlStatus

import akka.http.scaladsl.model.StatusCode
import akka.stream.Materializer
import akka.stream.scaladsl.Source
import akka.util.ByteString

import scala.concurrent.ExecutionContext

/**
 * @author zerdicorp
 * @project moex
 * @created 10/7/22 - 1:59 PM
 */

object ImportService extends XmlStatus {
  def importXml(source: Source[ByteString, Any])
               (implicit mtz: Materializer, exc: ExecutionContext): StatusCode = {
    XmlService.save(source)(mtz, exc)
  }

  def importXml(data: String)(implicit exc: ExecutionContext): StatusCode = {
    XmlService.save(data)(exc)
  }
}
