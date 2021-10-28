package com.isaratech.signalsender.strategy.rule

import org.ta4j.core.TradingRecord
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.num.Num
import org.ta4j.core.num.PrecisionNum
import org.ta4j.core.trading.rules.AbstractRule

/**
 * Time dependent stop loss rule. The effetive loss percentage is equal to lossPercentageAtStart - openedTimeMinutes * linearDecay
 * If the lossPercentageAtStart is 2 and the linearDecay is 0.1, then the effective loss percentage after 1 minutes will
 * be 1.9 and the effective loss percentage after 10 minutes will be 1.0. After, 20 minutes, 0, and after 40 minutes -2.
 *
 * This rule is a strict copy of StopLossRule except that the loss percentage effectively used vary over time.
 */
class TimeDependentStopGainRule(
        val closePrice: ClosePriceIndicator,
        val lossPercentageAtStart: Double,
        val linearDecay: Double = 0.1,
        private val HUNDRED: Num = closePrice.numOf(100)) : AbstractRule() {

    override fun isSatisfied(index: Int, tradingRecord: TradingRecord?): Boolean {
        var satisfied = false
        if(tradingRecord != null) {
            val currentTrade = tradingRecord.currentTrade

            if(currentTrade.isOpened) {
                val openedTimeMinutes = index - currentTrade.entry.index

                val entryPrice = currentTrade.entry.netPrice
                val currentPrice: Num = closePrice.getValue(index)

                val effectiveLossPercentage = PrecisionNum.valueOf(lossPercentageAtStart + openedTimeMinutes * linearDecay)

                satisfied = if (currentTrade.entry.isBuy) {
                    isBuyGainSatisfied(entryPrice, currentPrice, effectiveLossPercentage)
                } else {
                    isGainStopSatisfied(entryPrice, currentPrice, effectiveLossPercentage)
                }
            }
        }
        traceIsSatisfied(index, satisfied)
        return satisfied
    }

    private fun isGainStopSatisfied(entryPrice: Num, currentPrice: Num, effectiveLossPercentage: Num): Boolean {
        val lossRatioThreshold: Num = HUNDRED.minus(effectiveLossPercentage).dividedBy(HUNDRED)
        val threshold = entryPrice.multipliedBy(lossRatioThreshold)
        return currentPrice.isGreaterThanOrEqual(threshold)
    }

    private fun isBuyGainSatisfied(entryPrice: Num, currentPrice: Num, effectiveLossPercentage: Num): Boolean {
        val lossRatioThreshold: Num = HUNDRED.plus(effectiveLossPercentage).dividedBy(HUNDRED)
        val threshold = entryPrice.multipliedBy(lossRatioThreshold)
        return currentPrice.isLessThanOrEqual(threshold)
    }
}
