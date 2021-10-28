package com.isaratech.signalsender.strategy.ta4jindicator

import org.ta4j.core.BarSeries
import org.ta4j.core.indicators.CachedIndicator
import org.ta4j.core.indicators.EMAIndicator
import org.ta4j.core.indicators.ParabolicSarIndicator
import org.ta4j.core.indicators.adx.ADXIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.num.PrecisionNum

class BuySignalIndicator(series: BarSeries?, timeframe: Int, private val momentum: PrecisionNum) : CachedIndicator<Boolean>(series) {
    private val upSwingIndicator: UpSwingIndicator
    private val swapIndicator: SwapIndicator
    private val adxIndicator: ADXIndicator

    override fun calculate(index: Int): Boolean {
        val upSwing: Boolean = upSwingIndicator.getValue(index)
        val swap: Boolean = swapIndicator.getValue(index)
        val adxValue = adxIndicator.getValue(index)
        val adx = adxValue.isGreaterThan(momentum)
        //log.debug("@index=$index upSwing=$upSwing swap=$swap adx=$adx (value=$adxValue, momentum=$momentum)")
        return upSwing && swap && adx
    }

    init {
        val closePriceIndicator = ClosePriceIndicator(series)
        val emaIndicator = EMAIndicator(closePriceIndicator, timeframe)
        val sarIndicator = ParabolicSarIndicator(series)
        adxIndicator = ADXIndicator(series, timeframe)

        // wait for stable turn from bearish to bullish market
        swapIndicator = SwapIndicator(closePriceIndicator, sarIndicator)

        // consider prices above ema to be in upswing
        upSwingIndicator = UpSwingIndicator(closePriceIndicator, emaIndicator)
    }
}
