package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.stereotype.Component
import org.ta4j.core.*
import org.ta4j.core.indicators.CCIIndicator
import org.ta4j.core.trading.rules.OverIndicatorRule
import org.ta4j.core.trading.rules.UnderIndicatorRule
import java.math.BigDecimal


/**
 * CCI Correction Strategy
 *
 * @see [http://stockcharts.com/school/doku.php?id=chart_school:trading_strategies:cci_correction](http://stockcharts.com/school/doku.php?id=chart_school:trading_strategies:cci_correction)
 */
@Component
class CCICorrectionStrategy : AbstractStrategy() {

    /**
     * @param series a time series
     * @return a CCI correction strategy
     */
    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val longCci = CCIIndicator(series, 200)
        val shortCci = CCIIndicator(series, 5)

        val entryRule: Rule = OverIndicatorRule(longCci, BigDecimal(100)) // Bull trend
                .and(UnderIndicatorRule(shortCci, BigDecimal(-100))) // Signal
        val exitRule: Rule = UnderIndicatorRule(longCci, BigDecimal(-100)) // Bear trend
                .and(OverIndicatorRule(shortCci, BigDecimal(100))) // Signal
        val strategy: Strategy = BaseStrategy(entryRule, exitRule)
        strategy.unstablePeriod = 5
        return strategy
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
        return "CCI STRATEGY"
    }
}