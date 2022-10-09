package com.moex.app
package controller

import service.ImportService

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

/**
 * @author zerdicorp
 * @project moex
 * @created 10/6/22 - 11:02 AM
 */

private class ImportController {
  val routes: Route = {
    post {
      path("import") {
        extractRequestContext { ctx =>
          extractExecutionContext { exc =>
            fileUpload("file") {
              case (_, fileStream) =>
                complete(ImportService.importXml(fileStream)(ctx.materializer, exc))
            }
          }
        }
      }
    }
  }
}