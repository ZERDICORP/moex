package com.moex.app
package table

import model.TestModel

import slick.lifted.TableQuery

/**
 * @author zerdicorp
 * @project moex
 * @created 10/7/22 - 7:51 PM
 */

trait TestTable {
  val table: TableQuery[TestModel] = TableQuery[TestModel]
}
