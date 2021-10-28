package com.isaratech.signalsender.strategy.ta4jindicator

import org.ta4j.core.Indicator
import org.ta4j.core.indicators.CachedIndicator

class ShiftedIndicator<T>(private val baseIndicator: Indicator<T>, private val shift: Int) : CachedIndicator<T>(baseIndicator) {
    override fun calculate(index: Int): T {
        return baseIndicator.getValue(index - shift)
    }
}
