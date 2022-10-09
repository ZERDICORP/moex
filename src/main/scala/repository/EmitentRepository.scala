package com.moex.app
package repository

import configuration.DatabaseConnection
import dto.EmitentDto
import table.EmitentTable

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 8:12 PM
 */

object EmitentRepository extends DatabaseConnection with EmitentTable {
  def update(emitent: EmitentDto): Future[Int] = {
    db.run(table.filter(_.id === emitent.id).update(emitent))
  }

  def deleteById(id: Long): Future[Int] = {
    db.run(table.filter(_.id === id).delete)
  }

  def findById(id: Long): Future[Option[EmitentDto]] = {
    db.run(table.filter(_.id === id).take(1).result.headOption)
  }

  def save(emitent: EmitentDto): Future[EmitentDto] = {
    db.run(table.returning(table) += emitent)
  }
}
