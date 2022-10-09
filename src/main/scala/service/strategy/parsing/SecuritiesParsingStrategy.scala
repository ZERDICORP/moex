package com.moex.app
package service.strategy.parsing

import constant.XmlType
import dto.{EmitentDto, SecurityDto}
import service.{EmitentService, SecurityService}
import util.ParserUtil.parseLong

import akka.actor.typed.ActorSystem

import scala.concurrent.ExecutionContext
import scala.language.postfixOps
import scala.xml.Node

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 3:15 AM
 */

private class SecuritiesParsingStrategy extends ParsingStrategy with XmlType {
  override def accepts(xmlType: String): Boolean = {
    xmlType.toUpperCase.equals(SECURITIES)
  }

  override def parse(xml: Node)(implicit exc: ExecutionContext, actorSystem: ActorSystem[Nothing]): Unit = {
    xml \\ "row" foreach { row =>
      val emitentDto: EmitentDto = EmitentDto(
        parseLong(row \ "@emitent_id" text),
        row \ "@emitent_title" text,
        row \ "@emitent_inn" text,
        row \ "@emitent_okpo" text)

      EmitentService.save(emitentDto)

      val securityDto: SecurityDto = SecurityDto(
        parseLong(row \ "@id" text),
        row \ "@secid" text,
        row \ "@shortname" text,
        row \ "@regnumber" text,
        row \ "@name" text,
        row \ "@isin" text,
        parseLong(row \ "@is_traded" text),
        parseLong(row \ "@emitent_id" text),
        row \ "@gosreg" text,
        row \ "@type" text,
        row \ "@group" text,
        row \ "@primary_boardid" text,
        row \ "@marketprice_boardid" text)

      SecurityService.save(securityDto)
    }
  }
}
