package com.moex.app
package model

import dto.{EmitentDto, SecurityDto}

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ForeignKeyQuery, ProvenShape, Tag}

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 7:50 PM
 */

class SecurityModel(tag: Tag) extends Table[SecurityDto](tag, "security") {
  def id: Rep[Option[Long]] = column[Option[Long]]("id", O.PrimaryKey)

  def secid: Rep[String] = column[String]("secid")

  def shortname: Rep[String] = column[String]("shortname")

  def regnumber: Rep[String] = column[String]("regnumber")

  def name: Rep[String] = column[String]("name")

  def isin: Rep[String] = column[String]("isin")

  def is_traded: Rep[Option[Long]] = column[Option[Long]]("is_traded")

  def emitent_id: Rep[Option[Long]] = column[Option[Long]]("emitent_id")

  def gosreg: Rep[String] = column[String]("gosreg")

  def `type`: Rep[String] = column[String]("type")

  def group: Rep[String] = column[String]("group")

  def primary_boardid: Rep[String] = column[String]("primary_boardid")

  def marketprice_boardid: Rep[String] = column[String]("marketprice_boardid")

  def fk_emitent_id: ForeignKeyQuery[EmitentModel, EmitentDto] = foreignKey("fk_emitent_id", emitent_id,
    TableQuery[EmitentModel])(_.id, ForeignKeyAction.Restrict, ForeignKeyAction.Cascade)

  override def * : ProvenShape[SecurityDto] = (id, secid, shortname, regnumber, name, isin, is_traded, emitent_id,
    gosreg, `type`, group, primary_boardid, marketprice_boardid) <> (SecurityDto.tupled, SecurityDto.unapply)
}
