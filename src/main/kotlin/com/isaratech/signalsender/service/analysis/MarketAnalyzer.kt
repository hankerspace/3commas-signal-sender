package com.isaratech.signalsender.service.analysis

import org.springframework.stereotype.Component
import org.ta4j.core.BarSeries
import org.ta4j.core.num.Num

import org.ta4j.core.indicators.CCIIndicator

import org.ta4j.core.indicators.StochasticRSIIndicator

import org.ta4j.core.indicators.helpers.ClosePriceIndicator

import org.ta4j.core.indicators.RSIIndicator

import org.ta4j.core.trading.rules.IsFallingRule

import org.ta4j.core.trading.rules.IsRisingRule

import org.ta4j.core.Indicator
import org.ta4j.core.Rule

import org.ta4j.core.indicators.EMAIndicator

import org.ta4j.core.indicators.SMAIndicator

import org.ta4j.core.trading.rules.OverIndicatorRule


/**
 * helper class for raw indicator
 *
 * @see https://github.com/wolfgangunger/crtr-bot/blob/aaa031dcec83b36863e9c83dfb0451ea8c8a7968/src/main/java/com/unw/crypto/service/MarketAnalyzer.java
 * @author UNGERW
 */
@Component
class MarketAnalyzer {
    /**
     * determine if the price is rising
     *
     * @param s BarSeries
     * @param durationTimeframe
     * @param strenght rise or fall strengh(0.5 and 1.0 - no other float values
     * working yet)
     * @return
     */
    fun isClosedPriceRising(s: BarSeries, durationTimeframe: Int, strenght: Double): Boolean {
        val closePrice = ClosePriceIndicator(s)
        val rule1: Rule = IsRisingRule(closePrice, durationTimeframe, strenght)
        return rule1.isSatisfied(s.endIndex)
    }

    /**
     * determine if the price is falling with strength 1
     *
     * @param s
     * @param durationTimeframe
     * @return
     */
    fun isClosedPriceFallingStrict(s: BarSeries, durationTimeframe: Int): Boolean {
        val closePrice = ClosePriceIndicator(s)
        val rule1: Rule = IsFallingRule(closePrice, durationTimeframe)
        return rule1.isSatisfied(s.endIndex)
    }

    /**
     * check if the price is falling strict <br></br>
     * this means: falling more from the last to the pre-last bar as the given
     * percent value
     *
     * @param s BarSeries
     * @param percent
     * @return true if falling strict
     */
    fun isFallingStrict(s: BarSeries, percent: Double): Boolean {
        val lastPrice: Double = s.lastBar.closePrice.doubleValue()
        val preLastPrice: Double = s.getBar(s.endIndex - 1).closePrice.doubleValue()
        if (lastPrice > preLastPrice) {
            return false
        }
        val d = (1.0 - lastPrice / preLastPrice) * 100
        return d >= percent
    }

    /**
     * determine if the Simple MA is rising - simple method with no additional
     * logic
     *
     * @param s BarSeries
     * @param sma (sma8, sma14, sma200 , pass the value you want )
     * @param durationTimeframe
     * @param strenght rise or fall strengh(0.5 and 1.0 - no other float values
     * working yet)
     * @return
     */
    fun isSmaRising(s: BarSeries, sma: Int, durationTimeframe: Int, strenght: Double): Boolean {
        val closePrice = ClosePriceIndicator(s)
        val smaInd = SMAIndicator(closePrice, sma)
        val rule1: Rule = IsRisingRule(smaInd, durationTimeframe, strenght)
        return rule1.isSatisfied(s.endIndex)
    }

    /**
     * determine if the Expotential MA is rising - simple method with no
     * additional logic
     *
     * @param s BarSeries
     * @param ema (ema8, ema14, ema200 , pass the value you want )
     * @param durationTimeframe
     * @param strength rise or fall strengh(0.5 and 1.0 - no other float values
     * working yet)
     * @return
     */
    fun isEmaRising(s: BarSeries, ema: Int, durationTimeframe: Int, strength: Double): Boolean {
        val closePrice = ClosePriceIndicator(s)
        val emaInd = EMAIndicator(closePrice, ema)
        val rule1: Rule = IsRisingRule(emaInd, durationTimeframe, strength)
        val satisfied: Boolean = rule1.isSatisfied(s.endIndex)
        println("EMA $ema  is rising :$satisfied")
        return satisfied
    }

