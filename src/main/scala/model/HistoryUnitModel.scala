package com.moex.app
package model

import dto.HistoryUnitDto

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

import java.time.LocalDate

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 6:45 PM
 */

class HistoryUnitModel(tag: Tag) extends Table[HistoryUnitDto](tag, "history") {
  def id: Rep[Option[Long]] = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def boardid: Rep[String] = column[String]("boardid")

  def tradedate: Rep[Option[LocalDate]] = column[Option[LocalDate]]("tradedate")

  def shortname: Rep[String] = column[String]("shortname")

  def secid: Rep[String] = column[String]("secid")

  def numtrades: Rep[Option[Double]] = column[Option[Double]]("numtrades")

  def value: Rep[Option[Double]] = column[Option[Double]]("value")

  def open: Rep[Option[Double]] = column[Option[Double]]("open")

  def low: Rep[Option[Double]] = column[Option[Double]]("low")

  def high: Rep[Option[Double]] = column[Option[Double]]("high")

  def legalcloseprice: Rep[Option[Double]] = column[Option[Double]]("legalcloseprice")

  def waprice: Rep[Option[Double]] = column[Option[Double]]("waprice")

  def close: Rep[Option[Double]] = column[Option[Double]]("close")

  def volume: Rep[Option[Double]] = column[Option[Double]]("volume")

  def marketprice2: Rep[Option[Double]] = column[Option[Double]]("marketprice2")

  def marketprice3: Rep[Option[Double]] = column[Option[Double]]("marketprice3")

  def admittedquote: Rep[Option[Double]] = column[Option[Double]]("admittedquote")

  def mp2valtrd: Rep[Option[Double]] = column[Option[Double]]("mp2valtrd")

  def marketprice3tradesvalue: Rep[Option[Double]] = column[Option[Double]]("marketprice3tradesvalue")

  def admittedvalue: Rep[Option[Double]] = column[Option[Double]]("admittedvalue")

  def waval: Rep[Option[Double]] = column[Option[Double]]("waval")

  override def * : ProvenShape[HistoryUnitDto] = (id, boardid, tradedate, shortname, secid, numtrades, value, open, low,
    high, legalcloseprice, waprice, close, volume, marketprice2, marketprice3, admittedquote, mp2valtrd,
    marketprice3tradesvalue, admittedvalue, waval) <> (HistoryUnitDto.tupled, HistoryUnitDto.unapply)
}
