package com.isaratech.signalsender.strategy.indicator

class DBB(closingPrices: List<Double?>?, private val period: Int) : Indicator {
    private var closingPrice = 0.0
    private var standardDeviation = 0.0
    private var upperBand = 0.0
    private var upperMidBand = 0.0
    private var middleBand = 0.0
    private var lowerMidBand = 0.0
    private var lowerBand = 0.0
    override var explanation: String? = null
        private set
    private val sma: SMA
    override fun get(): Double {
        if ((upperBand - lowerBand) / middleBand < 0.05) //Low volatility case
            return 0.0
        if (upperMidBand < closingPrice && closingPrice <= upperBand) return 1.0
        return if (lowerBand < closingPrice && closingPrice <= lowerMidBand) (-1).toDouble() else 0.0
    }

    override fun getTemp(newPrice: Double): Double {
        val tempMidBand: Double = sma.getTemp(newPrice)
        val tempStdev: Double = sma.tempStandardDeviation(newPrice)
        val tempUpperBand = tempMidBand + tempStdev * 2
        val tempUpperMidBand = tempMidBand + tempStdev
        val tempLowerMidBand = tempMidBand - tempStdev
        val tempLowerBand = tempMidBand - tempStdev * 2

        if ((tempUpperBand - tempLowerBand) / tempMidBand < 0.05) //Low volatility case
            return 0.0
        if (tempUpperMidBand < newPrice && newPrice <= tempUpperBand)
            return 1.0
        if (tempLowerBand < newPrice && newPrice <= tempLowerMidBand)
            return -1.0
        else
            return 0.0
//        if ((tempUpperBand - tempLowerBand) / tempMidBand < 0.05) //Low volatility case
//            return 0.0
//        if (tempLowerMidBand >= newPrice)
//            return 1.0
//        if (tempUpperMidBand <= newPrice)
//            return -1.0
//        else
//            return 0.0
    }

    override fun init(closingPrices: List<Double?>?) {
        if (period > closingPrices!!.size) return
        closingPrice = (closingPrices.size - 2).toDouble()
        standardDeviation = sma.standardDeviation()
        middleBand = sma.get()
        upperBand = middleBand + standardDeviation * 2
        upperMidBand = middleBand + standardDeviation
        lowerMidBand = middleBand - standardDeviation
        lowerBand = middleBand - standardDeviation * 2
    }

    override fun update(newPrice: Double) {
        closingPrice = newPrice
        sma.update(newPrice)
        standardDeviation = sma.standardDeviation()
        middleBand = sma.get()
        upperBand = middleBand + standardDeviation * 2
        upperMidBand = middleBand + standardDeviation
        lowerMidBand = middleBand - standardDeviation
        lowerBand = middleBand - standardDeviation * 2
    }

    override fun check(newPrice: Double): Int {
        if (getTemp(newPrice) == 1.0) {
            explanation = "Price in DBB buy zone"
            return 1
        }
        if (getTemp(newPrice) == -1.0) {
            explanation = "Price in DBB sell zone"
            return -1
        }
        explanation = ""
        return 0
    }

    init {
        sma = SMA(closingPrices, period)
        init(closingPrices)
    }
}