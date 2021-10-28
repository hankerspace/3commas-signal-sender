package com.isaratech.signalsender.strategy.builder

import com.binance.api.client.domain.market.CandlestickInterval
import lombok.AllArgsConstructor
import lombok.Builder

import lombok.NoArgsConstructor
import lombok.Setter


@Builder //@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
class StrategyInputParams : AbstractStrategyInputParams() {
    // to be discussed: should timeframe values be set in days ( and multiplicated inside this cointainer ) or
    // set with the timeframe value ( days * bar multiplicator) from outside
    /// todo : timeframe values must be calculated with factor of barsize
    //  smaLong = smaLon * BarDurationUtil.getMAMultiplicator
    // bar / candle size
    //    private BarDuration barDuration;
    //
    //    private boolean barMultiplikator;
    //    private boolean extraMultiplikator;
    //    private float extraMultiplikatorValue;
    //entry and exitRuleChain
    var entryRuleChain: EntryRuleChain? = null
    var exitRuleChain: ExitRuleChain? = null

    /// indicators
    // simple MA indays
    var smaShort: Int = 0
        get() = (field * mAMultiplicator).toInt()
    var smaLong: Int = 0
        get() = (field * mAMultiplicator).toInt()
    var sma8: Int = 8
        get() = (field * mAMultiplicator).toInt()
    var sma14: Int = 14
        get() = (field * mAMultiplicator).toInt()
    var sma200: Int = 200
        get() = (field * mAMultiplicator).toInt()
    var sma314 : Int= 314
        get() = (field * mAMultiplicator).toInt()

    // expotential MA in days
    var emaShort: Int = 0
        get() = (field * mAMultiplicator).toInt()
    var emaLong: Int = 0
        get() = (field * mAMultiplicator).toInt()

    // RSI
    var rsiTimeframeBuy: Int = 0
        get() = (field * mAMultiplicator).toInt()
    var rsiTimeframeSell: Int = 0
        get() = (field * mAMultiplicator).toInt()

    //StochasticRSIIndicator
    var stoRsiTimeframeBuy: Int = 0
        get() = (field * mAMultiplicator).toInt()
    var stoRsiTimeframeSell: Int = 0
        get() = (field * mAMultiplicator).toInt()

    //StochasticOscillatorKIndicator
    var stoOscKTimeFrame: Int = 0
        get() = (field * mAMultiplicator).toInt()

    //SMAIndicator
    var smaIndicatorTimeframe: Int = 0
        get() = (field * mAMultiplicator).toInt()

    //EMAIndicator
    var emaIndicatorTimeframe: Int = 0
        get() = (field * mAMultiplicator).toInt()

    // for closed price e.g. isFalling
    var priceTimeFrameBuy: Int = 0
        get() = (field * mAMultiplicator).toInt()
    var priceTimeFrameSell: Int = 0
        get() = (field * mAMultiplicator).toInt()

    /// rules
    // rsi
    var rsiThresholdLow = 0
    var rsiThresholdHigh = 0

    // sto
    var stoThresholdLow = 0.0
    var stoThresholdHigh = 0.0

    //stochasticOscillK
    var stoOscKThresholdLow = 0
    var stoOscKThresholdHigh = 0

    // the strength for the is falling and is rising rules
    var fallingStrenght = 0.0
    var risingStrenght = 0.0

    // stopp loss
    var stopLoss = 0.0
    var trailingStopLoss = 0.0

    // stop gain
    var stopGain = 0.0

    // wait rule
    var waitBars = 0
    private val mAMultiplicator: Float
        get() {
            var tmp = 1f
            if (barMultiplikator) {
                tmp = getMAMultiplicator(barDuration)
            }
            if (extraMultiplikator) {
                tmp *= extraMultiplikatorValue
            }
            return tmp
        }

    public override var barDuration: CandlestickInterval?
        get() {
            return super.barDuration
        }
        set(barDuration) {
            super.barDuration = barDuration
        }

    fun getMAMultiplicator(barDuration: CandlestickInterval?): Float {
        return when (barDuration) {
            CandlestickInterval.FIVE_MINUTES -> 12F
            CandlestickInterval.THREE_MINUTES -> 30F
            CandlestickInterval.ONE_MINUTE -> 60F
            else -> 1F
        }
    }

    override fun toString(): String {
        return "StrategyInputParams(entryRuleChain=$entryRuleChain, exitRuleChain=$exitRuleChain, smaShort=$smaShort, smaLong=$smaLong, sma8=$sma8, sma14=$sma14, sma200=$sma200, sma314=$sma314, emaShort=$emaShort, emaLong=$emaLong, rsiTimeframeBuy=$rsiTimeframeBuy, rsiTimeframeSell=$rsiTimeframeSell, stoRsiTimeframeBuy=$stoRsiTimeframeBuy, stoRsiTimeframeSell=$stoRsiTimeframeSell, stoOscKTimeFrame=$stoOscKTimeFrame, smaIndicatorTimeframe=$smaIndicatorTimeframe, emaIndicatorTimeframe=$emaIndicatorTimeframe, priceTimeFrameBuy=$priceTimeFrameBuy, priceTimeFrameSell=$priceTimeFrameSell, rsiThresholdLow=$rsiThresholdLow, rsiThresholdHigh=$rsiThresholdHigh, stoThresholdLow=$stoThresholdLow, stoThresholdHigh=$stoThresholdHigh, stoOscKThresholdLow=$stoOscKThresholdLow, stoOscKThresholdHigh=$stoOscKThresholdHigh, fallingStrenght=$fallingStrenght, risingStrenght=$risingStrenght, stopLoss=$stopLoss, trailingStopLoss=$trailingStopLoss, stopGain=$stopGain, waitBars=$waitBars, mAMultiplicator=$mAMultiplicator, barDuration=$barDuration)"
    }


}