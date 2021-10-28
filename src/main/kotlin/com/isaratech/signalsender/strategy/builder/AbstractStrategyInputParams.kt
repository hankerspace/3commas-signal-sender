package com.isaratech.signalsender.strategy.builder

import com.binance.api.client.domain.market.CandlestickInterval
import lombok.Data

@Data
abstract class AbstractStrategyInputParams {
    protected open var barDuration: CandlestickInterval? = null
    var barMultiplikator = false
    var extraMultiplikator = false
    var extraMultiplikatorValue = 0f
}