    /**
     * determine if the Short MA is over the long MA (bullish signal)
     *
     * @param s BarSeries
     * @param smaShort ( use 5 for example)
     * @param smaLong ( use 200 for example)
     * @param durationTimeframe
     * @return
     */
    fun isSMALongTimeBullish(s: BarSeries, smaShort: Int, smaLong: Int, durationTimeframe: Int): Boolean {
        val closePrice = ClosePriceIndicator(s)
        val shortSma = SMAIndicator(closePrice, smaShort)
        val longSma = SMAIndicator(closePrice, smaLong)
        val rule1: Rule = OverIndicatorRule(shortSma, longSma)
        return rule1.isSatisfied(s.endIndex)
    }

    /**
     * determine if the closed price is above SMA200 and SMA314
     *
     * @param series
     * @return
     */
    fun isPriceAboveSMA200and314(series: BarSeries): Boolean {
        val closePrice = ClosePriceIndicator(series)
        val sma200 = SMAIndicator(closePrice, 200)
        val sma314 = SMAIndicator(closePrice, 314)
        val rule: Rule = OverIndicatorRule(closePrice, sma200).and(OverIndicatorRule(closePrice, sma314))
        return rule.isSatisfied(series.endIndex)
    }

    /**
     * determine if the closed price is above SMA
     *
     * @param series
     * @param sma (the MA, for example 200 or 314)
     * @return
     */
    fun isPriceAboveSMA(series: BarSeries, sma: Int): Boolean {
        val closePrice = ClosePriceIndicator(series)
        val smaInd = SMAIndicator(closePrice, sma)
        val rule: Rule = OverIndicatorRule(closePrice, smaInd)
        return rule.isSatisfied(series.endIndex)
    }

    /**
     * calculate a Strenght value (-1 to 1)for the longterm CCI (50,100,200)
     *
     * @param series
     * @return
     */
    fun getMarketCCIStrenght(series: BarSeries): Double {
        val cci50 = calculateCCIStrenght(determineCCI(series, 50))
        val cci100 = calculateCCIStrenght(determineCCI(series, 100))
        val cci200 = calculateCCIStrenght(determineCCI(series, 200))
        return cci50 + cci100 + cci200
    }

    private fun calculateCCIStrenght(cci: Int): Double {
        // TODO this logic must be enhanced
        return if (cci > 0) {
            0.3
        } else {
            -0.3
        }
    }

    /**
     * determine the SMA strenght for the given MA. returns a value between -1
     * and 1 logic to calculate the MA strength is to be enhanced
     *
     * @param series
     * @param sma the lenght of the MA for example 14 or 50
     * @return double between -1 and 1
     */
    fun getMarketSMAStrength(series: BarSeries, sma: Int): Double {
        var result = 0.0
        val closePrice = ClosePriceIndicator(series)
        val smaInd = SMAIndicator(closePrice, sma)
        // check the last trend + final result must be positiv or negativ dependig on this value
//        boolean rising = isLastBarRising(smaInd);
        // only last bar is no good, check last  bars, for longer MAs max 10
        val lastBars = sma / 5
        //        if (lastBars > 10) {
//            lastBars = 10;
//        }
        val rising = isLastTrendRising(smaInd, lastBars)
        var barCount: Int = series.barCount
        // smaIndicator has always one bar less than closePirce
        barCount = barCount - 1
        // cut down to a even number of bars
        if (barCount % 2 != 0) {
            barCount = barCount - 1
        }
        val totalStrength = determineSlopeStrength(smaInd, barCount, barCount - 1)
        //        System.out.println("total strengh t" + totalStrength);
        if (rising && totalStrength < 0 || !rising && totalStrength > 0) {
            // last direction diametral to allover direction, return a small strenght
            // TODO value must be advanced - not just 0.1
            return if (rising) 0.1 else -0.1
        }
        var mediumStrength = 0.0
        var count = 0
        var i = barCount
        while (i > 1) {
            val strength = determineSlopeStrength(smaInd, i, barCount)
            mediumStrength += strength
            count++
            i = i / 2
        }
        mediumStrength = mediumStrength / count
        //        System.out.println("medium strenght " + mediumStrength);
//        double percentDiffTotal = calculateLastToFirstDiff(series, rising);
//        System.out.println("percent all " + percentDiffTotal);
// weighted method will weight second half percentage double
        val percentDiff = calculateWeightedLastToFirstDiff(series, rising)
        val percentDiff2 = calculateLastToFirstDiff(smaInd, sma)
        //        System.out.println("percent weighted " + percentDiff);
        println("percent $percentDiff2")
        val percentDiffAbs = Math.abs(percentDiff)
        val mediumStrengthAbs = Math.abs(mediumStrength)
        result = calculateMediumFromStrenghtAndPercent(mediumStrengthAbs, percentDiffAbs)
        return if (rising) result else -result
    }

