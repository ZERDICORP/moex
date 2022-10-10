package com.moex.app
package table

import model.HistoryUnitModel

import slick.lifted.TableQuery

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 6:59 PM
 */

trait HistoryUnitTable {
  val historyUnitTable: TableQuery[HistoryUnitModel] = TableQuery[HistoryUnitModel]
}
