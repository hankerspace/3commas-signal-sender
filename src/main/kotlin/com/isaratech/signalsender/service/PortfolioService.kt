package com.isaratech.signalsender.service

import com.binance.api.client.domain.account.AssetBalance
import com.isaratech.signalsender.service.exchange.ExchangeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PortfolioService(@Autowired private val exchangeService: ExchangeService) {

    /**
     * Get all your portfolio data
     */
    fun getAllPortfolio(): MutableList<AssetBalance>? {
        return exchangeService.getBalances()
    }

    /**
     * Get specific symbol data
     */
    fun getPortfolioElement(symbol: String): AssetBalance? {
        return exchangeService.getAssetBalance(symbol)
    }

}
