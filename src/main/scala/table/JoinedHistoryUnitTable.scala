package com.moex.app
package table

import model.JoinedHistoryUnitModel

import slick.lifted.TableQuery

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 6:59 PM
 */

trait JoinedHistoryUnitTable {
  val joinedHistoryUnitTable: TableQuery[JoinedHistoryUnitModel] = TableQuery[JoinedHistoryUnitModel]
}
