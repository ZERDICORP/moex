package com.moex.app
package model

import dto.TestDto

import slick.jdbc.PostgresProfile.api._
import slick.lifted.{ProvenShape, Tag}

/**
 * @author zerdicorp
 * @project moex
 * @created 10/6/22 - 11:33 AM
 */

class TestModel(tag: Tag) extends Table[TestDto](tag, "test") {
  def id: Rep[Option[Long]] = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def name: Rep[String] = column[String]("name")

  def email: Rep[String] = column[String]("email")

  override def * : ProvenShape[TestDto] = (id, name, email) <> (TestDto.tupled, TestDto.unapply)
}