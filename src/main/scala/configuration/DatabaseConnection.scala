package com.moex.app
package configuration

import slick.jdbc.JdbcBackend.Database

/**
 * @author zerdicorp
 * @project moex
 * @created 10/6/22 - 11:02 AM
 */

trait DatabaseConnection {
  lazy val db = Database.forConfig("postgres")
}
