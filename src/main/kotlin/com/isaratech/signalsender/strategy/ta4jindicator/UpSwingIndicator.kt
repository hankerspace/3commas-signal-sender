package com.isaratech.signalsender.strategy.ta4jindicator

import org.ta4j.core.indicators.CachedIndicator
import org.ta4j.core.indicators.EMAIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator

class UpSwingIndicator(private val closePriceIndicator: ClosePriceIndicator, private val emaIndicator: EMAIndicator) : CachedIndicator<Boolean>(closePriceIndicator) {
    override fun calculate(index: Int): Boolean {
        return emaIndicator.getValue(index).isLessThanOrEqual(closePriceIndicator.getValue(index))
    }

}
