package com.isaratech.signalsender.utils

import com.binance.api.client.domain.market.Candlestick
import org.ta4j.core.BarSeries
import org.ta4j.core.BaseBar
import org.ta4j.core.BaseBarSeries
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

class Utils {

    companion object {
        const val LOW_VALUE = 0.00000001

        fun getMarketSymbolFromString(symbol: String): String {
            if (symbol.endsWith("BTC")) return "BTC"
            if (symbol.endsWith("ETH")) return "ETH"
            if (symbol.endsWith("BNB")) return "BNB"
            if (symbol.endsWith("USDT")) return "USDT"
            return ""
        }

        fun getPositionSymbolFromString(symbol: String): String {
            val market = getMarketSymbolFromString(symbol)
            if (symbol.startsWith(market)) {
                return symbol.substring(market.length)
            }
            if (symbol.endsWith(market)) {
                val index: Int = symbol.length - market.length
                return symbol.substring(0, index)
            }
            return ""
        }

        fun round(
                value: BigDecimal, increment: BigDecimal,
                roundingMode: RoundingMode?
        ): BigDecimal {
            return if (increment.signum() == 0) {
                // 0 increment does not make much sense, but prevent division by 0
                value
            } else {
                val divided = value.divide(increment, 0, roundingMode)
                divided.multiply(increment)
            }
        }

        /**
         * Parse the raw data coming from klines
         */
        fun parseRawTickers(rawData: MutableList<Candlestick>?): BarSeries {
            val bars: List<BaseBar>? = rawData?.map { convertBar(it) }?.toList()
            return BaseBarSeries(bars)
        }

        /**
         * Convert a single tick to ta4j api
         */
        fun convertBar(data: Candlestick): BaseBar {
            return BaseBar(
                Duration.between(Instant.ofEpochMilli(data.openTime), Instant.ofEpochMilli(data.closeTime)),
                ZonedDateTime.ofInstant(Instant.ofEpochMilli(data.closeTime), ZoneId.systemDefault()),
                BigDecimal(data.open),
                BigDecimal(data.high),
                BigDecimal(data.low),
                BigDecimal(data.close),
                BigDecimal(data.volume))
        }


        fun format(d: BigDecimal, decimalsCount: Int): String {
            //val nf = NumberFormat.getInstance(Locale.FRENCH)
            //nf.maximumFractionDigits = decimalsCount
            val f = DecimalFormat("##0.${"0".repeat(decimalsCount)}")
            val str = f.format(d.toDouble())
            return str.replace(",", ".")
        }
    }
}