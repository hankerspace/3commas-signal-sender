package com.isaratech.signalsender.strategy.indicator

import com.isaratech.signalsender.utils.Formatter


//Default setting in crypto are period of 9, short 12 and long 26.
//MACD = 12 EMA - 26 EMA and compare to 9 period of MACD value.
class MACD(closingPrices: List<Double?>?, shortPeriod: Int, longPeriod: Int, signalPeriod: Int) : Indicator {
    var change = 0.25
    private var currentMACD = 0.0
    private var currentSignal = 0.0
    private val shortEMA //Will be the EMA object for shortEMA-
            : EMA
    private val longEMA //Will be the EMA object for longEMA.
            : EMA
    private val period //Only value that has to be calculated in setInitial.
            : Int
    private val multiplier: Double
    private val periodDifference: Int
    override lateinit var explanation: String
        private set
    private var lastTick = 0.0
    override fun get(): Double {
        return currentMACD - currentSignal //Difference between the values.
    }

    override fun getTemp(newPrice: Double): Double {
        //temporary values
        val longTemp = longEMA.getTemp(newPrice)
        val shortTemp = shortEMA.getTemp(newPrice)
        val tempMACD = shortTemp - longTemp
        val tempSignal = tempMACD * multiplier + currentSignal * (1 - multiplier)
        return tempMACD - tempSignal //Getting the difference between the two signals.
    }

    override fun init(closingPrices: List<Double?>?) {
        //Initial signal line
        //i = longEMA.getPeriod(); because the sizes of shortEMA and longEMA are different.
        for (i in longEMA.period until longEMA.period + period) {
            //i value with shortEMA gets changed to compensate the list size difference
            currentMACD = shortEMA.getEMAhistory()[i + periodDifference] - longEMA.getEMAhistory()[i]
            currentSignal += currentMACD
        }
        currentSignal = currentSignal / period.toDouble()

        //Everything after the first calculation of signal line.
        for (i in longEMA.period + period until longEMA.getEMAhistory().size) {
            currentMACD = shortEMA.getEMAhistory()[i + periodDifference] - longEMA.getEMAhistory()[i]
            currentSignal = currentMACD * multiplier + currentSignal * (1 - multiplier)
        }
        lastTick = get()
    }

    override fun update(newPrice: Double) {
        //Updating the EMA values before updating MACD and Signal line.
        lastTick = get()
        shortEMA.update(newPrice)
        longEMA.update(newPrice)
        currentMACD = shortEMA.get() - longEMA.get()
        currentSignal = currentMACD * multiplier + currentSignal * (1 - multiplier)
    }

    override fun check(newPrice: Double): Int {
        val change = (getTemp(newPrice) - lastTick) / Math.abs(lastTick)
        if (change > this.change && get() < 0) {
            explanation = "MACD histogram grew by " + Formatter.formatPercent(change)
            return 1
        }
        /*if (change > this.change * 2 && get() < 0) {
            explanation = "MACD histogram grew by " + Formatter.formatPercent(change)
            return 2
        }*/
        /*if (change < -MACD.change) {
            explanation = "MACD histogram fell by " + Formatter.formatPercent(change);
            return -1;
        }*/explanation = ""
        return 0
    }

    init {
        shortEMA = EMA(closingPrices, shortPeriod, true) //true, because history is needed in MACD calculations.
        longEMA = EMA(closingPrices, longPeriod, true) //true for the same reasons.
        period = signalPeriod
        multiplier = 2.0 / (signalPeriod + 1).toDouble()
        periodDifference = longPeriod - shortPeriod
        explanation = ""
        init(closingPrices) //initializing the calculations to get current MACD and signal line.
    }
}