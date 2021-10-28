package com.isaratech.signalsender.service.exchange

import com.binance.api.client.BinanceApiCallback
import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.BinanceApiRestClient
import com.binance.api.client.BinanceApiWebSocketClient
import com.binance.api.client.domain.account.*
import com.binance.api.client.domain.account.request.OrderRequest
import com.binance.api.client.domain.account.request.OrderStatusRequest
import com.binance.api.client.domain.event.CandlestickEvent
import com.binance.api.client.domain.general.*
import com.binance.api.client.domain.market.Candlestick
import com.binance.api.client.domain.market.CandlestickInterval
import com.binance.api.client.domain.market.TickerPrice
import com.isaratech.signalsender.utils.Utils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.ta4j.core.BarSeries
import org.ta4j.core.BaseBarSeries
import java.io.Closeable
import java.util.*
import javax.annotation.PostConstruct


/**
 * This service is used to interact with exchange API
 * Currently only Binance API supported
 * https://github.com/binance-exchange/binance-java-api
 */
@Service
class ExchangeService {
    @Value("\${bot.binance.access-key}")
    private val accessKey: String = ""

    @Value("\${bot.binance.secret-key}")
    private val secretKey: String = ""

    @Value("\${bot.test-mode}")
    private val testMode: Int = 0

    private lateinit var restClient: BinanceApiRestClient

    private lateinit var wsClient: BinanceApiWebSocketClient

    private lateinit var exchangeInfo: ExchangeInfo

    private val log = LoggerFactory.getLogger(this.javaClass)


    // Test mode variables
    private val orders: HashMap<Long, NewOrderResponse> = HashMap<Long, NewOrderResponse>()

    lateinit var testModeLatestPrices: MutableList<TickerPrice>
    var beginTimeMillis = 0L
    var serverTimeMillis = 0L
    var currentMinute = 0
    val initialOffset = 1000
    var historicalDataOffset = 0
    val multiplicatorFactor = 60.0 // 60 times faster : 1 second = 1 minute
    private val barSeries: HashMap<String, BarSeries> = HashMap<String, BarSeries>()
    private val candlestickSeries: HashMap<String, MutableList<Candlestick>> = HashMap<String, MutableList<Candlestick>>()

    @PostConstruct
    fun init() {
        wsClient = BinanceApiClientFactory.newInstance().newWebSocketClient()
        restClient = BinanceApiClientFactory.newInstance(accessKey, secretKey).newRestClient()
        restClient.ping()
        exchangeInfo = restClient.exchangeInfo
        log.info("Exchange info retrieved")
//
//        val listenKey: String = restClient.startUserDataStream()
//        restClient.keepAliveUserDataStream(listenKey)
//
//        wsClient.onUserDataUpdateEvent(listenKey) { response ->
//            if (response.eventType === UserDataUpdateEvent.UserDataUpdateEventType.ACCOUNT_UPDATE) {
//                val accountUpdateEvent: AccountUpdateEvent = response.accountUpdateEvent
//
//                // Print new balances of every available asset
//                println(accountUpdateEvent.balances)
//            } else {
//                val orderTradeUpdateEvent: OrderTradeUpdateEvent = response.orderTradeUpdateEvent
//
//                // Print details about an order/trade
//                println("Order update event : $orderTradeUpdateEvent")
//            }
//        }

        if(testMode == 2) {
            //historicalDataOffset = Random.nextInt(0, 10000)
            testModeLatestPrices = restClient.allPrices!!.filter { p -> p.symbol.endsWith("USDT") && !p.symbol.contains("UP") && !p.symbol.contains("DOWN") }.toMutableList().subList(0, 50) // TODO Changeit!
            serverTimeMillis = getServerTime()!!
            log.info("TEST MODE : building historical data...")
            buildHistoricalData()
            beginTimeMillis = System.currentTimeMillis()
        }

    }

    fun nextMinute() {
        currentMinute ++
    }

    /**
     * Gets the server time (eg: 1508380346873)
     */
    fun getServerTime(): Long? {
        return restClient.serverTime
    }

    /**
     * Gets all assets balances
     */
    fun getBalances(): MutableList<AssetBalance>? {
        val account = restClient.account
        return account.balances
    }

