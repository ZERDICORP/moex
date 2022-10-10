package com.moex.app
package service

import constant.XmlStatus
import dto.XmlDto
import repository.XmlRepository
import service.strategy.parsing.ParsingStrategy
import service.strategy.parsing.resolver.ParsingStrategyResolver

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.model.{StatusCode, StatusCodes}
import akka.stream.Materializer
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import scala.concurrent.{ExecutionContext, Future}
import scala.io.Source.fromFile
import scala.language.postfixOps
import scala.util.{Failure, Success, Using}
import scala.xml.XML

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 12:58 AM
 */

object XmlService extends XmlStatus {
  def parse(xmlDto: XmlDto)(implicit exc: ExecutionContext, actorSystem: ActorSystem[Nothing]): Unit = {
    XmlRepository.updateStatusById(xmlDto.id, PARSING_STARTED)

    val filePath: String = "data/" + xmlDto.id.get
    Using(fromFile(filePath)) { source =>
      val plainXml: String = source.mkString
      val xml = XML.loadString(plainXml)
      val data = xml \\ "data" head

      try {
        val parsingStrategy: ParsingStrategy = ParsingStrategyResolver.resolve(data \ "@id" text)
        parsingStrategy.parse(data, xmlDto.secid)(exc, actorSystem)

        XmlRepository.updateStatusById(xmlDto.id, PARSING_COMPLETED)
      } catch {
        case e: Throwable =>
          XmlRepository.updateStatusById(xmlDto.id, PARSING_FAILED)
      }
    }

    Files.deleteIfExists(Paths.get(filePath))
  }

  def findAllWithStatus(status: String)(implicit exc: ExecutionContext): Future[Seq[XmlDto]] = {
    XmlRepository.findAllWithStatus(status)
  }

  def save(source: Source[ByteString, Any])
          (implicit mtz: Materializer, exc: ExecutionContext): StatusCode = {
    XmlRepository.save(XmlDto(Option.empty, NEW, Option.empty))
      .onComplete {
        case Success(xmlDto: XmlDto) =>
          source.runWith(FileIO.toPath(Paths.get("data").resolve(xmlDto.id.get.toString)))
            .onComplete {
              case Success(_) => StatusCodes.OK
              case Failure(_) => StatusCodes.InternalServerError
            }
        case Failure(_) => StatusCodes.InternalServerError
      }
    StatusCodes.OK
  }

  def save(data: String, secid: Option[String])(implicit exc: ExecutionContext): StatusCode = {
    XmlRepository.save(XmlDto(Option.empty, NEW, secid))
      .onComplete {
        case Success(xmlDto: XmlDto) =>
          Files.write(Paths.get(s"data/${xmlDto.id.get}"), data.getBytes(StandardCharsets.UTF_8))
        case Failure(_) => StatusCodes.InternalServerError
      }
    StatusCodes.OK
  }
}
