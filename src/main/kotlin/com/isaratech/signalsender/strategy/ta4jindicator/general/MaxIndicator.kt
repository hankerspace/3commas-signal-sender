package com.isaratech.signalsender.strategy.ta4jindicator.general

import org.ta4j.core.Indicator
import org.ta4j.core.indicators.CachedIndicator
import org.ta4j.core.num.Num

class MaxIndicator(private val src: Indicator<Num>, private val length: Int) : CachedIndicator<Num>(src) {

    override fun calculate(index: Int): Num {
        val number = numOf(length)
        return src.getValue(index).max(number)
    }
}