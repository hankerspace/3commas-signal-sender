package com.isaratech.signalsender.service.analysis

import com.isaratech.signalsender.strategy.ta4jindicator.BuySignalIndicator
import com.isaratech.signalsender.service.analysis.AbstractAnalysisService
import com.isaratech.signalsender.utils.Utils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.ta4j.core.num.PrecisionNum
import java.math.BigDecimal

@Service
class SimpleAnalysisService() : AbstractAnalysisService() {


    @Value("\${bot.analysis.time-frame}")
    private val timeframe: Int = 30

    @Value("\${bot.analysis.momentum}")
    private val momentum: String = "0.25"

    /**
     * Calculates a buy signal based several technical analysis indicators
     *
     * @param tickers the tickers to analyze
     * @return true if pair is good for buying, false otherwise
     */
    override fun getInternalBuySignal(): Boolean {
        val buySignal = BuySignalIndicator(cachedBarSeries, timeframe, PrecisionNum.valueOf(momentum))

        // Need to go through all index to initialize indicators
        for (i in cachedBarSeries.beginIndex..cachedBarSeries.endIndex) {
            buySignal.getValue(i)
        }

        val signal: Boolean = buySignal.getValue(cachedBarSeries.endIndex)
        //log.debug("buy_trigger: $signal (end time=${tickers.lastBar.endTime})")
        return signal
    }

    override fun getName(): String {
        return "SIMPLE ANALYSIS"
    }
}
