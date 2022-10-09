package com.moex.app
package service.strategy.parsing.resolver

import service.strategy.parsing.{HistoryParsingStrategy, ParsingStrategy, SecuritiesParsingStrategy}

/**
 * @author zerdicorp
 * @project moex
 * @created 10/9/22 - 2:56 AM
 */

object ParsingStrategyResolver {
  private val strategies: List[ParsingStrategy] = List(
    new HistoryParsingStrategy,
    new SecuritiesParsingStrategy
  )

  def resolve(xmlType: String): ParsingStrategy = {
    strategies.foreach { strategy =>
      if (strategy accepts xmlType) {
        return strategy
      }
    }
    println(strategies.length)
    throw new IllegalStateException(s"unknown type of xml: \"$xmlType\"")
  }
}
