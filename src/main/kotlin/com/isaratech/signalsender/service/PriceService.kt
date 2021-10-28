package com.isaratech.signalsender.service

import com.binance.api.client.domain.market.CandlestickInterval
import com.binance.api.client.domain.market.TickerPrice
import com.isaratech.signalsender.service.exchange.ExchangeService
import com.isaratech.signalsender.utils.Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.ta4j.core.BarSeries
import java.math.BigDecimal
import java.math.RoundingMode


@Service
class PriceService(@Autowired private val exchangeService: ExchangeService) {

    /**
     * Convert amount of specified symbol to USDT at current rate
     */
    fun symbolToUsdt(symbol: String, amount: BigDecimal): BigDecimal {
        if(symbol == "USDT") return amount
        val pair = symbol + "USDT"
        val currentPrice = getCurrentPrice(pair)
        return amount.multiply(currentPrice)
    }

    /**
     * Convert amount of USDT to specified symbol at current rate
     */
    fun usdtToSymbol(symbol: String, amount: BigDecimal): BigDecimal {
        if(symbol == "USDT") return amount
        val pair = symbol + "USDT"
        val currentPrice = getCurrentPrice(pair)
        return amount.divide(currentPrice, 10, RoundingMode.HALF_UP)
    }

    fun getAllLatestsPrices(): MutableList<TickerPrice>? {
        return exchangeService.getAllLatestsPrices()
    }

    /**
     * Get current symbol price
     */
    fun getCurrentPrice(symbol: String): BigDecimal {
        return BigDecimal(exchangeService.getLatestPrice(symbol))
    }

    /**
     * Gets the latest time series for a given symbol in a ta4j BarSeries format
     */
    fun getLatestTimeSeries(symbol: String, limit: Int = 1000): BarSeries {
        val klines = exchangeService.getMinutelyCandlestick(symbol, limit)
        return Utils.parseRawTickers(klines)
    }

    /**
     * Gets a time series for a given symbol in a ta4j BarSeries format
     * This function can be used to retrieve a past time series
     */
    fun getTimeSeries(symbol: String, interval: CandlestickInterval, limit: Int, start: Long, end: Long): BarSeries {
        val klines = exchangeService.getCandlestick(symbol, interval, limit, start, end)
        return Utils.parseRawTickers(klines)
    }
}
