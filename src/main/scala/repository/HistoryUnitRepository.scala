package com.moex.app
package repository

import configuration.DatabaseConnection
import dto.HistoryUnitDto
import table.HistoryUnitTable

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 6:58 PM
 */

object HistoryUnitRepository extends DatabaseConnection with HistoryUnitTable {
  def update(historyUnit: HistoryUnitDto): Future[Int] = {
    db.run(table.filter(_.id === historyUnit.id).update(historyUnit))
  }

  def deleteById(id: Long): Future[Int] = {
    db.run(table.filter(_.id === id).delete)
  }

  def findById(id: Long): Future[Option[HistoryUnitDto]] = {
    db.run(table.filter(_.id === id).take(1).result.headOption)
  }

  def save(historyUnit: HistoryUnitDto): Future[HistoryUnitDto] = {
    db.run(table.returning(table) += historyUnit)
  }
}
