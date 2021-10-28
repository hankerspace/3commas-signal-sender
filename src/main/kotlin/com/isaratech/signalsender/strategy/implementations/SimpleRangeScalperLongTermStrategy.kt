package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.stereotype.Component
import org.ta4j.core.*

import org.ta4j.core.trading.rules.UnderIndicatorRule

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

import org.ta4j.core.indicators.bollinger.BollingerBandsMiddleIndicator

import org.ta4j.core.indicators.statistics.StandardDeviationIndicator
import org.ta4j.core.trading.rules.StopGainRule

import org.ta4j.core.indicators.bollinger.BollingerBandsUpperIndicator







/**
 * http://www.investopedia.com/terms/s/scalping.asp
 * http://forexop.com/strategy/simple-range-scalper/
 *
 *  STRAT FOR RT
 */
@Component
class SimpleRangeScalperLongTermStrategy : AbstractStrategy() {
    private val emaForBollingerBandValue = 14
    private val takeProfitValue = 0.02

    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrice = ClosePriceIndicator(series)

        val ema = EMAIndicator(closePrice, emaForBollingerBandValue)
        val standardDeviation = StandardDeviationIndicator(closePrice, emaForBollingerBandValue)
        val middleBollingerBand = BollingerBandsMiddleIndicator(ema)
        val upperBollingerBand = BollingerBandsUpperIndicator(middleBollingerBand, standardDeviation)

        val d_upper_middle = DifferenceIndicator(upperBollingerBand, middleBollingerBand)
        // exit if half way up to middle reached
        // exit if half way up to middle reached
        val threshold = MultiplierIndicator(d_upper_middle, java.lang.Double.valueOf(0.5))

        val entrySignal: Rule = CrossedUpIndicatorRule(HighPriceIndicator(series), upperBollingerBand)
        val entrySignal2: Rule = UnderIndicatorRule(LowPriceIndicator(series), upperBollingerBand)

        val exitSignal: Rule = CrossedDownIndicatorRule(closePrice, threshold)
        val exitSignal2: Rule = StopGainRule(closePrice, takeProfitValue)
        val exitSignal3: Rule = StopLossRule(closePrice, takeProfitValue)

        return BaseStrategy("SimpleRangeScalperLongTermStrategy", entrySignal.and(entrySignal2), exitSignal.or(exitSignal2).or(exitSignal3))
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
        return "SIMPLE RANGE SCALPER LONG TERM STRATEGY"
    }
}