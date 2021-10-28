package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.stereotype.Component
import org.ta4j.core.*
import org.ta4j.core.indicators.EMAIndicator
import org.ta4j.core.indicators.MACDIndicator
import org.ta4j.core.indicators.StochasticOscillatorKIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.trading.rules.*
import java.math.BigDecimal


/**
 * Moving momentum strategy.
 *
 *
 *  @see
 * [
 * http://stockcharts.com/school/doku.php?id=chart_school:trading_strategies:moving_momentum](http://stockcharts.com/school/doku.php?id=chart_school:trading_strategies:moving_momentum)
 */
@Component
class MovingMomentumStrategy : AbstractStrategy() {
    /**
     * @param series a time series
     * @return a moving momentum strategy
     */
    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrice = ClosePriceIndicator(series)

        // The bias is bearish when the shorter-moving average moves below the longer moving average.
        val shortEma = EMAIndicator(closePrice, iMAShort)
        val longEma = EMAIndicator(closePrice, iMALong)
        val stochasticOscillK = StochasticOscillatorKIndicator(series, 14)
        val macd = MACDIndicator(closePrice, iMAShort, iMALong)
        val emaMacd = EMAIndicator(macd, 18)

        // Entry rule
        val entryRule: Rule = OverIndicatorRule(shortEma, longEma) // Trend
            .and(CrossedDownIndicatorRule(stochasticOscillK, BigDecimal(20))) // Signal 1
            .and(OverIndicatorRule(macd, emaMacd)) // Signal 2

        // Exit rule
        val exitRule: Rule = UnderIndicatorRule(shortEma, longEma) // Trend
            .and(CrossedUpIndicatorRule(stochasticOscillK, BigDecimal(80))) // Signal 1
            .and(UnderIndicatorRule(macd, emaMacd)) // .or(new StopLossRule(closePrice, Decimal.valueOf(1)));
            .and(StopGainRule(closePrice, BigDecimal(-1))) // works
        //.and(new IsFallingRule(closePrice, 1, 0.5d));
        return BaseStrategy(entryRule, exitRule)
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
        return "MOVING MOMENTUM STRATEGY"
    }
}