package com.moex.app
package repository

import configuration.DatabaseConnection
import dto.HistoryUnitDto
import table.{EmitentTable, HistoryUnitTable, SecurityTable}
import util.ParserUtil.parseLocalDate

import slick.jdbc.PostgresProfile.api._

import java.time.LocalDate
import scala.concurrent.Future

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 6:58 PM
 */

object HistoryUnitRepository extends DatabaseConnection with HistoryUnitTable with SecurityTable with EmitentTable {
  def findAllJoined(orderBy: String, emitentTitle: String, tradedate: String):
  Future[Seq[(String, String, String, String, Option[LocalDate], Option[Double], Option[Double], Option[Double])]] = {
    val security_emitent_innerJoin = for {
      (s, e) <- securityTable join emitentTable on (_.emitent_id === _.id)
    } yield (s.secid, s.regnumber, s.name, e.title)

    val historyUnit_security_innerJoin = for {
      (h, s) <- historyUnitTable join security_emitent_innerJoin on (_.secid === _._1)
    } yield (s._1, s._2, s._3, s._4, h.tradedate, h.numtrades, h.open, h.close)

    var finalQuery = historyUnit_security_innerJoin
    if (orderBy.nonEmpty) {
      finalQuery = orderBy match {
        case "secid" => finalQuery.sortBy(_._1)
        case "regnumber" => finalQuery.sortBy(_._2)
        case "name" => finalQuery.sortBy(_._3)
        case "emitent_title" => finalQuery.sortBy(_._4)
        case "tradedate" => finalQuery.sortBy(_._5)
        case "numtrades" => finalQuery.sortBy(_._6)
        case "open" => finalQuery.sortBy(_._7)
        case "close" => finalQuery.sortBy(_._8)
      }
    }

    if (emitentTitle.nonEmpty) {
      finalQuery = for {
        r <- finalQuery
        if r._4 like s"%$emitentTitle%"
      } yield r
    }

    if (tradedate.nonEmpty) {
      val parsedTradedate: Option[LocalDate] = parseLocalDate(tradedate)
      if (parsedTradedate.isDefined) {
        finalQuery = for {
          r <- finalQuery
          if r._5 === parsedTradedate.get
        } yield r
      }
    }

    db.run(finalQuery.result)
  }

  def findAll(): Future[Seq[HistoryUnitDto]] = {
    db.run(historyUnitTable.result)
  }

  def update(historyUnit: HistoryUnitDto): Future[Int] = {
    db.run(historyUnitTable.filter(_.id === historyUnit.id).update(historyUnit))
  }

  def deleteById(id: Long): Future[Int] = {
    db.run(historyUnitTable.filter(_.id === id).delete)
  }

  def findById(id: Long): Future[Option[HistoryUnitDto]] = {
    db.run(historyUnitTable.filter(_.id === id).take(1).result.headOption)
  }

  def save(historyUnit: HistoryUnitDto): Future[HistoryUnitDto] = {
    db.run(historyUnitTable.returning(historyUnitTable) += historyUnit)
  }
}
