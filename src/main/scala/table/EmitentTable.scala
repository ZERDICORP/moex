package com.moex.app
package table

import model.EmitentModel

import slick.lifted.TableQuery

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 8:13 PM
 */

trait EmitentTable {
  val emitentTable: TableQuery[EmitentModel] = TableQuery[EmitentModel]
}
