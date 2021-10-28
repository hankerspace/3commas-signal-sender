package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.stereotype.Component
import org.ta4j.core.*

import org.ta4j.core.trading.rules.UnderIndicatorRule

import org.ta4j.core.trading.rules.OverIndicatorRule

import org.ta4j.core.trading.rules.CrossedDownIndicatorRule

import org.ta4j.core.trading.rules.CrossedUpIndicatorRule

import org.ta4j.core.indicators.adx.MinusDIIndicator

import org.ta4j.core.indicators.adx.PlusDIIndicator

import org.ta4j.core.indicators.adx.ADXIndicator

import org.ta4j.core.indicators.SMAIndicator

import org.ta4j.core.indicators.helpers.ClosePriceIndicator


/**
 * ADX indicator based strategy
 *
 * @see <a href=
 *      "http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:average_directional_index_adx">
 *      http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:average_directional_index_adx</a>
 */
@Component
class ADXStrategy : AbstractStrategy() {
    /**
     * @param series a bar series
     * @return an adx indicator based strategy
     */
    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        requireNotNull(series) { "Series cannot be null" }
        val closePriceIndicator = ClosePriceIndicator(series)
        val smaIndicator = SMAIndicator(closePriceIndicator, 50)
        val adxBarCount = 14
        val adxIndicator = ADXIndicator(series, adxBarCount)
        val adxOver20Rule = OverIndicatorRule(adxIndicator, 20)
        val plusDIIndicator = PlusDIIndicator(series, adxBarCount)
        val minusDIIndicator = MinusDIIndicator(series, adxBarCount)
        val plusDICrossedUpMinusDI: Rule = CrossedUpIndicatorRule(plusDIIndicator, minusDIIndicator)
        val plusDICrossedDownMinusDI: Rule = CrossedDownIndicatorRule(plusDIIndicator, minusDIIndicator)
        val closePriceOverSma = OverIndicatorRule(closePriceIndicator, smaIndicator)
        val entryRule: Rule = adxOver20Rule.and(plusDICrossedUpMinusDI).and(closePriceOverSma)
        val closePriceUnderSma = UnderIndicatorRule(closePriceIndicator, smaIndicator)
        val exitRule: Rule = adxOver20Rule.and(plusDICrossedDownMinusDI).and(closePriceUnderSma)
        return BaseStrategy(entryRule, exitRule, adxBarCount)
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
        return "ADX STRATEGY"
    }
}