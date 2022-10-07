package com.example.akka_http
package controller

import service.ImportService

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.Materializer
import akka.stream.scaladsl.FileIO

import java.nio.file.Paths
import scala.io.Source.fromFile
import scala.util.{Failure, Success, Using}

/**
 * @author zerdicorp
 * @project akka_http_example
 * @created 10/6/22 - 11:02 AM
 */

private class ImportController {
  val routes: Route = {
    post {
      path("import") {
        extractRequestContext { ctx =>
          implicit val materializer: Materializer = ctx.materializer
          fileUpload("file") {
            case (fileInfo, fileStream) =>
              val sink = FileIO.toPath(Paths.get("data").resolve(fileInfo.fileName))
              val writeResult = fileStream.runWith(sink)
              onSuccess(writeResult) { result =>
                result.status match {
                  case Success(_) =>
                    Using(fromFile("data/" + fileInfo.fileName)) { source =>
                      ImportService.importXml(source.mkString)
                    }
                    complete(StatusCodes.OK)
                  case Failure(e) => throw e
                }
              }
          }
        }
      }
    }
  }
}