    private fun printSMA(ind: Indicator<Num>) {
        // just helper method for debugging
        val s: BarSeries = ind.barSeries
        for (i in 0..s.endIndex) {
            System.out.println(s.getBar(i).closePrice.doubleValue())
            println(ind.getValue(i))
        }
    }

    private fun calculateMediumFromStrenghtAndPercent(mediumStrenght: Double, percent: Double): Double {
        // if percent is higher than 100% return 1.0 ( maximum strenght)
        var percent = percent
        var medium = 0.0
        if (percent > 100) {
            return 1.0
        }
        // convert to a value between 0 and 1
        percent = percent / 100
        // medium value from percent and strenght; percent is double weighted
        medium = (mediumStrenght + 2 * percent) / 3
        return medium
    }

    private fun calculateLastToFirstDiff(indicator: Indicator<Num>, barCount: Int): Double {
        val s: BarSeries = indicator.barSeries
        println(indicator.getValue(barCount - 1))
        println(indicator.getValue(0))
        println(s.getBar(s.endIndex).dateName.toString() + " " + s.getBar(s.endIndex).closePrice)
        println(
            s.getBar(s.endIndex - barCount).dateName.toString() + " " + s.getBar(s.endIndex - barCount)
                .closePrice
        )
        return 0.0
    }

    /**
     * calculate the percentual diff between the last and the first bar
     *
     * @param series
     * @param rising
     * @return
     */
    private fun calculateLastToFirstDiff(series: BarSeries, rising: Boolean): Double {
        val lastPrice: Double = series.lastBar.closePrice.doubleValue()
        val firstPrice: Double = series.firstBar.closePrice.doubleValue()
        //        System.out.println("first price " + firstPrice + " date " + series.getFirstBar().getDateName());
//        System.out.println("last price " + lastPrice + " date " + series.getLastBar().getDateName());
        val diff: Double
        val percent: Double
        if (rising) {
            diff = lastPrice - firstPrice
            percent = diff / firstPrice * 100
        } else {
            diff = firstPrice - lastPrice
            percent = diff / lastPrice * 100
        }
        return if (rising) percent else -percent
    }

    /**
     * see method above, but this method will weight the second half of the
     * series higher than the allover percent
     *
     * @param series
     * @param rising
     * @return
     */
    private fun calculateWeightedLastToFirstDiff(series: BarSeries, rising: Boolean): Double {
        val lastPrice: Double = series.lastBar.closePrice.doubleValue()
        val firstPrice: Double = series.firstBar.closePrice.doubleValue()
        val midIndex: Int = series.endIndex / 2
        val midPrice: Double = series.getBar(midIndex).closePrice.doubleValue()
        //        System.out.println("first price " + firstPrice + " date " + series.getFirstBar().getDateName());
//        System.out.println("last price " + lastPrice + " date " + series.getLastBar().getDateName());
//        System.out.println("mid price " + midPrice + " date " + series.getBar(midIndex).getDateName());
        val diffComplete: Double
        val diffMid: Double
        val percentComplete: Double
        val percentMid: Double
        val weightedPercentage: Double
        if (rising) {
            diffComplete = lastPrice - firstPrice
            diffMid = lastPrice - midPrice
            percentComplete = diffComplete / firstPrice * 100
            percentMid = diffMid / midPrice * 100
        } else {
            diffComplete = firstPrice - lastPrice
            diffMid = midPrice - lastPrice
            percentComplete = diffComplete / lastPrice * 100
            percentMid = diffMid / midPrice * 100
        }
        weightedPercentage = calculateMediumPercent(percentComplete, percentMid)
        return if (rising) weightedPercentage else -weightedPercentage
    }

