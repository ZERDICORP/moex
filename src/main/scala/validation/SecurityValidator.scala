package com.moex.app
package validation

import dto.SecurityDto

/**
 * @author zerdicorp
 * @project moex
 * @created 10/10/22 - 5:22 PM
 */

object SecurityValidator extends App {
  private def validName(name: String): Either[String, String] = {
    if (name.matches("^[а-яА-Я0-9 ]+$")) {
      Right(name)
    } else {
      Left("name can only contain cyrillic, numbers and spaces")
    }
  }

  def validate(securityDto: SecurityDto): Either[String, SecurityDto] = {
    for {
      name <- validName(securityDto.name)
    } yield securityDto
  }
}
