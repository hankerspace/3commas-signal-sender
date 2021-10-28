package com.isaratech.signalsender.strategy

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.domain.market.CandlestickInterval
import com.isaratech.signalsender.strategy.implementations.*
import com.isaratech.signalsender.utils.Utils
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.ta4j.core.TradingRecord
import org.ta4j.core.analysis.criteria.TotalProfitCriterion
import java.text.SimpleDateFormat
import java.util.*

import java.lang.StringBuilder
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import kotlin.collections.ArrayList

import kotlin.math.pow
import kotlin.math.sqrt

class StrategyTester {
    @Value("\${bot.binance.access-key}")
    private val accessKey: String = ""

    @Value("\${bot.binance.secret-key}")
    private val secretKey: String = ""

    private val strategies = listOf(
            PivotPointReversalStrategy(),
            SimpleRangeScalperLongTermStrategy(),
            SimpleRangeScalperShortTermStrategy(),
            MacdStrategy(),
            RandomStrategy(),
            ADXStrategy(),
            EMAStrategy(),
            GlobalExtremaStrategy(),
            CCICorrectionStrategy(),
            MovingMomentumStrategy(),
            RSI2Strategy(),
            AlexStrategy(),
            FinalStrategyShortV2(),
            FinalTradingStrategyV1b(),
            TotoStrategy())

    @Test
    fun main() {

        val repeat = 1
        val newRestClient = BinanceApiClientFactory.newInstance(accessKey, secretKey).newRestClient()
        val serverTime = newRestClient.serverTime
        val msDiffFor1000Minutes: Long = 1000 * 60 * 1000

        var endingTime: Long = 0

        for(strategy in strategies) {
            var sumProfit = 0.0
            var sumTrades = 0
            val tradingRecords = arrayListOf<TradingRecord>()

            repeat(repeat) { index ->
                endingTime = serverTime - (msDiffFor1000Minutes * (index + 1))
                val candlestickBars = newRestClient.getCandlestickBars("BTCUSDT", CandlestickInterval.ONE_MINUTE, 1000, serverTime - (msDiffFor1000Minutes * (index + 1)), serverTime  - (msDiffFor1000Minutes * index))
                val parsedRawTickers = Utils.parseRawTickers(candlestickBars)

                val execute = strategy.execute(parsedRawTickers, CandlestickInterval.ONE_MINUTE)
                sumProfit += TotalProfitCriterion().calculate(parsedRawTickers, execute).doubleValue()
                sumTrades += execute.tradeCount

                tradingRecords += execute
            }
            println("----- ${strategy.getName()} -----")
            println("$sumTrades trades done from ${convertLongToTime(endingTime)} to ${convertLongToTime(serverTime)} with an average of ${(sumProfit / repeat.toDouble() * 100.0) - 100.0}% profit")

            val candlestickBars = newRestClient.getCandlestickBars("BTCUSDT", CandlestickInterval.ONE_MINUTE, 1000, serverTime - (msDiffFor1000Minutes * repeat), serverTime)
            var beginBTCUSDT = 0.0
            var endBTCUSD = 0.0
            println("Sharpe ratio: ${sharpeRatio(sumProfit / repeat.toDouble(), candlestickBars.first().high.toDouble(), candlestickBars.last().high.toDouble(), tradingRecords)}")

            //println(createLogOutput(tradingRecords))
        }

    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)
    }

    private fun sharpeRatio(
        r: Double,
        beginBTCUSDT: Double,
        endBTCUSDT: Double,
        trades: List<TradingRecord>): Double {
        val flatTrades = trades.flatMap { it.trades }
        val sigma = 1.0 / flatTrades.size * sqrt(flatTrades.sumByDouble { (flatTrades[0].exit.netPrice.dividedBy(flatTrades[0].entry.netPrice).doubleValue() - r).pow(2) })
        return (r - (endBTCUSDT / beginBTCUSDT)) / sigma
    }

    private val LB = "\n"
    private val TAB = "\t"

    fun createLogOutput(tradingRecord: ArrayList<TradingRecord>): String? {
        val sb = StringBuilder()
        // Analysis
        sb.append("" + LB)
        sb.append("Trades:$LB")
        val trades = tradingRecord.flatMap { it.trades }
        var sum = BigDecimal(0.0)
        var averagePercent = BigDecimal(0.0)
        for (trade in trades) {
            sb.append(
                "Entry " + trade.entry.netPrice.toString() + TAB + " : Exit " + trade.exit.netPrice
                    .toString() + TAB
            )
            val diff = BigDecimal(trade.exit.netPrice.minus(trade.entry.netPrice).doubleValue())
            var diffPercent = BigDecimal(trade.exit.netPrice.dividedBy(trade.entry.netPrice).doubleValue())
            diffPercent = diffPercent.minus(BigDecimal(1))
            diffPercent = diffPercent.multiply(BigDecimal(100))
            var percent: String = NumberFormat.getCurrencyInstance().format(diffPercent)
            percent = percent.replace("€", "")
            var strDiff: String = NumberFormat.getCurrencyInstance().format(diff)
            strDiff = strDiff.replace("€", "")
            sb.append("  Diff amount : $strDiff$TAB Diff Percent : $percent %  ")
            sb.append("Index-Diff " + (trade.exit.index - trade.entry.index).toString() + " ")
            sb.append(LB)
            sum = sum.plus(diff)
            averagePercent = averagePercent.plus(diffPercent)
        }
        sb.append("" + LB)
        var strSum: String = NumberFormat.getCurrencyInstance().format(sum)
        strSum = strSum.replace("€", "")
        sb.append("Total $strSum$LB")
        //String percent = NumberFormat.getCurrencyInstance().format(averagePercent.doubleValue());
        var percent: String = NumberFormat.getNumberInstance().format(averagePercent)
        //percent = percent.replace("€", "");
        //sb.append("Total Percent $percent % $LB")
        averagePercent = averagePercent.divide(BigDecimal(trades.size), 6, RoundingMode.DOWN)
        percent = NumberFormat.getNumberInstance().format(averagePercent)
        sb.append("Average Percent $percent % $LB")
        return sb.toString()
    }
}