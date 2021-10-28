package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.ta4j.core.*
import org.ta4j.core.indicators.EMAIndicator
import org.ta4j.core.indicators.MACDIndicator
import org.ta4j.core.indicators.StochasticOscillatorKIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.indicators.statistics.SimpleLinearRegressionIndicator
import org.ta4j.core.indicators.statistics.SimpleLinearRegressionIndicator.SimpleLinearRegressionType
import org.ta4j.core.num.PrecisionNum
import org.ta4j.core.trading.rules.*

// FIXME Do not use, it requires more tuning
class AlexStrategy : AbstractStrategy() {
    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        return customMacdStrategy(series, barDuration)
    }

    private fun customMovingMomentum(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrice = ClosePriceIndicator(series)

        // The bias is bearish when the shorter-moving average moves below the longer moving average.
        val shortEma = EMAIndicator(closePrice, iMAShort)
        val longEma = EMAIndicator(closePrice, iMALong)
        val stochasticOscillK = StochasticOscillatorKIndicator(series, 14)
        val macd = MACDIndicator(closePrice, iMAShort, iMALong)
        val emaMacd = EMAIndicator(macd, 18)

        // Entry rule
        val entryRule: Rule = OverIndicatorRule(shortEma, longEma) // Trend
            .and(CrossedDownIndicatorRule(stochasticOscillK, PrecisionNum.valueOf(20))) // Signal 1
            .and(OverIndicatorRule(macd, emaMacd)) // Signal 2

        // Exit rule
        val exitRule: Rule = UnderIndicatorRule(shortEma, longEma) // Trend
            .and(CrossedUpIndicatorRule(stochasticOscillK, PrecisionNum.valueOf(80))) // Signal 1
            .and(UnderIndicatorRule(macd, emaMacd))
            .or(StopGainRule(closePrice, PrecisionNum.valueOf(0.55)))
            .or(StopLossRule(closePrice, PrecisionNum.valueOf(0.5)))

        return BaseStrategy(entryRule, exitRule)
    }


    @Deprecated("not really good")
    private fun linearRegressionBasedStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrice = ClosePriceIndicator(series)
        val shortSlopeIndicator = SimpleLinearRegressionIndicator(closePrice, 14, SimpleLinearRegressionType.SLOPE)
        val longSlopeIndicator = SimpleLinearRegressionIndicator(closePrice, 26, SimpleLinearRegressionType.SLOPE)

        // Entry rule
        val positiveShortLinearRegressionRule = OverIndicatorRule(shortSlopeIndicator, PrecisionNum.valueOf(0.0))
        val positiveLinearRegressionRule = OverIndicatorRule(longSlopeIndicator, PrecisionNum.valueOf(0.0))
        val shortAboveLongLinearRegressionRule = OverIndicatorRule(shortSlopeIndicator, longSlopeIndicator)
//        val entryRule = positiveLinearRegressionRule.and(shortAboveLongLinearRegressionRule)
        val entryRule = InSlopeRule(shortSlopeIndicator, PrecisionNum.valueOf(0.0)).and(positiveShortLinearRegressionRule)

        // Exit rule
        val negativeShortLinearRegressionRule = UnderIndicatorRule(shortSlopeIndicator, PrecisionNum.valueOf(0.0))
        val stopLossRule = StopLossRule(closePrice, PrecisionNum.valueOf(0.5))
        val takeProfitRule = StopGainRule(closePrice, PrecisionNum.valueOf(0.6))
        val exitRule = negativeShortLinearRegressionRule.or(stopLossRule).or(takeProfitRule)

        return BaseStrategy(entryRule, exitRule)
    }

    @Deprecated("not working either")
    private fun customMacdStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrice = ClosePriceIndicator(series)

        val macd = MACDIndicator(closePrice, 12, 26)
        val emaMacd = EMAIndicator(macd, 9)
        // Entry rule
        val entryRule = OverIndicatorRule(macd, emaMacd) // Trend

        // Exit rule
        val exitRule = UnderIndicatorRule(macd, emaMacd) // Trend
            .or(StopLossRule(closePrice, PrecisionNum.valueOf(0.50)))
            .or(StopGainRule(closePrice, PrecisionNum.valueOf(0.55)))

        return BaseStrategy(entryRule, exitRule, 100)
    }

    override fun execute(series: BarSeries?, barDuration: CandlestickInterval?): TradingRecord {
        // Building the trading strategy
        val strategy: Strategy = buildStrategy(series, barDuration)

        // Running the strategy
        val seriesManager = BarSeriesManager(series)
        val tradingRecord: TradingRecord = seriesManager.run(strategy)

        // Analysis
        return tradingRecord
    }

    override fun getName(): String {
        return "ALEX STRATEGY"
    }
}
