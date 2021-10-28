package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import com.isaratech.signalsender.strategy.LoggedBaseStrategy
import com.isaratech.signalsender.strategy.builder.*
import com.isaratech.signalsender.strategy.rule.MyTrailingStopLossRule
import org.ta4j.core.*
import org.ta4j.core.indicators.*
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.num.PrecisionNum
import org.ta4j.core.trading.rules.*


class FinalTradingStrategyV1b : AbstractStrategy() {

    var params: AbstractStrategyInputParams? = null
    /**
     * @param series
     * @param barDuration
     * @return
     */
    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
//        val barMultiplikator = true
//        val extraMultiplikator = false
//        val extraMultiplikatorValue = 1f
//        val ma8 = 8
//        val ma14 = 14
//        val ma200 = 200
//        val ma314 = 314
//        val rsiTimeframeBuy = 2
//        val stoRsiTimeframeBuy = 4
//        val rsiTimeframeSell = 2
//        val stoRsiTimeframeSell = 4
//        val stoOscKTimeFrame = 14
//        val emaIndicatorTimeframe = 18
//        val smaIndicatorTimeframe = 12
//        val priceTimeframeBuy = 2
//        val priceTimeframeSell = 2
//        val rsiThresholdLow = 15
//        val rsiThresholdHigh = 80
//        val stoThresholdLow = 0.15
//        val stoThresholdHigh = 0.85
//        val stoOscKThresholdLow = 20
//        val stoOscKThresholdHigh = 80
//        val risingStrenght = 0.7
//        val fallingStrenght = 0.7
//        val stopLoss = 2.0
//        val trailingStopLoss = 5.0
//        val stopGain = -1.0
//        val waitBars = 50
//        val entryRuleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow = false, rule2_stoLow = true, rule3_priceAboveSMA200 = false, rule3b_priceAboveSMA314 = false, rule4_ma8PointingUp = true, rule5_priceBelow8MA = true, rule7_emaBandsPointingUp = true)
//        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh = true, rule2_stoHigh = true, rule3_8maDown = true, rule11_rsiPointingDown = false, rule12_StoPointingDown = false)
//        val params = createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator,
//                extraMultiplikatorValue, ma8, ma14, ma200, ma314, iMAShort, iMALong, iMAShort, iMALong, rsiTimeframeBuy, rsiTimeframeSell,
//                stoRsiTimeframeBuy, stoRsiTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeframeBuy,
//                priceTimeframeSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
//                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryRuleChain, exitRuleChain)
        if(params == null) params = StrategyInputParamsCreator.createStrategyInputParams(5, CandlestickInterval.ONE_MINUTE)
        return buildStrategyWithParams(series, params!!)
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
        return "FINAL TRADING STRATEGY V1B"
    }

    /**
     * this method can be used from outside app to test strategy , for example
     * junit or later for scheduled services
     *
     * @param series TimeSeries
     * @param params the parameter object StrategyInputParams ( contains all
     * params for indicators and strategies )
     * @return Strategy (BaseStrategy)
     */
    fun buildStrategyWithParams(series: BarSeries?, p: AbstractStrategyInputParams): Strategy {
        val params = p as StrategyInputParams

        // these rules are designed for hour candles - in case of shorter candles and bars the timeframe params (MA ...) must be adapted
//1- RSI is low and pointing up (v) only crossed down implemented, see rule 11 for pointing up
//2- Stochastic is low and pointing up (v) only crossed down implemented, see rule 12 for pointing up
//3- Price is above SMA200&314 (v)
//4- 8-MA is pointing up (v)
//5- Price is near or below the 8-MA (v) (the further away from the 8-MA price is, the higher probability price will turn back towards it)
//6- Price is _above_ a known area of resistance (use Fib levels to determine those zones) TODO
//7- Moving EMA bands are angled up (v)
//8- Price is not approaching prior resistance
//9- Price is near the bottom of an identified cycle
//10- Still room to grow in larger time frames
// 11 RSI is pointing up
// 12 STO is pointing up
// 13 Moving Momentum
//Be prepare to sell when the rsi, stoch and 8-MA turn down in agreement
        // simple base indicator for closed price
        val closePrice = ClosePriceIndicator(series)

        // moving averages
        // The bias is bullish when the shorter-moving average moves above the longer moving average.
        // The bias is bearish when the shorter-moving average moves below the longer moving average.
        val shortEma = EMAIndicator(closePrice, params.emaShort)
        val longEma = EMAIndicator(closePrice, params.emaLong)

        // simple moving average on long time frame
        val smaLong = SMAIndicator(closePrice, params.smaLong)
        // simple MA8
        val sma8 = SMAIndicator(closePrice, params.sma8)
        // simple MA200
        val sma200 = SMAIndicator(closePrice, params.sma200)
        val sma2314 = SMAIndicator(closePrice, params.sma314)
        // RSI
        val rsiIndicator = RSIIndicator(closePrice, params.rsiTimeframeBuy)
        // stochastik
        val stochasticRSIIndicator = StochasticRSIIndicator(closePrice, params.stoRsiTimeframeBuy)
        //new:
//        StochasticOscillatorKIndicator stoK = new StochasticOscillatorKIndicator(series, params.getStoRsiTimeframeBuy());
        val stochasticOscillK = StochasticOscillatorKIndicator(series, params.stoOscKTimeFrame)
        //MACD
        val macd = MACDIndicator(closePrice, params.smaShort, params.smaLong)
        val emaMacd = EMAIndicator(macd, params.emaIndicatorTimeframe)

        // ----------
        // rules
        // 1 - RSI is under  low threshold
//        Rule entryRule1 = new CrossedDownIndicatorRule(rsiIndicator, PrecisionNum.valueOf(params.getRsiThresholdLow()));
        val entryRule1: Rule = CrossedUpIndicatorRule(rsiIndicator, PrecisionNum.valueOf(params.rsiThresholdLow))
        // 2  STO is under low threshold
//        Rule entryRule2 = new CrossedDownIndicatorRule(stochasticRSIIndicator, PrecisionNum.valueOf(params.getStoThresholdLow()));
        val entryRule2: Rule = CrossedUpIndicatorRule(stochasticRSIIndicator, PrecisionNum.valueOf(params.stoThresholdLow))
        //        Rule entryRule2 = new CrossedDownIndicatorRule(stoK, PrecisionNum.valueOf(params.getStoThresholdLow()));
        // 3 - prive over SMA 200 (and 314)
        val entryRule3: Rule = OverIndicatorRule(closePrice, sma200)
        val entryRule3b: Rule = OverIndicatorRule(closePrice, sma2314)
        // 4 8-MA is pointing up - second param to check
        val entryRule4: Rule = IsRisingRule(sma8, params.smaIndicatorTimeframe, params.risingStrenght)
        //5- Price is near or below the 8-MA
        val entryRule5: Rule = UnderIndicatorRule(closePrice, sma8)
        // Rule 6 TODO

        // Rule 7
        val entryRule7 = IsRisingRule(shortEma, params.emaIndicatorTimeframe, params.risingStrenght)
                .and(IsRisingRule(longEma, params.emaIndicatorTimeframe, params.risingStrenght))
        // .and(new OverIndicatorRule(shortEma, longEma)) // Trend

        // rule 11 rsi pointing up
        //Rule entryRule11 = new IsRisingRule(rsiIndicator, params.getRsiTimeframeBuy(), params.getRisingStrenght());
        // rising - falling timeframe always 1
        val entryRule11: Rule = IsRisingRule(rsiIndicator, 1, params.risingStrenght)

        //rule 12 sto pointing up
        val entryRule12: Rule = IsRisingRule(stochasticRSIIndicator, 1, params.risingStrenght)
        //        Rule entryRule12 = new IsRisingRule(stoK, 1, params.getRisingStrenght());

        // rule 13 - moving momentung
        val entryRule13 = OverIndicatorRule(shortEma, longEma) // Trend
                .and(CrossedDownIndicatorRule(stochasticOscillK, PrecisionNum.valueOf(params.stoThresholdLow))) // Signal 1
                .and(OverIndicatorRule(macd, emaMacd)) // Signal 2

        // build the complete final rule
        val entryRule = buildCompleteEntryRule(closePrice, params.entryRuleChain!!, entryRule1, entryRule2, entryRule3, entryRule3b, entryRule4, entryRule5, entryRule7, entryRule11, entryRule12, entryRule13)

        // exit rule - todo
//        Rule exitRule = new StopLossRule(closePrice, Decimal.valueOf(0.4d))
//                .or(new StopGainRule(closePrice, Decimal.valueOf(0.4d)));
        //Rule exitRule = new UnderIndicatorRule(shortEma, longEma) // Trend
        //        .and(new CrossedUpIndicatorRule(stochasticOscillK, Decimal.valueOf(params.getStoOscKThresholdHigh()))); // Signal 1
        // .and(new UnderIndicatorRule(macd, emaMacd));
        //.and(new StopGainRule(closePrice, Decimal.valueOf(params.getStopGain()))); // works
//                .and(new StopGainRule(closePrice, Decimal.valueOf(-1))); // works
        //Rule exitRule2 = new WaitForRule(Order.OrderType.BUY, params.getWaitBars()).
        //        or(new StopLossRule(closePrice, Decimal.valueOf(params.getStopLoss())));
        val exitRuleb: Rule = WaitForRule(Order.OrderType.BUY, params.waitBars)

        //////////// exit rules
        // rsi is high and falling
        val exitRule1: Rule = CrossedUpIndicatorRule(rsiIndicator, PrecisionNum.valueOf(params.rsiThresholdHigh))
        //Rule exitRule11 = new IsFallingRule(rsiIndicator, params.getRsiTimeframeSell(), params.getFallingStrenght());
        // risng and falling timeframe always 1
        val exitRule11: Rule = IsFallingRule(rsiIndicator, 1, params.fallingStrenght)
        val exitRule2: Rule = CrossedUpIndicatorRule(stochasticRSIIndicator, PrecisionNum.valueOf(params.stoThresholdHigh))
        //        Rule exitRule2 = new CrossedUpIndicatorRule(stoK, PrecisionNum.valueOf(params.getStoThresholdHigh()));
        val exitRule12: Rule = IsFallingRule(stochasticRSIIndicator, 1, params.fallingStrenght)
        //        Rule exitRule12 = new IsFallingRule(stoK, 1, params.getFallingStrenght());
        // ma 8 is falling
        val exitRule3: Rule = IsFallingRule(sma8, params.smaIndicatorTimeframe, params.fallingStrenght)

        // price is falling
        val exitRule21: Rule = IsFallingRule(closePrice, params.priceTimeFrameSell, params.fallingStrenght)
        // strict falling ruing
        val exitRule21b: Rule = IsFallingRule(closePrice, 1, 1.0)

        //Rule exitRule22 = new StopLossRule(closePrice, PrecisionNum.valueOf(params.getStopLoss()));
        //Rule exitRule22 = new TrailingStopLossRuleUnger(closePrice, PrecisionNum.valueOf(params.getStopLoss()));
        var exitRule22: Rule = StopLossRule(closePrice, PrecisionNum.valueOf(params.stopLoss))
        val exitRule22b: Rule = MyTrailingStopLossRule(null, PrecisionNum.valueOf(params.trailingStopLoss))
        (exitRule22b as MyTrailingStopLossRule).rebuildRule(closePrice, PrecisionNum.valueOf(params.trailingStopLoss))
        //stop gain in combination with price is falling
        val exitRule23 = StopGainRule(closePrice, PrecisionNum.valueOf(params.stopGain)).and(exitRule21)
        val exitRule26: Rule = WaitForRule(Order.OrderType.BUY, params.waitBars)
        val exitRule = buildCompleteExitRule(closePrice, params.exitRuleChain!!, exitRule1, exitRule2, exitRule3, exitRule11,
                exitRule12, exitRule21, exitRule21b, exitRule22b, exitRule23, exitRule26)

//        return new BaseStrategy(entryRule, exitRule);
        return LoggedBaseStrategy(entryRule, exitRule)
    }

    /**
     * concatenate the rules depending on the boolean params
     *
     * @param closePrice
     * @param ruleChain
     * @param rule1
     * @param rule2
     * @param rule3
     * @param rule4
     * @param rule5
     * @param rule7
     * @return the complete Rule Chain
     */
    private fun buildCompleteEntryRule(closePrice: ClosePriceIndicator, ruleChain: EntryRuleChain, rule1: Rule, rule2: Rule, rule3: Rule, rule3b: Rule, rule4: Rule, rule5: Rule,
                                       rule7: Rule, rule11: Rule, rule12: Rule, rule13: Rule): Rule? {
        // first create a rule, which will always be chained and is always true
        var result: Rule = OverIndicatorRule(closePrice, PrecisionNum.valueOf(0))
        if (ruleChain.rule1_rsiLow) {
            result = result.and(rule1)
        }
        if (ruleChain.rule2_stoLow) {
            result = result.and(rule2)
        }
        if (ruleChain.rule3_priceAboveSMA200) {
            result = result.and(rule3)
        }
        if (ruleChain.rule3b_priceAboveSMA314) {
            result = result.and(rule3b)
        }
        if (ruleChain.rule4_ma8PointingUp) {
            result = result.and(rule4)
        }
        if (ruleChain.rule5_priceBelow8MA) {
            result = result.and(rule5)
        }
        if (ruleChain.rule7_emaBandsPointingUp) {
            result = result.and(rule7)
        }
        //
        if (ruleChain.rule11_isRsiPointingUp) {
            result = result.and(rule11)
        }
        if (ruleChain.rule12_isStoPointingUp) {
            result = result.and(rule12)
        }
        if (ruleChain.rule13_movingMomentum) {
            result = result.and(rule13)
        }
        return result
    }

    /**
     * concatenate the rules depending on the boolean params
     *
     * @param closePrice
     * @param ruleChain
     * @param rule1
     * @param rule2
     * @param rule3
     * @param rule11
     * @param rule12
     * @param rule21
     * @param rule21b
     * @param rule22
     * @param rule23
     * @return the complete Rule Chain
     */
    private fun buildCompleteExitRule(closePrice: ClosePriceIndicator, ruleChain: ExitRuleChain?, rule1: Rule, rule2: Rule, rule3: Rule,
                                      rule11: Rule, rule12: Rule, rule21: Rule, rule21b: Rule, rule22: Rule, rule23: Rule, rule26: Rule): Rule {
        // first create a rule, which will always be chained and is always true
        var result: Rule? = null
        if (ruleChain!!.rule1_rsiHigh) {
            result = if(result == null) rule1 else result.and(rule1)
        }
        if (ruleChain.rule2_stoHigh) {
            result = if(result == null) rule2 else result.and(rule2)
        }
        if (ruleChain.rule3_8maDown) {
            result = if(result == null) rule3 else result.and(rule3)
        }
        if (ruleChain.rule11_rsiPointingDown) {
            result = if(result == null) rule11 else result.and(rule11)
        }
        if (ruleChain.rule12_StoPointingDown) {
            result = if(result == null) rule12 else result.and(rule12)
        }
        if (ruleChain.rule21_priceFalling) {
            result = if(result == null) rule21 else result.and(rule21)
        }
        /// or rules - single rule will cause sell
        if (ruleChain.rule22_stopLoss) {
            result = if(result == null) rule22 else result.or(rule22)
        }
        if (ruleChain.rule23_stopGain) {
            result = if(result == null) rule23 else result.or(rule23)
        }
        if (ruleChain.rule26_waitbars) {
            result = if(result == null) rule26 else result.or(rule26)
        }
        // strict price falling rule
        // result = result.or(rule21b);
        return result ?: OverIndicatorRule(closePrice, PrecisionNum.valueOf(0))
    }
}