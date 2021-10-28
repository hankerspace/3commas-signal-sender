package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.stereotype.Component
import org.ta4j.core.*
import org.ta4j.core.indicators.RSIIndicator
import org.ta4j.core.indicators.SMAIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule
import org.ta4j.core.trading.rules.OverIndicatorRule
import org.ta4j.core.trading.rules.UnderIndicatorRule
import java.math.BigDecimal


/**
 * 2-Period RSI Strategy
 *
 *
 * @see [
 * http://stockcharts.com/school/doku.php?id=chart_school:trading_strategies:rsi2](http://stockcharts.com/school/doku.php?id=chart_school:trading_strategies:rsi2)
 */
@Component
class RSI2Strategy : AbstractStrategy() {
    /**
     * @param series a time series
     * @return a 2-period RSI strategy
     */
    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrice = ClosePriceIndicator(series)
        val shortSma = SMAIndicator(closePrice, 5)
        val longSma = SMAIndicator(closePrice, 200)

        // We use a 2-period RSI indicator to identify buying
        // or selling opportunities within the bigger trend.
        val rsi = RSIIndicator(closePrice, 2)

        // Entry rule
        // The long-term trend is up when a security is above its 200-period SMA.
        val entryRule: Rule = OverIndicatorRule(shortSma, longSma) // Trend
            .and(CrossedDownIndicatorRule(rsi, BigDecimal(5))) // Signal 1
            .and(OverIndicatorRule(shortSma, closePrice)) // Signal 2

        // Exit rule
        // The long-term trend is down when a security is below its 200-period SMA.
        val exitRule: Rule = UnderIndicatorRule(shortSma, longSma) // Trend
            .and(CrossedUpIndicatorRule(rsi, BigDecimal(95))) // Signal 1
            .and(UnderIndicatorRule(shortSma, closePrice)) // Signal 2

        // TODO: Finalize the strategy
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
        return "RSI2 STRATEGY"
    }
}