package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.stereotype.Component
import org.ta4j.core.*

import org.ta4j.core.trading.rules.OverIndicatorRule

import org.ta4j.core.indicators.EMAIndicator

import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.BaseStrategy

import org.ta4j.core.trading.rules.StopLossRule

import org.ta4j.core.trading.rules.CrossedUpIndicatorRule

import org.ta4j.core.indicators.helpers.HighPriceIndicator

import org.ta4j.core.indicators.helpers.LowPriceIndicator

import org.ta4j.core.trading.rules.CrossedDownIndicatorRule

import org.ta4j.core.indicators.helpers.MultiplierIndicator

import org.ta4j.core.indicators.helpers.DifferenceIndicator

import org.ta4j.core.indicators.bollinger.BollingerBandsLowerIndicator

import org.ta4j.core.indicators.bollinger.BollingerBandsMiddleIndicator

import org.ta4j.core.indicators.statistics.StandardDeviationIndicator




/**
 * http://www.investopedia.com/terms/s/scalping.asp
 * http://forexop.com/strategy/simple-range-scalper/
 *
 *  STRAT FOR RT
 */
@Component
class SimpleRangeScalperShortTermStrategy : AbstractStrategy() {
    private val emaForBollingerBandValue = 14
    private val takeProfitValue = 0.01

    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrice = ClosePriceIndicator(series)

        val ema = EMAIndicator(closePrice, emaForBollingerBandValue)
        val standardDeviation = StandardDeviationIndicator(closePrice, emaForBollingerBandValue)
        val middleBollingerBand = BollingerBandsMiddleIndicator(ema)
        val lowerBollingeBand = BollingerBandsLowerIndicator(middleBollingerBand, standardDeviation)

        //SHORT TERM start, long term below
        val d_middle_lower = DifferenceIndicator(middleBollingerBand, lowerBollingeBand)

        // exit if half way down to middle reached
        val threshold = MultiplierIndicator(d_middle_lower, 0.5)

        val entrySignal: Rule = CrossedDownIndicatorRule(LowPriceIndicator(series), lowerBollingeBand) //LowestValueIndicator?

        val entrySignal2: Rule = OverIndicatorRule(HighPriceIndicator(series), lowerBollingeBand)

        val exitSignal: Rule = CrossedUpIndicatorRule(closePrice, threshold)
        val exitSignal2: Rule = StopLossRule(closePrice, takeProfitValue) // stop loss long = stop gain short?


        return BaseStrategy("SimpleRangeScalperShortTermStrategy", entrySignal.and(entrySignal2), exitSignal.or(exitSignal2))
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
        return "SIMPLE RANGE SCALPER SHORT TERM STRATEGY"
    }
}