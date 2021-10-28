package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.stereotype.Component
import org.ta4j.core.*

import org.ta4j.core.trading.rules.UnderIndicatorRule

import org.ta4j.core.trading.rules.OverIndicatorRule

import org.ta4j.core.indicators.MACDIndicator

import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.BaseStrategy

import org.ta4j.core.trading.rules.StopLossRule

import org.ta4j.core.num.PrecisionNum

import org.ta4j.core.trading.rules.TrailingStopLossRule

import org.ta4j.core.trading.rules.StopGainRule

import org.ta4j.core.indicators.RSIIndicator

import org.ta4j.core.indicators.SMAIndicator

import org.ta4j.core.indicators.helpers.VolumeIndicator

import org.ta4j.core.indicators.pivotpoints.PivotLevel

import org.ta4j.core.indicators.pivotpoints.StandardReversalIndicator

import org.ta4j.core.indicators.pivotpoints.TimeLevel

import org.ta4j.core.indicators.pivotpoints.PivotPointIndicator

import org.ta4j.core.indicators.helpers.LowestValueIndicator

import org.ta4j.core.indicators.helpers.OpenPriceIndicator







/**
 */
@Component
class PivotPointReversalStrategy : AbstractStrategy() {

    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrice = ClosePriceIndicator(series)
        val openPriceIndicator1 = OpenPriceIndicator(series)
        val lowestPrice = LowestValueIndicator(ClosePriceIndicator(series),
                10)
        val pivotPoints = PivotPointIndicator(series, TimeLevel.BARBASED)
        val reversalIndicator = StandardReversalIndicator(pivotPoints,
                PivotLevel.SUPPORT_1)
        val tenSMA = SMAIndicator(closePrice, 10)
        val fiftySMA1 = SMAIndicator(closePrice, 50)
        val twoHundredSMA = SMAIndicator(closePrice, 200)
        val macdIndicator = MACDIndicator(closePrice)

        val volumeIndicator = VolumeIndicator(series)
        val voluemSma10 = SMAIndicator(volumeIndicator, 10)
        val voluemSma50 = SMAIndicator(volumeIndicator, 50)

        val entryRule = OverIndicatorRule(reversalIndicator, lowestPrice)
                .and(OverIndicatorRule(macdIndicator, 0.01))
                .and(OverIndicatorRule(openPriceIndicator1, reversalIndicator))
                .and(OverIndicatorRule(openPriceIndicator1, tenSMA))
                .and(OverIndicatorRule(voluemSma10, voluemSma50))
                .and(UnderIndicatorRule(RSIIndicator(closePrice, 14), 55))
                .and(OverIndicatorRule(RSIIndicator(closePrice, 14), 30))
                .and(UnderIndicatorRule(closePrice, fiftySMA1))

        val exitRule = StopGainRule(closePrice, 5)
                .or(StopLossRule(closePrice, 2))
                .or(TrailingStopLossRule(closePrice, PrecisionNum.valueOf(1)))

        return BaseStrategy(
                entryRule, exitRule
        )
    }

    override fun execute(series: BarSeries?, barDuration: CandlestickInterval?): TradingRecord {
        // Building the trading strategy
        val strategy: Strategy = buildStrategy(series, barDuration)

        // Running the strategy
        val seriesManager = BarSeriesManager(series)
        val tradingRecord: TradingRecord = seriesManager.run(strategy)
        //println("Number of trades for the strategy: " + tradingRecord.tradeCount)

        // Analysis
        //println("Total profit for the strategy: " + TotalProfitCriterion().calculate(series, tradingRecord))
        return tradingRecord
    }

    override fun getName(): String {
        return "PIVOT POINT REVERSAL STRATEGY"
    }
}