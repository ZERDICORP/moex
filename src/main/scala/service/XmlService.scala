package com.moex.app
package service

import constant.XmlStatus
import dto.XmlDto
import repository.XmlRepository
import service.strategy.parsing.ParsingStrategy
import service.strategy.parsing.resolver.ParsingStrategyResolver

import akka.http.scaladsl.model.{StatusCode, StatusCodes}
import akka.stream.Materializer
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString

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
  def parse(xmlDto: XmlDto): Unit = {
    XmlRepository.updateStatusById(xmlDto.id, PARSING_STARTED)
    println("Parsing started..")

    val filePath: String = "data/" + xmlDto.id.get
    Using(fromFile(filePath)) { source =>
      val plainXml: String = source.mkString
      val xml = XML.loadString(plainXml)
      val data = xml \\ "data" head

      try {
        val parsingStrategy: ParsingStrategy = ParsingStrategyResolver.resolve(data \ "@id" text)
        parsingStrategy.parse(data)

        XmlRepository.updateStatusById(xmlDto.id, PARSING_COMPLETED)
        println("Parsing completed..")
      } catch {
        case e: Throwable =>
          XmlRepository.updateStatusById(xmlDto.id, PARSING_FAILED)
          println("Parsing failed.. " + e.getMessage)
      }
    }

    Files.deleteIfExists(Paths.get(filePath))
    println("File deleted..")
  }

  def findAllWithStatus(status: String)(implicit exc: ExecutionContext): Future[Seq[XmlDto]] = {
    XmlRepository.findAllWithStatus(status)
  }

  def save(source: Source[ByteString, Any])
          (implicit mtz: Materializer, exc: ExecutionContext): StatusCode = {
    XmlRepository.save(XmlDto(Option.empty, NEW))
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
}
