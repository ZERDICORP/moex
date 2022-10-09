package com.moex.app
package model

import dto.EmitentDto

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 8:11 PM
 */

class EmitentModel(tag: Tag) extends Table[EmitentDto](tag, "emitent") {
  def id: Rep[Option[Long]] = column[Option[Long]]("id", O.PrimaryKey)

  def title: Rep[String] = column[String]("title")

  def inn: Rep[String] = column[String]("inn")

  def okpo: Rep[String] = column[String]("okpo")

  override def * : ProvenShape[EmitentDto] = (id, title, inn, okpo) <> (EmitentDto.tupled, EmitentDto.unapply)
}
