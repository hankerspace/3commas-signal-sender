package com.isaratech.signalsender.strategy.ta4jindicator

import org.ta4j.core.Indicator

import org.ta4j.core.indicators.CachedIndicator
import org.ta4j.core.num.Num


/**
 * This class implenents a basic trailing stop loss indicator.
 *
 * Basic idea:
 * Your stop order limit is automatically adjusted while price is rising.
 * On falling prices the initial StopLossDistance is reduced.
 * Sell signal: When StopLossDistance becomes '0'
 *
 * Usage:
 *
 * // Buying rule
 * Rule buyingRule = new BooleanRule(true); // No real buying rule
 *
 * // Selling rule
 * Rule sellingRule = new CrossedDownIndicatorRule(ClosePrice_Indicator, TrailingStopLoss_Indicator).and(new JustOnceRule());
 *
 * // Strategy
 * Strategy strategy = new Strategy(buyingRule, sellingRule);
 *
 * Hints:
 * There are two constructors for two use cases:
 * - Constructor 1: No initialStopLimit is needed. It is taken from the first indicator value
 * - Constructor 2: You can set an initialStopLimit
 * It may influence the trade signals of the strategy depending which constructor you choose.
 *
 * @author Bastian Engelmann
 */
class TrailingStopLossIndicator(indicator: Indicator<Num>, stopLossDistance: Num?, initialStopLossLimit: Num?) : CachedIndicator<Num>(indicator) {
    private val indicator: Indicator<Num> = indicator
    private var stopLossLimit: Num?
    private val stopLossDistance: Num? = stopLossDistance

    /**
     * Constructor.
     * @param indicator an indicator
     * @param stopLossDistance the stop-loss distance (absolute)
     */
    constructor(indicator: Indicator<Num>, stopLossDistance: Num?) : this(indicator, stopLossDistance,null) {}

    /**
     * Simple implementation of the trailing stop-loss concept.
     * Logic:
     * IF CurrentPrice - StopLossDistance > StopLossLimit THEN StopLossLimit = CurrentPrice - StopLossDistance
     * @param index
     * @return Num
     */
    override fun calculate(index: Int): Num {
        if (stopLossLimit == null) {
            // Case without initial stop-loss limit value
            stopLossLimit = indicator.getValue(0).minus(stopLossDistance)
        }
        val currentValue: Num = indicator.getValue(index)
        val referenceValue: Num = stopLossLimit!!.plus(stopLossDistance)
        if (currentValue.isGreaterThan(referenceValue)) {
            stopLossLimit = currentValue.minus(stopLossDistance)
        }
        return stopLossLimit!!
    }

    /**
     * Constructor.
     * @param indicator an indicator
     * @param stopLossDistance the stop-loss distance (absolute)
     * @param initialStopLossLimit the initial stop-loss limit
     */
    init {
        stopLossLimit = initialStopLossLimit
    }
}