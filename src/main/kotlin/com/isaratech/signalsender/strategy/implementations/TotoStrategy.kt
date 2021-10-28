package com.isaratech.signalsender.strategy.implementations

import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.AbstractStrategy
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.ta4j.core.*
import org.ta4j.core.indicators.*
import org.ta4j.core.indicators.adx.ADXIndicator
import org.ta4j.core.indicators.adx.MinusDIIndicator
import org.ta4j.core.indicators.adx.PlusDIIndicator
import org.ta4j.core.indicators.bollinger.BollingerBandsMiddleIndicator
import org.ta4j.core.indicators.bollinger.BollingerBandsUpperIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.indicators.statistics.StandardDeviationIndicator
import org.ta4j.core.num.PrecisionNum
import org.ta4j.core.trading.rules.*
import java.math.BigDecimal


@Component
class TotoStrategy : AbstractStrategy() {
    @Value("\${bot.analysis.time-frame}")
    private val timeframe: Int = 30
    @Value("\${bot.analysis.momentum}")
    private val momentum: String = "0.25"
    var ma8 = 8
    var ma14 = 14
    var ma200 = 200
    var ma314 = 314
    var rsiTimeframeBuy = 10
    var stoRsiTimeframeBuy = 20
    var rsiTimeframeSell = 10
    var stoRsiTimeframeSell = 4
    var stoOscKTimeFrame = 14
    var emaIndicatorTimeframe = 30
    var smaIndicatorTimeframe = 20
    var priceTimeframeBuy = 2
    var priceTimeframeSell = 5
    var rsiThresholdLow = 15
    var rsiThresholdHigh = 80
    var stoThresholdLow = 0.15
    var stoThresholdHigh = 0.85
    var stoOscKThresholdLow = 20
    var stoOscKThresholdHigh = 80
    var risingStrenght = 0.8
    var fallingStrenght = 0.8
    var stopLoss = 2.0
    var trailingStopLoss = 5.0
    var stopGain = -2.0
    var waitBars = 30
    var emaForBollingerBandValue = 16