    private fun calculateMediumPercent(percentComplete: Double, percentMid: Double): Double {
        // mid percent is half of period, therefore must be doubled 
        var percentMid = percentMid
        percentMid = percentMid * 2
        // now weight the percentage of the second half of the period with double weight
        return (percentComplete + percentMid) / 3
    }

    /**
     * determine the rise or fall strenth (- is falling, + is rising)
     *
     * @param s BarSeries
     * @param sma
     * @param durationTimeframe
     * @return double strenght ( from -1 to +1)
     */
    @Deprecated(
        """use determineSMAStrength(BarSeries s, int sma)
      """
    )
    fun determineSMAStrength(s: BarSeries, sma: Int, durationTimeframe: Int): Double {
        var result = 0.0
        val closePrice = ClosePriceIndicator(s)
        val smaInd = SMAIndicator(closePrice, sma)
        result = determineSlopeStrength(smaInd, durationTimeframe, s.endIndex)
        return result
    }

    /**
     * determine the rise or fall strenth (- is falling, + is rising)
     *
     * @param s BarSeries
     * @param ema
     * @param durationTimeframe
     * @return double strenght ( from -1 to +1)
     */
    fun determineEMAStrength(s: BarSeries, ema: Int, durationTimeframe: Int): Double {
        var result = 0.0
        val closePrice = ClosePriceIndicator(s)
        val emaInd = EMAIndicator(closePrice, ema)
        result = determineSlopeStrength(emaInd, durationTimeframe, s.endIndex)
        return result
    }

    /**
     * determine the rise or fall strenth (- is falling, + is rising)
     *
     * @param s
     * @param durationTimeframe
     * @return double strenght ( from -1 to +1)
     */
    fun determineClosedPriceStrength(s: BarSeries, durationTimeframe: Int): Double {
        var result = 0.0
        val closePrice = ClosePriceIndicator(s)
        result = determineSlopeStrength(closePrice, durationTimeframe, s.endIndex)
        return result
    }

    /**
     * return true if the last bar is rising ( short trend)
     *
     * @param ind
     * @return
     */
    private fun isLastBarRising(ind: Indicator<Num>): Boolean {
        val rule: Rule = IsRisingRule(ind, 1, 0.1)
        return rule.isSatisfied(ind.barSeries.endIndex)
    }

    /**
     * returns true if the last bars are rising - number of bars is parameter 2
     *
     * @param ind
     * @param barcount
     * @return
     */
    private fun isLastTrendRising(ind: Indicator<Num>, barcount: Int): Boolean {
        val rule: Rule = IsRisingRule(ind, barcount, 0.1)
        return rule.isSatisfied(ind.barSeries.endIndex)
    }

    fun determineSlopeStrength(ind: Indicator<Num?>?, durationTimeframe: Int, endIndex: Int): Double {
        val rule: Rule = IsRisingRule(ind, durationTimeframe, 0.1)
        val rising: Boolean = rule.isSatisfied(endIndex)
        var rule1: Rule
        var d = 0.0
        while (d <= 1) {
            rule1 = if (rising) {
                IsRisingRule(ind, durationTimeframe, d)
            } else {
                IsFallingRule(ind, durationTimeframe, d)
            }
            val satisfied: Boolean = rule1.isSatisfied(endIndex)
            if (!satisfied) {
                return if (rising) d - 0.1 else -(d - 0.1)
            }
            d += 0.1
        }
        return if (rising) 1.0 else -1.0
    }

    /**
     * determine the rise or fall strenth of RSI (- is falling, + is rising)
     *
     * @param s
     * @param timeframe
     * @return
     */
    fun determineRSIStrength(s: BarSeries, timeframe: Int): Double {
        val closePrice = ClosePriceIndicator(s)
        val rsiIndicator = RSIIndicator(closePrice, timeframe)
        return determineSlopeStrength(rsiIndicator, timeframe, s.endIndex)
    }

    fun determineRSIStrengthForTimefame1(s: BarSeries, timeframe: Int): Double {
        val closePrice = ClosePriceIndicator(s)
        val rsiIndicator = RSIIndicator(closePrice, timeframe)
        return determineSlopeStrength(rsiIndicator, 1, s.endIndex)
    }

