package com.moex.app
package table

import model.XmlModel

import slick.lifted.TableQuery

/**
 * @author zerdicorp
 * @project moex
 * @created 10/7/22 - 7:51 PM
 */

trait XmlTable {
  val xmlTable: TableQuery[XmlModel] = TableQuery[XmlModel]
}
