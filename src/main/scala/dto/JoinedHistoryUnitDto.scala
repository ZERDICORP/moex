package com.moex.app
package dto

import java.time.LocalDate

/**
 * @author zerdicorp
 * @project moex
 * @created 10/10/22 - 5:58 PM
 */

case class JoinedHistoryUnitDto(secid: String, regnumber: String, name: String, emitent_title: String,
                                tradedate: Option[LocalDate], numtrades: Option[Double], open: Option[Double],
                                close: Option[Double])
