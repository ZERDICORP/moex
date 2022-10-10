package com.moex.app
package model

import dto.JoinedHistoryUnitDto

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

import java.time.LocalDate

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 6:45 PM
 */

class JoinedHistoryUnitModel(tag: Tag) extends Table[JoinedHistoryUnitDto](tag, "joined_history") {
  def secid: Rep[String] = column[String]("secid")

  def regnumber: Rep[String] = column[String]("regnumber")

  def name: Rep[String] = column[String]("name")

  def emitent_title: Rep[String] = column[String]("emitent_title")

  def tradedate: Rep[Option[LocalDate]] = column[Option[LocalDate]]("tradedate")

  def numtrades: Rep[Option[Double]] = column[Option[Double]]("numtrades")

  def open: Rep[Option[Double]] = column[Option[Double]]("open")

  def close: Rep[Option[Double]] = column[Option[Double]]("close")

  override def * : ProvenShape[JoinedHistoryUnitDto] = (secid, regnumber, name, emitent_title, tradedate, numtrades,
    open, close) <> (JoinedHistoryUnitDto.tupled, JoinedHistoryUnitDto.unapply)
}
