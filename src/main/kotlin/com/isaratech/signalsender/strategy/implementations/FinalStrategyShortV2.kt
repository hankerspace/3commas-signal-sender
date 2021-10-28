package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import com.isaratech.signalsender.strategy.LoggedBaseStrategy
import com.isaratech.signalsender.strategy.builder.*
import org.ta4j.core.num.PrecisionNum

import org.ta4j.core.indicators.helpers.ClosePriceIndicator

import org.ta4j.core.indicators.EMAIndicator

import org.ta4j.core.indicators.MACDIndicator

import org.ta4j.core.indicators.StochasticOscillatorKIndicator

import org.ta4j.core.indicators.RSIIndicator

import org.ta4j.core.indicators.SMAIndicator

import org.ta4j.core.*

import com.isaratech.signalsender.strategy.builder.StrategyInputParams

import com.isaratech.signalsender.strategy.builder.ExitRuleChain

import com.isaratech.signalsender.strategy.builder.EntryRuleChain
import com.isaratech.signalsender.strategy.rule.MyTrailingStopLossRule
import org.ta4j.core.trading.rules.*


class FinalStrategyShortV2  : AbstractStrategy(){
    var params: AbstractStrategyInputParams? = null

    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        if(params == null) params = StrategyInputParamsCreator.createStrategyInputParams102(CandlestickInterval.ONE_MINUTE)
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
        return "FINAL STRATEGY SHORT V2"
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
        //3- Price is above SMA200&314 (v) really ?
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

        // RSI
        val rsiIndicator = RSIIndicator(closePrice, params.rsiTimeframeBuy)
        // stochastik
//        StochasticRSIIndicator stochasticRSIIndicator = new StochasticRSIIndicator(closePrice, params.getStoRsiTimeframeBuy());
        val stoK = StochasticOscillatorKIndicator(series, params.stoRsiTimeframeBuy)
        val stochasticOscillK = StochasticOscillatorKIndicator(series, params.stoOscKTimeFrame)
        //MACD
        val macd = MACDIndicator(closePrice, params.smaShort, params.smaLong)
        val emaMacd = EMAIndicator(macd, params.emaIndicatorTimeframe)

        // ----------
        // rules 
        // 1 - RSI is crossing low threshold 
        //Rule entryRule1 = new CrossedDownIndicatorRule(rsiIndicator, PrecisionNum.valueOf(params.getRsiThresholdLow()));
        val entryRule1: Rule = OverIndicatorRule(rsiIndicator, PrecisionNum.valueOf(params.rsiThresholdHigh))
        // 2  STO is crossing low threshold 
        //Rule entryRule2 = new CrossedDownIndicatorRule(stochasticRSIIndicator, PrecisionNum.valueOf(params.getStoThresholdLow()));
        val entryRule2: Rule = OverIndicatorRule(stoK, PrecisionNum.valueOf(params.stoThresholdHigh))
        // 3 - to be done - does it make sense ?
        //Rule entryRule3 = new OverIndicatorRule(closePrice, sma200);
        val entryRule3: Rule = UnderIndicatorRule(closePrice, sma200)
        // 4 8-MA is pointing up - second param to check
        //Rule entryRule4 = new IsRisingRule(sma8, params.getSmaIndicatorTimeframe(), params.getRisingStrenght());
        val entryRule4: Rule = IsFallingRule(sma8, params.smaIndicatorTimeframe, params.risingStrenght)
        //5- Price is near or below the 8-MA 
        //Rule entryRule5 = new UnderIndicatorRule(closePrice, sma8);
        val entryRule5: Rule = OverIndicatorRule(closePrice, sma8)
        // Rule 6 TODO 

        // Rule 7
//        Rule entryRule7 = new IsRisingRule(shortEma, params.getEmaIndicatorTimeframe())
//                .and(new IsRisingRule(longEma, params.getEmaIndicatorTimeframe()));
        val entryRule7: Rule = IsFallingRule(shortEma, params.emaIndicatorTimeframe, params.fallingStrenght)
                .and(IsFallingRule(longEma, params.emaIndicatorTimeframe, params.fallingStrenght))
        // .and(new OverIndicatorRule(shortEma, longEma)) // Trend

        // rule 11 rsi pointing up
        // Rule entryRule11 = new IsRisingRule(rsiIndicator, params.getRsiTimeframe(), params.getRisingStrenght());
        val entryRule11: Rule = IsFallingRule(rsiIndicator, params.rsiTimeframeBuy, params.fallingStrenght)

