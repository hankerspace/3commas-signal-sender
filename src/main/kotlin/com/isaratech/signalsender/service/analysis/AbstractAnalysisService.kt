package com.isaratech.signalsender.service.analysis

import com.isaratech.signalsender.service.PriceService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.ta4j.core.BarSeries
import org.ta4j.core.BaseBarSeries
import org.ta4j.core.BaseTradingRecord
import org.ta4j.core.TradingRecord
import org.ta4j.core.num.Num
import java.time.ZonedDateTime

@Service
abstract class AbstractAnalysisService() {
    @Autowired
    protected lateinit var priceService: PriceService

    var symbol: String = ""

    protected val log = LoggerFactory.getLogger(this.javaClass)
    var tradingRecord: TradingRecord = BaseTradingRecord()
    protected var cachedBarSeries: BarSeries = BaseBarSeries()

    fun purgeCache() {
        cachedBarSeries = BaseBarSeries()
        //cachedBarSeries.maximumBarCount = 1000
        tradingRecord = BaseTradingRecord()
    }

    fun completeTimeSeries(tickers: BarSeries) {
        // Copy if empty
        if(cachedBarSeries.isEmpty) {
            for(i in tickers.beginIndex..tickers.endIndex)
                cachedBarSeries.addBar(tickers.getBar(i))
        }

        val lastBarBeginTime = cachedBarSeries.lastBar.beginTime
        var add = false

        for(i in tickers.beginIndex..tickers.endIndex) {
            if(add) {
                // Add following bars but without replace
                cachedBarSeries.addBar(tickers.getBar(i), false)
            }
            if(lastBarBeginTime == tickers.getBar(i).beginTime) { // Find the last bar in tickers
                // i is the last bar : always replace the last bar
                cachedBarSeries.addBar(tickers.getBar(i), true)
                add = true
            }
        }

    }

    fun getIndexByDate(date: ZonedDateTime): Int {
        // Determine index based on date
        for(i in cachedBarSeries.beginIndex..cachedBarSeries.endIndex) {
            if(cachedBarSeries.getBar(i).beginTime < date && cachedBarSeries.getBar(i).endTime >= date) {
                // found!
                return i
            }
        }
        return -1
    }

    /**
     * Add a buy order to trading record
     * The date will be used to fund the right index in the barseries
     */
    fun buy(date: ZonedDateTime, price: Num, amount: Num): Boolean {
        return tradingRecord.enter(getIndexByDate(date), price, amount)
    }

    /**
     * Add a buy order to trading record
     * The CURRENT date will be used to fund the right index in the barseries
     */
    fun buy(price: Num, amount: Num): Boolean {
        return buy(cachedBarSeries.lastBar.endTime , price, amount)
    }

    /**
     * Calculates a buy signal based several technical analysis indicators
     *
     * @param symbol the symbol to analyze
     * @return true if pair is good for buying, false otherwise
     */
    fun getBuySignal(timeSeries: BarSeries, purge: Boolean = true): Boolean {
        // Purge data on buy signal : we can cleanup everything
        if(purge) purgeCache()
        completeTimeSeries(timeSeries)
        return getInternalBuySignal()
    }

    /**
     * Calculates a buy signal based several technical analysis indicators
     *
     * @param tickers the tickers to analyze
     * @return true if pair is good for buying, false otherwise
     */
    protected abstract fun getInternalBuySignal(): Boolean

    abstract fun getName(): String

}
