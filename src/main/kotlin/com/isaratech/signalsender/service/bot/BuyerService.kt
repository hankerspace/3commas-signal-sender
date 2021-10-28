package com.isaratech.signalsender.service.bot

import com.binance.api.client.domain.OrderStatus
import com.binance.api.client.domain.account.Order
import com.binance.api.client.domain.market.TickerPrice
import com.binance.api.client.exception.BinanceApiException
import com.isaratech.signalsender.service.PortfolioService
import com.isaratech.signalsender.service.PriceService
import com.isaratech.signalsender.service.analysis.StrategyAnalysisFactory
import com.isaratech.signalsender.service.bot.SelectedStrategyType.strategyType
import com.isaratech.signalsender.service.exchange.ExchangeService
import com.isaratech.signalsender.utils.Utils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime

@Service
@EnableScheduling
class BuyerService(@Autowired private val priceService: PriceService,
                   @Autowired private val exchangeService: ExchangeService
) {
    @Value("\${bot.3commas.bot-id}")
    private val botId: String = ""

    @Value("\${bot.3commas.email-token}")
    private val emailToken: String = ""

    @Value("\${bot.3commas.base-pair}")
    private val basePair: String = ""

    @Value("\${bot.test-mode}")
    private val testMode: Int = 0

    @Value("\${bot.allow-lever-positions}")
    private val allowLeverPositions: Boolean = false

    private val log = LoggerFactory.getLogger(this.javaClass)

    private var loopCount = 0

    @Scheduled(fixedDelay = 1000) // Every 2 minutes
    fun lookForBuySignals() {
        if(loopCount % 60 == 0 || testMode == 2) { // 60 seconds if not test mode. 1 second if test mode

            log.debug("-- Looking for buy signal :")
            priceService.getAllLatestsPrices()?.forEach {
                try {
                    // Check it only if we have available space & if this pair is not in open positions & market open
                    if (exchangeService.isMarketOpen(it.symbol) && it.symbol.contains(basePair)
                            && (!allowLeverPositions && !(it.symbol.contains("UP") || it.symbol.contains("DOWN"))) // Allow or not lever positions
                    ) {
                        val analysisService = StrategyAnalysisFactory.getStrategy(it.symbol, strategyType)
                        //analysisService.purgeTimeSeries()
                        val buySignal = analysisService.getBuySignal(priceService.getLatestTimeSeries(it.symbol), true)
                        if (buySignal) {
                            log.debug("\tBuy signal for : ${it.symbol} at ${it.price}")

                            sendSignal(it) // Send signal to 3commas
                        }
                    }
                } catch (ex: Exception) {
                    log.error("Error in lookForBuySignals : " + ex.message)
                }
            }
            log.debug("--")
        }
        if(testMode == 2)
            exchangeService.nextMinute()
        loopCount++
    }


    fun sendSignal(t: TickerPrice) {
        val webhookUrl = "https://3commas.io/trade_signal/trading_view"
        val signal = "{  \"message_type\": \"bot\",  \"bot_id\": " + botId + ",  \"email_token\": \"" + emailToken + "\",  \"delay_seconds\": 0,  \"pair\": \"" + basePair + "_" + t.symbol.replace(basePair, "") + "\"}"
        val ret = post(webhookUrl, signal)
        log.info("Sending signal $signal with ret $ret")
    }

    fun post(url: String, body: String): String {
        return URL(url)
                .openConnection()
                .let {
                    it as HttpURLConnection
                }.apply {
                    setRequestProperty("Content-Type", "application/json; charset=utf-8")
                    requestMethod = "POST"

                    doOutput = true
                    val outputWriter = OutputStreamWriter(outputStream)
                    outputWriter.write(body)
                    outputWriter.flush()
                }.let {
                    if (it.responseCode == 200) it.inputStream else it.errorStream
                }.let { streamToRead ->
                    BufferedReader(InputStreamReader(streamToRead)).use {
                        val response = StringBuffer()

                        var inputLine = it.readLine()
                        while (inputLine != null) {
                            response.append(inputLine)
                            inputLine = it.readLine()
                        }
                        it.close()
                        response.toString()
                    }
                }
    }

}
