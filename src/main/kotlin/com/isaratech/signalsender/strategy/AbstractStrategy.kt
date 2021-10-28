package com.isaratech.signalsender.strategy

import com.binance.api.client.domain.market.CandlestickInterval
import org.slf4j.LoggerFactory
import org.ta4j.core.BarSeries
import org.ta4j.core.Strategy
import org.ta4j.core.TradingRecord

abstract class AbstractStrategy {
    protected var iMAShort = 12
    protected var iMALong = 26
    protected val log = LoggerFactory.getLogger(this.javaClass)

    abstract fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy
    abstract fun execute(series: BarSeries?, barDuration: CandlestickInterval?): TradingRecord
    abstract fun getName(): String
}