    /**
     * Get a specific asset balance
     */
    fun getAssetBalance(asset: String): AssetBalance? {
        if(testMode > 0) {
            val response = AssetBalance()
            response.free = "1000000"
            response.asset = asset
            return response
        }
        val account = restClient.account
        return account.getAssetBalance(asset)
    }

    /**
     * Gets a list of open orders for this symbol
     */
    fun getOpenOrdersForSymbol(symbol: String): MutableList<Order>? {
        return restClient.getOpenOrders(OrderRequest(symbol))
    }

    /**
     * Gets a list of my trades for this symbol
     * Limit is set to 1000 trades max
     */
    fun getMyTradesForSymbol(symbol: String): MutableList<Trade>? {
        return restClient.getMyTrades(symbol, 1000)
    }

    /**
     * Place a new market order on the exchange
     */
    fun placeMarketOrder(order: NewOrder): NewOrderResponse {
//      USE ONLY SIGNAL NOW  if(testMode > 0) {
//            val time = System.currentTimeMillis()
//            val response = NewOrderResponse()
//            response.status = OrderStatus.FILLED
//            response.orderId = time
//            response.executedQty = order.quantity
//            var price = BigDecimal(getLatestPrice(order.symbol)).multiply(BigDecimal(order.quantity))
//            if(order.side == OrderSide.BUY) price = price
//                    .multiply(BigDecimal(1.001))// FEES
//                    .multiply(BigDecimal(Random.nextDouble(1.00, 1.002))) // Market latency
//            else if(order.side == OrderSide.SELL) price = price
//                    .multiply(BigDecimal(0.999)) // FEES
//                    .multiply(BigDecimal(Random.nextDouble(0.998, 1.00))) // Market latency
//            response.cummulativeQuoteQty = price.toString()
//            orders[time] = response
//            return response
//        }
        return restClient.newOrder(order)
    }

    fun getOrderStatus(symbol: String, orderId: Long): Order? {
//      USE ONLY SIGNAL NOW  if(testMode > 0) {
//            val response = Order()
//            val newOrder = orders[orderId]
//            response.status = OrderStatus.FILLED
//            response.orderId = orderId
//            response.executedQty = newOrder!!.executedQty
//            response.cummulativeQuoteQty = newOrder.cummulativeQuoteQty
//            return response
//        }
        return restClient.getOrderStatus(OrderStatusRequest(symbol, orderId))
    }

    /**
     * Cancel an order on the exchange
     */
//    fun cancelOrder(request: CancelOrderRequest): CancelOrderResponse? {
//        return restClient.cancelOrder(request)
//    }

    /**
     * Returns the latest price of a given symbol
     */
    fun getLatestPrice(symbol: String): String? {
        if(testMode == 2) {
            return barSeries[symbol]!!.getBar(currentMinute + initialOffset).closePrice.toString()
        }
        return restClient.get24HrPriceStatistics(symbol).lastPrice
    }

    /**
     * Returns all the latest prices available on the exchange
     */
    final fun getAllLatestsPrices(): MutableList<TickerPrice>? {
        if(testMode == 2) return testModeLatestPrices
        return restClient.allPrices
    }

    /**
     * Returns the latest 500 minutes in candlesticks
     */
    fun getMinutelyCandlestick(symbol: String, limit: Int): MutableList<Candlestick>? {
        if(testMode == 2) {
            return candlestickSeries[symbol]!!.subList(currentMinute + initialOffset - limit, currentMinute + initialOffset)
        }
        return restClient.getCandlestickBars(symbol, CandlestickInterval.ONE_MINUTE, limit, null, null)
    }

    /**
     * Returns the candlesticks using all available API parameters
     */
    fun getCandlestick(symbol: String, interval: CandlestickInterval, limit: Int, start: Long, end: Long): MutableList<Candlestick>? {
        if(testMode == 2) {
            //return candlestickSeries[symbol]!!.subList(currentMinute - 10000 - limit, currentMinute - 10000)
            // No needs for test mode
        }
        return restClient.getCandlestickBars(symbol, interval, limit, start, end)
    }

