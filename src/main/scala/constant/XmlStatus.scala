package com.moex.app
package constant

/**
 * @author zerdicorp
 * @project moex
 * @created 10/7/22 - 7:22 PM
 */

trait XmlStatus {
  val NEW: String = "NEW"
  val PARSING_STARTED: String = "PARSING_STARTED"
  val PARSING_COMPLETED: String = "PARSING_COMPLETED"
  val PARSING_FAILED: String = "PARSING_FAILED"
}
