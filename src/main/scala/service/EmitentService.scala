package com.moex.app
package service

import dto.EmitentDto
import repository.EmitentRepository

import scala.concurrent.Future

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 8:18 PM
 */

object EmitentService {
  def update(emitentDto: EmitentDto): Future[Int] = {
    EmitentRepository.update(emitentDto)
  }

  def deleteById(id: Long): Future[Int] = {
    EmitentRepository.deleteById(id)
  }

  def findById(id: Long): Future[Option[EmitentDto]] = {
    EmitentRepository.findById(id)
  }

  def save(emitent: EmitentDto): Unit = {
    EmitentRepository.save(emitent)
  }
}
