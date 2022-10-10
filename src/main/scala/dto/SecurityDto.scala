package com.moex.app
package dto

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 7:46 PM
 */

case class SecurityDto(id: Option[Long], secid: String, shortname: String, regnumber: String, name: String,
                       isin: String, is_traded: Option[Long], emitent_id: Option[Long], gosreg: String, `type`: String,
                       group: String, primary_boardid: String, marketprice_boardid: String)
