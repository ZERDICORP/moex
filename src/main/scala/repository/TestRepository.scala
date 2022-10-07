package com.moex.app
package repository

import configuration.DatabaseConnection
import dto.TestDto
import table.TestTable

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

/**
 * @author zerdicorp
 * @project akka_http_example
 * @created 10/6/22 - 11:28 AM
 */

object TestRepository extends DatabaseConnection with TestTable {
  def getUsers: Future[Seq[TestDto]] = {
    db.run(table.result)
  }

  def addUser(user: TestDto): Future[TestDto] = {
    db.run(table.returning(table) += user)
  }
}
