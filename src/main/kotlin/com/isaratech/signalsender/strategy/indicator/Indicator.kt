package com.isaratech.signalsender.strategy.indicator


interface Indicator {
    //Used to get the latest indicator value updated with closed candle
    fun get(): Double

    //Used to get value of indicator simulated with the latest non-closed price
    fun getTemp(newPrice: Double): Double

    //Used in constructor to set initial value
    fun init(closingPrices: List<Double?>?)

    //Used to update value with latest closed candle closing price
    fun update(newPrice: Double)

    //Used to check for buy signal
    fun check(newPrice: Double): Int
    val explanation: String?
}