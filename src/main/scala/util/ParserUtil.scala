package com.moex.app
package util

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 7:45 PM
 */

object ParserUtil {
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
}
