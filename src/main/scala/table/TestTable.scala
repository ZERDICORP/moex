package com.example.akka_http
package table

import model.TestModel

import slick.lifted.TableQuery

/**
 * @author zerdicorp
 * @project akka_http_example
 * @created 10/7/22 - 7:51 PM
 */

trait TestTable {
  val table = TableQuery[TestModel]
}
