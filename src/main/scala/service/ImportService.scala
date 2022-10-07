package com.moex.app
package service

/**
 * @author zerdicorp
 * @project akka_http_example
 * @created 10/7/22 - 1:59 PM
 */

object ImportService {
  def importXml(xml: String): Unit = {
    // TODO: save xml to db with status XmlStatus.NEW
    println(xml)
  }
}
