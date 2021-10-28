package com.isaratech.signalsender.strategy.indicator

import com.isaratech.signalsender.utils.Formatter

class RSI(closingPrice: List<Double?>?, private val period: Int) : Indicator {
    private var avgUp = 0.0
    private var avgDwn = 0.0
    private var prevClose = 0.0
    var positiveMin = 25
    var positivseMax = 40
    var negativeMin = 60
    var negativeMax = 75
    override var explanation = ""
        private set

    override fun init(closingPrices: List<Double?>?) {
        prevClose = closingPrices!![0]!!
        for (i in 1 until period + 1) {
            val change = closingPrices[i]!! - prevClose
            if (change > 0) {
                avgUp += change
            } else {
                avgDwn += Math.abs(change)
            }
        }

        //Initial SMA values
        avgUp = avgUp / period.toDouble()
        avgDwn = avgDwn / period.toDouble()

        //Dont use latest unclosed value
        for (i in period + 1 until closingPrices.size - 1) {
            update(closingPrices[i]!!)
        }
    }

    override fun get(): Double {
        return 100 - 100.0 / (1 + avgUp / avgDwn)
    }

    override fun getTemp(newPrice: Double): Double {
        val change = newPrice - prevClose
        val tempUp: Double
        val tempDwn: Double
        if (change > 0) {
            tempUp = (avgUp * (period - 1) + change) / period.toDouble()
            tempDwn = avgDwn * (period - 1) / period.toDouble()
        } else {
            tempDwn = (avgDwn * (period - 1) + Math.abs(change)) / period.toDouble()
            tempUp = avgUp * (period - 1) / period.toDouble()
        }
        return 100 - 100.0 / (1 + tempUp / tempDwn)
    }

    override fun update(newPrice: Double) {
        val change = newPrice - prevClose
        if (change > 0) {
            avgUp = (avgUp * (period - 1) + change) / period.toDouble()
            avgDwn = avgDwn * (period - 1) / period.toDouble()
        } else {
            avgUp = avgUp * (period - 1) / period.toDouble()
            avgDwn = (avgDwn * (period - 1) + Math.abs(change)) / period.toDouble()
        }
        prevClose = newPrice
    }

    override fun check(newPrice: Double): Int {
        val temp = getTemp(newPrice)
        if (temp < positiveMin) {
            explanation = "RSI of " + Formatter.formatDecimal(temp)
            return 2
        }
        if (temp < positivseMax) {
            explanation = "RSI of " + Formatter.formatDecimal(temp)
            return 1
        }
        if (temp > negativeMin) {
            explanation = "RSI of " + Formatter.formatDecimal(temp)
            return -1
        }
        if (temp > negativeMax) {
            explanation = "RSI of " + Formatter.formatDecimal(temp)
            return -2
        }
        explanation = ""
        return 0
    }

    init {
        init(closingPrice)
    }
}