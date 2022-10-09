package com.moex.app
package table

import model.SecurityModel

import slick.lifted.TableQuery

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 7:55 PM
 */

trait SecurityTable {
  val table: TableQuery[SecurityModel] = TableQuery[SecurityModel]
}