    override fun buildStrategy(series: BarSeries?, barDuration: CandlestickInterval?): Strategy {
        val closePrice = ClosePriceIndicator(series)

        val ema = EMAIndicator(closePrice, emaForBollingerBandValue)
        val standardDeviation = StandardDeviationIndicator(closePrice, emaForBollingerBandValue)
        val middleBollingerBand = BollingerBandsMiddleIndicator(ema)
        val upperBollingerBand = BollingerBandsUpperIndicator(middleBollingerBand, standardDeviation)

        val shortEma = EMAIndicator(closePrice, iMAShort)
        val longEma = EMAIndicator(closePrice, iMALong)

        // simple moving average on long time frame
        val smaLong = SMAIndicator(closePrice, ma14)
        // simple MA8
        val sma8 = SMAIndicator(closePrice, ma8)
        // simple MA200
        val sma200 = SMAIndicator(closePrice, ma200)
        val sma2314 = SMAIndicator(closePrice, ma314)
        // RSI
        val rsiIndicator = RSIIndicator(closePrice, rsiTimeframeBuy)
        // stochastik
        val stochasticRSIIndicator = StochasticRSIIndicator(closePrice, stoRsiTimeframeBuy)
        val stochasticOscillK = StochasticOscillatorKIndicator(series, stoOscKTimeFrame)
        //MACD
        val macd = MACDIndicator(closePrice, iMAShort, iMALong)
        val emaMacd = EMAIndicator(macd, emaIndicatorTimeframe)

        // ----------
        // rules
        // 1 - RSI is under  low threshold
        val entryRule1: Rule = CrossedUpIndicatorRule(rsiIndicator, PrecisionNum.valueOf(rsiThresholdLow))
        // 2  STO is under low threshold
//        Rule entryRule2 = new CrossedDownIndicatorRule(stochasticRSIIndicator, DoubleNum.valueOf(params.getStoThresholdLow()));
        val entryRule2: Rule = CrossedUpIndicatorRule(stochasticRSIIndicator, PrecisionNum.valueOf(stoThresholdLow))
        // 3 - prive over SMA 200 (and 314)
        val entryRule3: Rule = OverIndicatorRule(closePrice, sma200)
        val entryRule3b: Rule = OverIndicatorRule(closePrice, sma2314)
        // 4 8-MA is pointing up - second param to check
        val entryRule4: Rule = IsRisingRule(sma8, smaIndicatorTimeframe, risingStrenght)
        //5- Price is near or below the 8-MA
        val entryRule5: Rule = UnderIndicatorRule(closePrice, sma8)
        // Rule 6 TODO
        // Rule 7
        val entryRule7 = IsRisingRule(shortEma, emaIndicatorTimeframe, risingStrenght)
                .and(IsRisingRule(longEma, emaIndicatorTimeframe, risingStrenght))

        // rule 11 rsi pointing up
        //Rule entryRule11 = new IsRisingRule(rsiIndicator, params.getRsiTimeframeBuy(), params.getRisingStrenght());
        // rising - falling timeframe always 1
        val entryRule11: Rule = IsRisingRule(rsiIndicator, 1, risingStrenght)
        //rule 12 sto pointing up
        val entryRule12: Rule = IsRisingRule(stochasticRSIIndicator, 1, risingStrenght)
        // rule 13 - moving momentung
        val entryRule13 = OverIndicatorRule(shortEma, longEma) // Trend
                .and(CrossedDownIndicatorRule(stochasticOscillK, PrecisionNum.valueOf(stoThresholdLow))) // Signal 1
                .and(OverIndicatorRule(macd, emaMacd)) // Signal 2


        // build the complete final rule

        //val entryRule: Rule = OverIndicatorRule(closePrice, PrecisionNum.valueOf(0)).and(entryRule1).and(entryRule2).and(entryRule4)//.and(entryRule5).or(entryRule7).and(entryRule11).and(entryRule12).and(entryRule13)

        val closePriceIndicator = ClosePriceIndicator(series)
        val emaIndicator = EMAIndicator(closePriceIndicator, timeframe)
        val sarIndicator = ParabolicSarIndicator(series)

        val smaIndicator = SMAIndicator(closePriceIndicator, 30)
        val adxBarCount = 14
        val adxIndicator = ADXIndicator(series, adxBarCount)
        val adxOver20Rule = OverIndicatorRule(adxIndicator, 0.25)
        val plusDIIndicator = PlusDIIndicator(series, adxBarCount)
        val minusDIIndicator = MinusDIIndicator(series, adxBarCount)
        val plusDICrossedUpMinusDI: Rule = CrossedUpIndicatorRule(plusDIIndicator, minusDIIndicator)
        val plusDICrossedDownMinusDI: Rule = CrossedDownIndicatorRule(plusDIIndicator, minusDIIndicator)
        val closePriceOverSma = OverIndicatorRule(closePriceIndicator, smaIndicator)
        val adxRule: Rule = adxOver20Rule.and(plusDICrossedUpMinusDI).and(closePriceOverSma)
        // The bias is bearish when the shorter-moving average moves below the longer moving average.
        // Entry rule
        val movingMomentum: Rule = OverIndicatorRule(shortEma, longEma) // Trend
                .and(CrossedDownIndicatorRule(stochasticOscillK, BigDecimal(20))) // Signal 1
                .and(OverIndicatorRule(macd, emaMacd)) // Signal 2

        val entryRule: Rule = adxRule.and(movingMomentum)

        // rsi is high and falling
        val exitRule1: Rule = CrossedUpIndicatorRule(rsiIndicator, PrecisionNum.valueOf(rsiThresholdHigh))

        val exitRule11: Rule = IsFallingRule(rsiIndicator, 1, fallingStrenght)

        val exitRule2: Rule = CrossedUpIndicatorRule(stochasticRSIIndicator, PrecisionNum.valueOf(stoThresholdHigh))

        val exitRule12: Rule = IsFallingRule(stochasticRSIIndicator, 1, fallingStrenght)

        // ma 8 is falling
        val exitRule3: Rule = IsFallingRule(sma8, smaIndicatorTimeframe, fallingStrenght)

        // price is falling
        val exitRule21: Rule = IsFallingRule(closePrice, priceTimeframeSell, fallingStrenght)

        // strict falling ruing
        val exitRule21b: Rule = IsFallingRule(closePrice, 1, 0.9)

        val exitRule22 = TrailingStopLossRule(closePrice, PrecisionNum.valueOf(stopLoss))

        //stop gain in combination with price is falling
        val exitRule23: Rule = StopGainRule(closePrice, PrecisionNum.valueOf(stopGain)).and(exitRule21)

        val exitRule26: Rule = WaitForRule(Order.OrderType.BUY, waitBars)

        return BaseStrategy(entryRule,
                OverIndicatorRule(closePrice, PrecisionNum.valueOf(0)).and(exitRule1).and(exitRule2).and(exitRule3).and(exitRule11).and(exitRule12).and(exitRule21).or(exitRule22).or(exitRule23).or(exitRule26))
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
        return "TOTO STRATEGY"
    }
}