    /**
     * determine the rise or fall strenth of STO(- is falling, + is rising)
     *
     * @param s
     * @param timeframe
     * @return
     */
    fun determineSTOStrength(s: BarSeries, timeframe: Int): Double {
        val closePrice = ClosePriceIndicator(s)
        val stochasticRSIIndicator = StochasticRSIIndicator(closePrice, timeframe)
        return determineSlopeStrength(stochasticRSIIndicator, timeframe, s.endIndex)
    }

    fun determineSTOStrengthForTimeframe1(s: BarSeries, timeframe: Int): Double {
        val closePrice = ClosePriceIndicator(s)
        val stochasticRSIIndicator = StochasticRSIIndicator(closePrice, timeframe)
        return determineSlopeStrength(stochasticRSIIndicator, 1, s.endIndex)
    }

    /**
     * calculate RSI (0-100)
     *
     * @param s BarSeries
     * @param timeFrame
     * @return
     */
    fun calculateRSI(s: BarSeries, timeFrame: Int): Int {
        val closePrice = ClosePriceIndicator(s)
        val rsiIndicator = RSIIndicator(closePrice, timeFrame)
        val result = rsiIndicator.getValue(s.endIndex)
        return result.intValue()
    }

    fun rsiRisingUp(s: BarSeries, timeFrame: Int, rsiThreshold: Int): Boolean {
        val rsi = calculateRSI(s, timeFrame)
        val rsiS1 = determineRSIStrengthForTimefame1(s, timeFrame)
        return rsi < rsiThreshold && rsiS1 > 0.0
    }

    fun rsiPointingDown(s: BarSeries, timeFrame: Int, rsiThreshold: Int): Boolean {
        val rsi = calculateRSI(s, timeFrame)
        val rsiS1 = determineRSIStrengthForTimefame1(s, timeFrame)
        return rsi > rsiThreshold && rsiS1 < 0.0
    }

    fun stoRisingUp(s: BarSeries, timeFrame: Int, stoThreshold: Double): Boolean {
        val sto = calculateSTO(s, timeFrame)
        val stoS1 = determineSTOStrengthForTimeframe1(s, timeFrame)
        return sto < stoThreshold && stoS1 > 0.0
    }

    fun stoPointingDown(s: BarSeries, timeFrame: Int, stoThreshold: Double): Boolean {
        val sto = calculateSTO(s, timeFrame)
        val stoS1 = determineSTOStrengthForTimeframe1(s, timeFrame)
        return sto > stoThreshold && stoS1 < 0.0
    }

    /**
     * calculate STO (0.0 -1-0)
     *
     * @param s
     * @param timeFrame
     * @return
     */
    fun calculateSTO(s: BarSeries, timeFrame: Int): Double {
        val closePrice = ClosePriceIndicator(s)
        val stochasticRSIIndicator = StochasticRSIIndicator(closePrice, timeFrame)
        val result = stochasticRSIIndicator.getValue(s.endIndex)
        return result.doubleValue()
    }

    /**
     * analyzes if the market is long term bullish
     *
     * @param s
     * @return
     */
    fun isBullish(s: BarSeries): Boolean {
        return isBullishOrBearish(s, true)
    }

    /**
     * analyzes if the market is long term bearish
     *
     * @param s
     * @return
     */
    fun isBearish(s: BarSeries): Boolean {
        return isBullishOrBearish(s, false)
    }

    /**
     * analyzes if the market is long term bullish or bearish
     *
     * @param s
     * @param bullish
     * @return
     */
    fun isBullishOrBearish(s: BarSeries, bullish: Boolean): Boolean {
        var result = true
        // default values 
        val ma = true
        val cci = true
        val cciTimeframe = 100
        val cciThreshold = 0
        val maTimeframe = 40
        val maStrenght = 0.5
        if (ma) {
            result = result and isMABullish(s, maTimeframe, maStrenght, bullish)
        }
        if (cci) {
            result = result and isCCIBullish(s, cciTimeframe, cciThreshold, bullish)
        }
        if (!ma && !cci) {
            result = false
        }
        return result
    }

