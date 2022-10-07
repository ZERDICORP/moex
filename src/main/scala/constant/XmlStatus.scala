package com.moex.app
package constant

/**
 * @author zerdicorp
 * @project akka_http_example
 * @created 10/7/22 - 7:22 PM
 */

trait XmlStatus {
  val NEW: String = "NEW"
  val PARSING_STARTED: String = "PARSING_STARTED"
  val PARSING_COMPLETED: String = "PARSING_COMPLETED"
}
