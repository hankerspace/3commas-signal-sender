package com.isaratech.signalsender.strategy.ta4jindicator

import org.ta4j.core.BarSeries
import org.ta4j.core.indicators.CachedIndicator
import java.util.stream.IntStream


/**
 * N-green bars indicator.
 *
 *
 * Indicates when a sequence of n green bars has occurred.
 */
class GreenBarsIndicator(series: BarSeries?, private val barCount: Int) : CachedIndicator<Boolean>(series) {
    override fun calculate(index: Int): Boolean {
        return if (index < barCount) {
            // Not enough historical bars to check.
            false
        } else IntStream.range(index - 2, index).allMatch { i: Int -> barSeries.getBar(i).isBullish }
    }

}