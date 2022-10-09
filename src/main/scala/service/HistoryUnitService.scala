package com.moex.app
package service

import dto.HistoryUnitDto
import repository.HistoryUnitRepository

import scala.concurrent.Future

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 7:01 PM
 */

object HistoryUnitService {
  def update(historyUnitDto: HistoryUnitDto): Future[Int] = {
    HistoryUnitRepository.update(historyUnitDto)
  }

  def deleteById(id: Long): Future[Int] = {
    HistoryUnitRepository.deleteById(id)
  }

  def findById(id: Long): Future[Option[HistoryUnitDto]] = {
    HistoryUnitRepository.findById(id)
  }

  def save(historyUnit: HistoryUnitDto): Unit = {
    HistoryUnitRepository.save(historyUnit)
  }
}
