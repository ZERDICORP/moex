package com.moex.app
package model

import dto.TestDto

import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

/**
 * @author zerdicorp
 * @project moex
 * @created 10/6/22 - 11:33 AM
 */

class TestModel(tag: Tag) extends Table[TestDto](tag, "test") {
  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def email = column[String]("email")

  override def * = (id, name, email) <> (TestDto.tupled, TestDto.unapply)
}