    /**
     * Returns information about a specific symbol (!! not real time data !!)
     */
    fun getInfos(symbol: String): SymbolInfo {
        return exchangeInfo.getSymbolInfo(symbol)
    }

    /**
     * Gets price info about a specific symbol
     * https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#price_filter
     */
    fun getPriceInfos(symbol: String): SymbolFilter? {
        return exchangeInfo.getSymbolInfo(symbol).getSymbolFilter(FilterType.PRICE_FILTER)
    }

    /**
     * Gets lot info about a specific symbol
     * https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#lot_size
     */
    fun getLotInfos(symbol: String): SymbolFilter? {
        return exchangeInfo.getSymbolInfo(symbol).getSymbolFilter(FilterType.LOT_SIZE)
    }

    /**
     * Gets info about a specific symbol
     * https://github.com/binance/binance-spot-api-docs/blob/master/rest-api.md#min_notional
     */
    fun getMinNotionalInfos(symbol: String): SymbolFilter? {
        return exchangeInfo.getSymbolInfo(symbol).getSymbolFilter(FilterType.MIN_NOTIONAL)
    }

    fun isMarketOpen(symbol: String): Boolean {
        if(testMode == 2) return true
        return exchangeInfo.getSymbolInfo(symbol).status == SymbolStatus.TRADING
    }

    /**
     * Listen prices changes using websocket
     * The returned closeable can bu used to close the websocket
     * https://github.com/binance/binance-spot-api-docs/blob/master/web-socket-streams.md#individual-symbol-book-ticker-streams
     */
    fun listenPriceChange(symbol: String, listener: BinanceApiCallback<CandlestickEvent>) {
        if(testMode == 2) {
            var run = true
            val startingMinute = currentMinute
            Thread() {
                var loop = 0
                while(run) {
                    val event = CandlestickEvent()
//                    val currentBar = barSeries[symbol]!!.getBar(startingMinute + loop + initialOffset)
//                    val simulatedValue = if (currentBar.lowPrice.compareTo(currentBar.highPrice) == 0) BigDecimal(currentBar.lowPrice.doubleValue()) else BigDecimal(Random.nextDouble(currentBar.lowPrice.doubleValue(), currentBar.highPrice.doubleValue()))
                    event.close = barSeries[symbol]!!.getBar(startingMinute + loop + initialOffset).closePrice.toString()
                    listener.onResponse(event)
                    Thread.sleep(1000) // 60 times per second
                    loop++
                }
            }.start()

        }
        val lowercase = symbol.toLowerCase()
        wsClient.onCandlestickEvent(lowercase, CandlestickInterval.ONE_MINUTE, listener)
    }

    /**
     * Close a websocket
     */
    fun closeWebsocket(ws: Closeable) {
        ws.close();
    }


    /**
     * Build series
     */
    private fun buildHistoricalData() {

        log.info("\t ${testModeLatestPrices.size} prices to retrieve")
        var count = 0
        var toRemove: MutableList<TickerPrice> = ArrayList<TickerPrice>()
        testModeLatestPrices.parallelStream().forEach {
            var candlesticks: MutableList<Candlestick> = ArrayList<Candlestick>()
            for (i in 0..10000 step 1000) {
                candlesticks.addAll(getCandlestick(it.symbol, CandlestickInterval.ONE_MINUTE, 1000,
                        serverTimeMillis - ((10000 + historicalDataOffset) * 60000) + (i * 60000), // 10000 minutes earlier
                        serverTimeMillis - ((10000 + historicalDataOffset) * 60000) + (60000 * 1000) + (i * 60000))!!)// 9000 minutes earlier
            }
            val series: BarSeries = BaseBarSeries()
            if(candlesticks.size > 0) {
                for(candlestick in candlesticks) {
                    series.addBar(Utils.convertBar(candlestick))
                }
                count++
                log.info("\t ${count} / ${testModeLatestPrices.size}")
                barSeries[it.symbol] = series
                candlestickSeries[it.symbol] = candlesticks
            }
            else {
                toRemove.add(it)
            }

        }
        testModeLatestPrices.removeAll(toRemove)
    }

    private fun getMillisSinceStartup(): Long {
        return System.currentTimeMillis() - beginTimeMillis
    }


}
