package com.moex.app
package service

import dto.SecurityDto
import repository.SecurityRepository

import scala.concurrent.Future

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 7:01 PM
 */

object SecurityService {
  def findAll(): Future[Seq[SecurityDto]] = {
    SecurityRepository.findAll()
  }

  def update(securityDto: SecurityDto): Future[Int] = {
    SecurityRepository.update(securityDto)
  }

  def deleteById(id: Long): Future[Int] = {
    SecurityRepository.deleteById(id)
  }

  def findById(id: Long): Future[Option[SecurityDto]] = {
    SecurityRepository.findById(id)
  }

  def findBySecid(secid: String): Future[Seq[SecurityDto]] = {
    SecurityRepository.findBySecid(secid)
  }

  def save(security: SecurityDto): Unit = {
    SecurityRepository.save(security)
  }
}
