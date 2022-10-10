package com.moex.app
package repository

import configuration.DatabaseConnection
import dto.XmlDto
import table.XmlTable

import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

/**
 * @author zerdicorp
 * @project moex
 * @created 10/6/22 - 11:28 AM
 */

object XmlRepository extends DatabaseConnection with XmlTable {
  def updateStatusById(id: Option[Long], status: String): Unit = {
    val q = for {t <- xmlTable if t.id === id} yield t.status
    db.run(q.update(status))
  }

  def findAllWithStatus(status: String): Future[Seq[XmlDto]] = {
    db.run(xmlTable.filter(_.status === status).result)
  }

  def save(xml: XmlDto): Future[XmlDto] = {
    db.run(xmlTable.returning(xmlTable) += xml)
  }
}
