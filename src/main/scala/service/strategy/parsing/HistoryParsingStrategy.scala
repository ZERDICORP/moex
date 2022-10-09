package com.moex.app
package service.strategy.parsing

import constant.XmlType
import dto.HistoryUnitDto
import service.{HistoryUnitService, ImportService, SecurityService}
import util.ParserUtil.parseDouble

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.unmarshalling.Unmarshal

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.{ExecutorService, Executors}
import scala.collection.immutable.HashSet
import scala.concurrent.ExecutionContext
import scala.language.postfixOps
import scala.util.{Failure, Success}
import scala.xml.Node

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 3:15 AM
 */

private class HistoryParsingStrategy extends ParsingStrategy with XmlType {
  private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  private val executorService: ExecutorService = Executors.newFixedThreadPool(1000)

  override def accepts(xmlType: String): Boolean = {
    xmlType.toUpperCase.equals(HISTORY)
  }

  override def parse(xml: Node)(implicit exc: ExecutionContext, actorSystem: ActorSystem[Nothing]): Unit = {
    var missingSecurities: Set[String] = HashSet()

    xml \\ "row" foreach { row =>
      val historyUnitDto: HistoryUnitDto = HistoryUnitDto(
        Option.empty,
        row \ "@BOARDID" text,
        LocalDate.parse(row \ "@TRADEDATE" text, dateFormatter),
        row \ "@SHORTNAME" text,
        row \ "@SECID" text,
        parseDouble(row \ "@NUMTRADES" text),
        parseDouble(row \ "@VALUE" text),
        parseDouble(row \ "@OPEN" text),
        parseDouble(row \ "@LOW" text),
        parseDouble(row \ "@HIGH" text),
        parseDouble(row \ "@LEGALCLOSEPRICE" text),
        parseDouble(row \ "@WAPRICE" text),
        parseDouble(row \ "@CLOSE" text),
        parseDouble(row \ "@VOLUME" text),
        parseDouble(row \ "@MARKETPRICE2" text),
        parseDouble(row \ "@MARKETPRICE3" text),
        parseDouble(row \ "@ADMITTEDQUOTE" text),
        parseDouble(row \ "@MP2VALTRD" text),
        parseDouble(row \ "@MARKETPRICE3TRADESVALUE" text),
        parseDouble(row \ "@ADMITTEDVALUE" text),
        parseDouble(row \ "@WAVAL" text))

      HistoryUnitService.save(historyUnitDto)

      SecurityService.findBySecid(historyUnitDto.secid)
        .onComplete {
          case Success(found) =>
            if (found.isEmpty) {
              missingSecurities += historyUnitDto.secid
            }
          case Failure(e) => throw e
        }
    }

    missingSecurities foreach { secid =>
      executorService.execute(new Request(secid)(exc, actorSystem))
    }
  }

  private class Request(secid: String)(implicit exc: ExecutionContext, actorSystem: ActorSystem[Nothing]) extends Runnable {
    override def run(): Unit = {
      Http().singleRequest(HttpRequest(uri = "http://iss.moex.com/iss/securities.xml?q=" + secid))
        .flatMap(response => Unmarshal(response.entity).to[String])
        .onComplete {
          case Success(body) => ImportService.importXml(body)(exc)
          case Failure(e) => throw e
        }
    }
  }
}
