package com.moex.app
package util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 7:45 PM
 */

object ParserUtil {
  private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def parseDouble(s: String): Option[Double] = try {
    Some(s.toDouble)
  } catch {
    case _: Throwable => None
  }

  def parseLong(s: String): Option[Long] = try {
    Some(s.toLong)
  } catch {
    case _: Throwable => None
  }

  def parseLocalDate(s: String): Option[LocalDate] = try {
    Option(LocalDate.parse(s, dateFormatter))
  } catch {
    case _: Throwable => None
  }
}
