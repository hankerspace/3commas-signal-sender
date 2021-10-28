package com.isaratech.signalsender.strategy.rule

import org.ta4j.core.Trade
import org.ta4j.core.TradingRecord
import org.ta4j.core.indicators.helpers.ClosePriceIndicator
import org.ta4j.core.num.Num
import org.ta4j.core.trading.rules.AbstractRule


/**
 * A trailing stop-loss rule
 *
 *
 *
 * Satisfied when the close price reaches the trailing loss threshold.
 */
class MyTrailingStopLossRule(
        /**
         * The close price indicator
         */
        var closePrice: ClosePriceIndicator?,
        /**
         * the loss-distance as percentage
         */
        private var lossPercentage: Num) : AbstractRule() {

    /**
     * the current price extremum
     */
    private var currentExtremum: Num? = null

    /**
     * the current threshold
     */
    private var threshold: Num? = null

    /**
     * the current trade
     */
    private var supervisedTrade: Trade? = null
    fun rebuildRule(closePrice: ClosePriceIndicator?, lossPercentage: Num) {
        this.closePrice = closePrice
        this.lossPercentage = lossPercentage
    }

    override fun isSatisfied(index: Int, tradingRecord: TradingRecord): Boolean {
        var satisfied = false
        // No trading history or no trade opened, no loss
        if (tradingRecord != null) {
            val currentTrade: Trade = tradingRecord.currentTrade
            if (currentTrade.isOpened()) {
                if (!currentTrade.equals(supervisedTrade)) {
                    supervisedTrade = currentTrade
                    currentExtremum = null
                    threshold = null
                }
                val currentPrice = closePrice!!.getValue(index)
                satisfied = if (currentTrade.getEntry().isBuy()) {
                    isBuySatisfied(currentPrice)
                } else {
                    isSellSatisfied(currentPrice)
                }
            }
        }
        traceIsSatisfied(index, satisfied)
        return satisfied
    }

    private fun isBuySatisfied(currentPrice: Num): Boolean {
        var satisfied = false
        if (currentExtremum == null) {
            currentExtremum = currentPrice.numOf(Float.MIN_VALUE)
        }
        if (currentPrice.isGreaterThan(currentExtremum)) {
            currentExtremum = currentPrice
            val lossRatioThreshold = currentPrice.numOf(100).minus(lossPercentage).dividedBy(currentPrice.numOf(100))
            threshold = currentExtremum!!.multipliedBy(lossRatioThreshold)
        }
        if (threshold != null) {
            satisfied = currentPrice.isLessThanOrEqual(threshold)
        }
        return satisfied
    }

    private fun isSellSatisfied(currentPrice: Num): Boolean {
        var satisfied = false
        if (currentExtremum == null) {
            currentExtremum = currentPrice.numOf(Float.MAX_VALUE)
        }
        if (currentPrice.isLessThan(currentExtremum)) {
            currentExtremum = currentPrice
            val lossRatioThreshold = currentPrice.numOf(100).plus(lossPercentage).dividedBy(currentPrice.numOf(100))
            threshold = currentExtremum!!.multipliedBy(lossRatioThreshold)
        }
        if (threshold != null) {
            satisfied = currentPrice.isGreaterThanOrEqual(threshold)
        }
        return satisfied
    }

    /**
     * Constructor.
     *
     * @param closePrice the close price indicator
     * @param lossPercentage the loss percentage
     */
    init {
        lossPercentage = lossPercentage
    }
}