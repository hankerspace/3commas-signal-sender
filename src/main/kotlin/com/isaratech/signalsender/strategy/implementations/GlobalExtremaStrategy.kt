package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.stereotype.Component
import org.ta4j.core.*
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.indicators.helpers.HighestValueIndicator
import org.ta4j.core.indicators.helpers.LowestValueIndicator
import org.ta4j.core.indicators.helpers.MultiplierIndicator
import org.ta4j.core.trading.rules.OverIndicatorRule
import org.ta4j.core.trading.rules.UnderIndicatorRule
import org.ta4j.core.indicators.helpers.*;

/**
 * Strategy which compares current price to global extrema over a day.
 */
@Component
class GlobalExtremaStrategy : AbstractStrategy() {
    private val NB_BARS_PER_DAY = 60*24

    /**
     * @param series a time series
     * @return a global extrema strategy
     */
    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrices = ClosePriceIndicator(series)

        // Getting the max price over the past week
        val maxPrices = HighPriceIndicator(series)
        val weekMaxPrice = HighestValueIndicator(maxPrices, NB_BARS_PER_DAY)
        // Getting the min price over the past week
        val minPrices = LowPriceIndicator(series)
        val weekMinPrice = LowestValueIndicator(minPrices, NB_BARS_PER_DAY)

        // Going long if the close price goes below the min price
        val downWeek = MultiplierIndicator(weekMinPrice, java.lang.Double.valueOf("1.004"))
        val buyingRule: Rule = UnderIndicatorRule(closePrices, downWeek)

        // Going short if the close price goes above the max price
        val upWeek = MultiplierIndicator(weekMaxPrice, java.lang.Double.valueOf("0.996"))
        val sellingRule: Rule = OverIndicatorRule(closePrices, upWeek)
        return BaseStrategy(buyingRule, sellingRule)
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
        return "GLOBAL EXTREMA STRATEGY"
    }

}