        //rule 12 sto pointing up
        //Rule entryRule12 = new IsRisingRule(stochasticRSIIndicator, params.getStoRsiTimeframe(), params.getRisingStrenght());
        val entryRule12: Rule = IsFallingRule(stoK, params.stoRsiTimeframeBuy, params.fallingStrenght)

        // rule 13 - moving momentung
        val entryRule13: Rule = OverIndicatorRule(shortEma, longEma) // Trend
                .and(CrossedDownIndicatorRule(stochasticOscillK, PrecisionNum.valueOf(params.stoThresholdLow))) // Signal 1
                .and(OverIndicatorRule(macd, emaMacd)) // Signal 2
        // TODO

        // build the complete final rule 
        val entryRule: Rule = buildCompleteEntryRule(closePrice, params.entryRuleChain, entryRule1, entryRule2, entryRule3, entryRule4, entryRule5,
                entryRule7, entryRule11, entryRule12, entryRule13)

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
        //Rule exitRuleb = new WaitForRule(Order.OrderType.BUY, params.getWaitBars());
        //////////// exit rules
        //Rule exitRule1 = new CrossedUpIndicatorRule(rsiIndicator, PrecisionNum.valueOf(params.getRsiThresholdHigh()));
        val exitRule1: Rule = UnderIndicatorRule(rsiIndicator, PrecisionNum.valueOf(params.rsiThresholdLow))

        //Rule exitRule11 = new IsFallingRule(rsiIndicator, params.getRsiTimeframe(), params.getFallingStrenght());
        val exitRule11: Rule = IsRisingRule(rsiIndicator, params.rsiTimeframeSell, params.risingStrenght)

        // Rule exitRule2 = new CrossedUpIndicatorRule(stochasticRSIIndicator, PrecisionNum.valueOf(params.getStoThresholdHigh()));
        val exitRule2: Rule = UnderIndicatorRule(stoK, PrecisionNum.valueOf(params.stoThresholdLow))

        //Rule exitRule12 = new IsFallingRule(stochasticRSIIndicator, params.getStoRsiTimeframe(), params.getFallingStrenght());
        val exitRule12: Rule = IsRisingRule(stoK, params.stoRsiTimeframeSell, params.fallingStrenght)
        // ma 8 is falling
        //Rule exitRule3 = new IsFallingRule(sma8, params.getSmaIndicatorTimeframe(), params.getFallingStrenght());
        val exitRule3: Rule = IsRisingRule(sma8, params.smaIndicatorTimeframe, params.risingStrenght)

        // prive is falling
        //Rule exitRule21 = new IsFallingRule(closePrice, params.getPriceTimeFrame(), params.getFallingStrenght());
        val exitRule21: Rule = IsRisingRule(closePrice, params.priceTimeFrameSell, params.risingStrenght)
        // strict falling ruing
        //Rule exitRule21b = new IsFallingRule(closePrice, 1, 1d);
        val exitRule21b: Rule = IsRisingRule(closePrice, 1, 1.0)

        val exitRule22 = MyTrailingStopLossRule(closePrice, PrecisionNum.valueOf(params.trailingStopLoss));
        //val exitRule22: Rule = StopGainRule(closePrice, params.stopGain)
        // TODO
        //.and(new StopGainRule(closePrice, Decimal.valueOf(-1))); // works
        // stop gain is stop loss in short version - in combination with rule 21
        val exitRule23: Rule = StopLossRule(closePrice, PrecisionNum.valueOf(params.stopGain))//.and(exitRule21)
        val exitRule26: Rule = WaitForRule(Order.OrderType.BUY, params.waitBars)
        val exitRule: Rule = buildCompleteExitRule(closePrice, params.exitRuleChain, exitRule1, exitRule2, exitRule3, exitRule11,
                exitRule12, exitRule21, exitRule21b, exitRule22, exitRule23, exitRule26)

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
    private fun buildCompleteEntryRule(closePrice: ClosePriceIndicator, ruleChain: EntryRuleChain?, rule1: Rule, rule2: Rule, rule3: Rule, rule4: Rule, rule5: Rule,
                                       rule7: Rule, rule11: Rule, rule12: Rule, rule13: Rule): Rule {
        // first create a rule, which will always be chained and is always true
        var result: Rule = OverIndicatorRule(closePrice, PrecisionNum.valueOf(0))
        if (ruleChain!!.rule1_rsiLow) {
            result = result.and(rule1)
        }
        if (ruleChain.rule2_stoLow) {
            result = result.and(rule2)
        }
        if (ruleChain.rule3_priceAboveSMA200) {
            result = result.and(rule3)
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