package com.isaratech.signalsender.strategy.indicator

import java.util.LinkedList


class SMA(closingPrices: List<Double?>?, private val period: Int) : Indicator {
    private var currentSum = 0.0
    private val prices: LinkedList<Double>
    override fun get(): Double {
        return currentSum / period.toDouble()
    }

    override fun getTemp(newPrice: Double): Double {
        return (currentSum - prices[0] + newPrice) / period.toDouble()
    }

    override fun init(closingPrices: List<Double?>?) {
        if (period > closingPrices!!.size) return

        //Initial sum
        for (i in closingPrices.size - period - 1 until closingPrices.size - 1) {
            prices.add(closingPrices[i]!!)
            currentSum += closingPrices[i]!!
        }
    }

    override fun update(newPrice: Double) {
        currentSum -= prices[0]
        prices.removeFirst()
        prices.add(newPrice)
        currentSum += newPrice
    }

    override fun check(newPrice: Double): Int {
        return 0
    }

    override val explanation: String?
        get() = null

    fun standardDeviation(): Double {
        val mean = currentSum / period.toDouble()
        var stdev = 0.0
        for (price in prices) {
            stdev += Math.pow(price - mean, 2.0)
        }
        return Math.sqrt(stdev / period.toDouble())
    }

    fun tempStandardDeviation(newPrice: Double): Double {
        val tempMean = (currentSum - prices[0] + newPrice) / period.toDouble()
        var tempStdev = 0.0
        for (i in 1 until prices.size) {
            tempStdev += Math.pow(prices[i] - tempMean, 2.0)
        }
        tempStdev += Math.pow(newPrice - tempMean, 2.0)
        return Math.sqrt(tempStdev / period.toDouble())
    }

    init {
        prices = LinkedList()
        init(closingPrices)
    }
}