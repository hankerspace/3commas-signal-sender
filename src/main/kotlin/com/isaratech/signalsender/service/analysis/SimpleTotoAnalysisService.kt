package com.isaratech.signalsender.service.analysis

import com.isaratech.signalsender.strategy.indicator.DBB
import com.isaratech.signalsender.strategy.indicator.Indicator
import com.isaratech.signalsender.strategy.indicator.MACD
import com.isaratech.signalsender.strategy.indicator.RSI
import com.isaratech.signalsender.service.analysis.AbstractAnalysisService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*
import java.util.stream.Collectors


@Service
class SimpleTotoAnalysisService : AbstractAnalysisService() {

    var multiplicator = 1
    var confluenceBuy = 2
    var confluenceSell = -3

    var trailingStopWinEnabled = false
    var stopWin = 1.04 // 10%
    var trailingStopWin = 0.1 // 1%

    var trailingStoploss = false
    var stopLoss = 0.5 //10%

    var stopLossIncrementByMinute = 0.001 // 1%
    var stopLossDividerByMinute = 1 // /1
    var stopLossByIncrementEnabled = false



    var fixedStoplossEnabled = false
    var fixedStoploss = 1.02 //2% if price reach +1% dont let it go below

    var macdLevel = 0.25
    var rsiPositiveMin = 15
    var rsiPositiveMax = 30
    var rsiNegativeMin = 70
    var rsiNegativeMax = 80

    fun createIndicators(): MutableList<Indicator> {
        val closingPrices: List<Double> = cachedBarSeries.barData.stream().map { candle -> candle.closePrice.doubleValue() }.collect(Collectors.toList())
        val indicators: MutableList<Indicator> = ArrayList<Indicator>()
        val rsi = RSI(closingPrices, 14 * multiplicator)
        rsi.positiveMin = rsiPositiveMin
        rsi.positivseMax = rsiPositiveMax
        rsi.negativeMin = rsiNegativeMin
        rsi.negativeMax = rsiNegativeMax

        val macd = MACD(closingPrices, 12 * multiplicator, 26 * multiplicator, 9 * multiplicator)
        macd.change = macdLevel

        val dbb = DBB(closingPrices, 20 * multiplicator)

        indicators.add(rsi)
        indicators.add(macd)
        indicators.add(dbb)
        return indicators
    }

    /**
     * Calculates a buy signal based several technical analysis indicators
     *
     * @param tickers the tickers to analyze
     * @return true if pair is good for buying, false otherwise
     */
    override fun getInternalBuySignal(): Boolean {

        var sumConfluence = 0
        createIndicators().forEach {
            sumConfluence += it.check(cachedBarSeries.lastBar.closePrice.doubleValue())
        }

        if (sumConfluence >= this.confluenceBuy) {
            //log.info("Buy order because : $expl")
            return true
        }

        return false
    }


    override fun getName(): String {
        return "TOTO ANALYSIS STRATEGY"
    }

    override fun toString(): String {
        return "SimpleTotoAnalysisService(multiplicator=$multiplicator, confluenceBuy=$confluenceBuy, confluenceSell=$confluenceSell, stopWin=$stopWin, stopLoss=$stopLoss, stopLossIncrementByMinute=$stopLossIncrementByMinute, macdLevel=$macdLevel, rsiPositiveMin=$rsiPositiveMin, rsiPositiveMax=$rsiPositiveMax, rsiNegativeMin=$rsiNegativeMin, rsiNegativeMax=$rsiNegativeMax)"
    }


}
