package com.moex.app
package model

import dto.XmlDto

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

/**
 * @author zerdicorp
 * @project moex
 * @created 10/6/22 - 11:33 AM
 */

class XmlModel(tag: Tag) extends Table[XmlDto](tag, "xml") {
  def id: Rep[Option[Long]] = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def status: Rep[String] = column[String]("status")

  override def * : ProvenShape[XmlDto] = (id, status) <> (XmlDto.tupled, XmlDto.unapply)
}