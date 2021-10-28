package com.isaratech.signalsender.strategy.ta4jindicator

import org.ta4j.core.Indicator
import org.ta4j.core.indicators.CachedIndicator
import org.ta4j.core.indicators.ParabolicSarIndicator
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.num.Num


class SwapIndicator(private val closePriceIndicator: ClosePriceIndicator, private val parabolicSarIndicator: ParabolicSarIndicator) : CachedIndicator<Boolean>(closePriceIndicator) {
    private val prevSar: Indicator<Num>
    private val prevClose: Indicator<Num>
    private val prevSar2: Indicator<Num>
    private val prevClose2: Indicator<Num>
    override fun calculate(index: Int): Boolean {
        val closePriceBiggerThanSar = closePriceIndicator.getValue(index)
                .isGreaterThan(parabolicSarIndicator.getValue(index))
        val prevClosePriceBiggerThanPrevSar = prevClose.getValue(index).isGreaterThan(prevSar.getValue(index))
        val prev2ClosePriceSmallerThanPrev2Sar = prevClose2.getValue(index).isLessThan(prevSar2.getValue(index))
        return closePriceBiggerThanSar && prevClosePriceBiggerThanPrevSar && prev2ClosePriceSmallerThanPrev2Sar
    }


    init {
        prevSar = ShiftedIndicator(parabolicSarIndicator, 1)
        prevClose = ShiftedIndicator(closePriceIndicator, 1)
        prevSar2 = ShiftedIndicator(parabolicSarIndicator, 2)
        prevClose2 = ShiftedIndicator(closePriceIndicator, 2)
    }
}
