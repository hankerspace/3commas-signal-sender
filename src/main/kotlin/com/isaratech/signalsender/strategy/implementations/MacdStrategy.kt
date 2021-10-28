package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.stereotype.Component
import org.ta4j.core.*

import org.ta4j.core.trading.rules.UnderIndicatorRule

import org.ta4j.core.trading.rules.OverIndicatorRule

import org.ta4j.core.indicators.EMAIndicator

import org.ta4j.core.indicators.MACDIndicator

import org.ta4j.core.indicators.helpers.ClosePriceIndicator




/**
 * https://github.com/saeedshokoohi/arbitrager/blob/883c9fc84a40047dbf876349b0c46399527a7735/src/main/java/ir/smarttrader/arbitrager/service/technical/MacdStrategy.java
 */
@Component
class MacdStrategy : AbstractStrategy() {
    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrice = ClosePriceIndicator(series)

        // The bias is bullish when the shorter-moving average moves above the longer
        // moving average.
        // The bias is bearish when the shorter-moving average moves below the longer
        // moving average.

        // The bias is bullish when the shorter-moving average moves above the longer
        // moving average.
        // The bias is bearish when the shorter-moving average moves below the longer
        // moving average.
        val shortEma = EMAIndicator(closePrice, 12)
        val longEma = EMAIndicator(closePrice, 26)


        val macd = MACDIndicator(closePrice, 12, 26)
        val emaMacd = EMAIndicator(macd, 9)

        // Entry rule
//        Rule entryRule = new OverIndicatorRule(shortEma, longEma) // Trend
//                .and(new OverIndicatorRule(macd, emaMacd)); // Signal 2

        // Entry rule
//        Rule entryRule = new OverIndicatorRule(shortEma, longEma) // Trend
//                .and(new OverIndicatorRule(macd, emaMacd)); // Signal 2
        val entryRule: Rule = OverIndicatorRule(macd, emaMacd) // Trend


        // Exit rule
//        Rule exitRule = new UnderIndicatorRule(shortEma, longEma) // Trend
//                .and(new UnderIndicatorRule(macd, emaMacd)); // Signal 2

        // Exit rule
//        Rule exitRule = new UnderIndicatorRule(shortEma, longEma) // Trend
//                .and(new UnderIndicatorRule(macd, emaMacd)); // Signal 2
        val exitRule: Rule = UnderIndicatorRule(macd, emaMacd) // Trend


        return BaseStrategy(entryRule, exitRule, 100)
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
        return "MACD STRATEGY"
    }
}