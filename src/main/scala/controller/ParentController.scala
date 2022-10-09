package com.moex.app
package controller

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.cors

/**
 * @author zerdicorp
 * @project moex
 * @created 10/6/22 - 4:34 PM
 */

object ParentController {
  private val importController: ImportController = new ImportController
  private val securityController: SecurityController = new SecurityController
  private val emitentController: EmitentController = new EmitentController
  private val historyUnitController: HistoryUnitController = new HistoryUnitController

  val routes: Route = cors() {
    pathPrefix("api" / "v1") {
      importController.routes ~
        securityController.routes ~
        emitentController.routes ~
        historyUnitController.routes
    }
  }
}
