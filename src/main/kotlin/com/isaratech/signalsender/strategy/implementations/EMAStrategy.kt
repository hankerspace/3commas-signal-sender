package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.stereotype.Component
import org.ta4j.core.*
import org.ta4j.core.indicators.EMAIndicator
import org.ta4j.core.indicators.MACDIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.trading.rules.*


/**
 * https://vsdelke.ru/torgovye-strategii/peresechenie-skolzyashhix-srednix-strategiya-dlya-torgovli.html
 */
@Component
class EMAStrategy : AbstractStrategy() {
    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePriceIndicator = ClosePriceIndicator(series)
        val shortEMA = EMAIndicator(closePriceIndicator, 5)
        val longEMA = EMAIndicator(closePriceIndicator, 15)
        val macdIndicator = MACDIndicator(closePriceIndicator, 12, 26)

        val buyingRule: Rule = CrossedUpIndicatorRule(shortEMA, longEMA)
            .and(IsRisingRule(macdIndicator, 2))
            .and(UnderIndicatorRule(macdIndicator, 0))
        val sellingRule: Rule = StopGainRule(closePriceIndicator, 2)
            .or(StopLossRule(closePriceIndicator, 2))

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
        return "EMA STRATEGY"
    }

}