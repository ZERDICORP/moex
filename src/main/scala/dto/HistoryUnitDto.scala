package com.moex.app
package dto

import java.time.LocalDate

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 6:39 PM
 */

final case class HistoryUnitDto(id: Option[Long], boardid: String, tradedate: Option[LocalDate], shortname: String,
                                secid: String, numtrades: Option[Double], value: Option[Double], open: Option[Double],
                                low: Option[Double], high: Option[Double], legalcloseprice: Option[Double],
                                waprice: Option[Double], close: Option[Double], volume: Option[Double],
                                marketprice2: Option[Double], marketprice3: Option[Double],
                                admittedquote: Option[Double], mp2valtrd: Option[Double],
                                marketprice3tradesvalue: Option[Double], admittedvalue: Option[Double],
                                waval: Option[Double])
