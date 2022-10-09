package com.moex.app
package service.strategy.parsing

import constant.XmlType

import java.util.Locale
import scala.xml.Node

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 3:15 AM
 */

private class HistoryParsingStrategy extends ParsingStrategy with XmlType {
  override def accepts(xmlType: String): Boolean = {
    xmlType.toUpperCase(Locale.ROOT).equals(HISTORY)
  }

  override def parse(xml: Node): Unit = {
    xml \\ "row" foreach { row =>
      // TODO: create history entity and save to db
      // TODO: check if security with SECID is defined in our db, if not - make request to moex api
      // TODO: create set of undefined SECIDs and then make requests to moex api to find each security
    }
  }
}
