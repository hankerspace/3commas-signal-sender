package com.isaratech.signalsender.strategy.indicator

import java.util.ArrayList


/**
 * EXPONENTIAL MOVING AVERAGE
 */
class EMA(closingPrices: List<Double?>?, val period: Int, private val historyNeeded: Boolean) : Indicator {
    private var currentEMA = 0.0
    private val multiplier: Double
    private val EMAhistory: MutableList<Double>
    override fun get(): Double {
        return currentEMA
    }

    override fun getTemp(newPrice: Double): Double {
        return (newPrice - currentEMA) * multiplier + currentEMA
    }

    override fun init(closingPrices: List<Double?>?) {
        if (period > closingPrices!!.size) return

        //Initial SMA
        for (i in 0 until period) {
            currentEMA += closingPrices[i]!!
        }
        currentEMA = currentEMA / period.toDouble()
        if (historyNeeded) EMAhistory.add(currentEMA)
        //Dont use latest unclosed candle;
        for (i in period until closingPrices.size - 1) {
            update(closingPrices[i]!!)
        }
    }

    override fun update(newPrice: Double) {
        // EMA = (Close - EMA(previousBar)) * multiplier + EMA(previousBar)
        currentEMA = (newPrice - currentEMA) * multiplier + currentEMA
        if (historyNeeded) EMAhistory.add(currentEMA)
    }

    override fun check(newPrice: Double): Int {
        return 0
    }

    override val explanation: String?
        get() = null

    fun getEMAhistory(): List<Double> {
        return EMAhistory
    }

    init {
        multiplier = 2.0 / (period + 1).toDouble()
        EMAhistory = ArrayList()
        init(closingPrices)
    }
}