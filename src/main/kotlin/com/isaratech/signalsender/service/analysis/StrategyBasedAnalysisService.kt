package com.isaratech.signalsender.service.analysis

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import com.isaratech.signalsender.service.analysis.AbstractAnalysisService
import org.springframework.stereotype.Service
import org.ta4j.core.*

@Service
class StrategyBasedAnalysisService() : AbstractAnalysisService() {

    var strategy: AbstractStrategy? = null

    /**
     * Calculates a buy signal based several technical analysis indicators
     *
     * @param tickers the tickers to analyze
     * @return true if pair is good for buying, false otherwise
     */
    override fun getInternalBuySignal(): Boolean {
        val ta4jStrategy: Strategy = strategy!!.buildStrategy(cachedBarSeries, CandlestickInterval.ONE_MINUTE)
        if (ta4jStrategy.shouldEnter(cachedBarSeries.endIndex, tradingRecord)) {
            // Our strategy should enter
            return true
        }
        return false
    }

    override fun getName(): String {
        return strategy!!.getName()
    }
}
