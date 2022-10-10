package com.moex.app
package service

import dto.HistoryUnitDto
import repository.HistoryUnitRepository

import java.time.LocalDate
import scala.concurrent.Future

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 7:01 PM
 */

object HistoryUnitService {
  def findAllJoined(orderBy: String, emitentTitle: String, tradedate: String):
  Future[Seq[(String, String, String, String, Option[LocalDate], Option[Double], Option[Double], Option[Double])]] = {
    HistoryUnitRepository.findAllJoined(orderBy, emitentTitle, tradedate)
  }

  def findAll(): Future[Seq[HistoryUnitDto]] = {
    HistoryUnitRepository.findAll()
  }

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
