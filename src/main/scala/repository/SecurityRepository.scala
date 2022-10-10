package com.moex.app
package repository

import configuration.DatabaseConnection
import dto.SecurityDto
import table.SecurityTable

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 7:56 PM
 */

object SecurityRepository extends DatabaseConnection with SecurityTable {
  def findAll(): Future[Seq[SecurityDto]] = {
    db.run(securityTable.result)
  }

  def update(security: SecurityDto): Future[Int] = {
    db.run(securityTable.filter(_.id === security.id).update(security))
  }

  def deleteById(id: Long): Future[Int] = {
    db.run(securityTable.filter(_.id === id).delete)
  }

  def findById(id: Long): Future[Option[SecurityDto]] = {
    db.run(securityTable.filter(_.id === id).take(1).result.headOption)
  }

  def findBySecid(secid: String): Future[Seq[SecurityDto]] = {
    db.run(securityTable.filter(_.secid === secid).result)
  }

  def save(security: SecurityDto): Future[SecurityDto] = {
    db.run(securityTable.returning(securityTable) += security)
  }
}