    fun getMarketStrenght(s: BarSeries): Double {
        var result = 0.0
        val ma = true
        val cci = true
        val maTimeframe = 40
        val strMA = getMarketSMAStrength(s, maTimeframe)
        val strCCI = getMarketCCIStrenght(s)
        result = if (ma && !cci) {
            strMA
        } else if (cci && !ma) {
            strCCI
        } else if (ma && cci) {
            // medium of ma & cci - to be enhanced
            (strMA + strCCI) / 2
        } else {
            return 0.0
        }
        return result
    }

    /**
     * analyzes if the market is long term bullish or bearish based on the MA
     * data
     *
     * @param s
     * @param maTimeframe
     * @param strenght rising or falling strength of MA
     * @param bullish
     * @return
     */
    fun isMABullish(s: BarSeries, maTimeframe: Int, strenght: Double, bullish: Boolean): Boolean {
        val strMA = getMarketSMAStrength(s, maTimeframe)
        return if (bullish) {
            strMA >= strenght
        } else {
            strMA <= -strenght
        }
    }

    /**
     * analyzes if the market is long term bullish or bearish based on the CCI
     * data
     *
     * @param s
     * @param timeframe the CCI timeframe
     * @param threshold the CCI threshold
     * @param bullish
     * @return
     */
    fun isCCIBullish(s: BarSeries, timeframe: Int, threshold: Int, bullish: Boolean): Boolean {
        val cci = determineCCI(s, timeframe)
        return if (bullish) cci > threshold else cci < threshold
    }

    /**
     * determine the value of the CCI
     *
     * @param s
     * @param timeframe
     * @return
     */
    fun determineCCI(s: BarSeries, timeframe: Int): Int {
        val cci = CCIIndicator(s, timeframe)
        val result = cci.getValue(s.endIndex)
        return result.intValue()
    }

    /**
     * create TO AddOrderInfo with current buy/sell params
     *
     * @param s BarSeries
     * @param rsiTimeframe
     * @param stoTimeframe
     * @return AddOrderInfo
     */
    /*fun analyzeOrderParams(s: BarSeries, rsiTimeframe: Int, stoTimeframe: Int): AddOrderInfo {
        val timeframe = 2
        val rsi = calculateRSI(s, rsiTimeframe)
        val sto = calculateSTO(s, stoTimeframe)
        val closedPriceStrenth = determineClosedPriceStrength(s, 2)
        val rsiStrenght = determineRSIStrength(s, rsiTimeframe)
        val stoStrenght = determineSTOStrength(s, stoTimeframe)
        val rsiStrenght1 = determineRSIStrengthForTimefame1(s, rsiTimeframe)
        val stoStrenght1 = determineSTOStrengthForTimeframe1(s, stoTimeframe)
        val sma3 = determineSMAStrength(s, 3, timeframe)
        val sma8 = determineSMAStrength(s, 8, timeframe)
        val sma14 = determineSMAStrength(s, 14, timeframe)
        val sma50 = determineSMAStrength(s, 50, timeframe)
        val sma200 = determineSMAStrength(s, 200, timeframe)
        val sma314 = determineSMAStrength(s, 314, timeframe)
        val ema14 = determineEMAStrength(s, 14, timeframe)
        val ema50 = determineEMAStrength(s, 50, timeframe)
        val cci14 = determineCCI(s, 14).toDouble()
        val cci50 = determineCCI(s, 50).toDouble()
        val priceAboveSma200 = isPriceAboveSMA(s, 200)
        val priceAboveSma3141 = isPriceAboveSMA(s, 314)
        val isSMALongTimeBullish = isSMALongTimeBullish(s, 3, 80, timeframe)
        return AddOrderInfo.builder().rsi(rsi).sto(sto).closedPriceStrenth(closedPriceStrenth).sma3(sma3).sma14(sma14)
            .sma8(sma8).sma50(sma50).sma200(sma200).sma314(sma314).ema14(ema14).ema50(ema50)
            .priceAboveSma200(priceAboveSma200).priceAboveSma3141(priceAboveSma3141)
            .isSMALongTimeBullish(isSMALongTimeBullish).rsiStrength(rsiStrenght).stoStrength(stoStrenght)
            .rsiStrength1(rsiStrenght1).stoStrength1(stoStrenght1).cci14(cci14).cci50(cci50).build()
    }*/
}