package com.isaratech.signalsender.strategy.builder

import com.binance.api.client.domain.market.CandlestickInterval

object StrategyInputParamsBuilder {
    fun createStrategyInputParams(barDuration: CandlestickInterval?, barMultiplikator: Boolean, extraMultiplikator: Boolean, extraMultiplikatorValue: Float, ma8: Int, ma14: Int, ma200: Int, ma314: Int, smaShort: Int, smaLong: Int, emaShort: Int, emaLong: Int,
                                  rsiTimeFrameBuy: Int, rsiTimeFrameSell: Int, stoRsiTimeframeBuy: Int, stoRsiTimeframeSell: Int, stoOscKTimeFrame: Int, emaIndicatorTimeframe: Int, smaIndicatorTimeframe: Int,
                                  priceTimeFrameBuy: Int, priceTimeFrameSell: Int, rsiThresholdLow: Int, rsiThresholdHigh: Int,
                                  stoThresholdLow: Double, stoThresholdHigh: Double, stoOscKThresholdLow: Int, stoOscKThresholdHigh: Int,
                                  isRisingStrenght: Double, isFallingStrenght: Double, stopLoss: Double, trailingStopLoss: Double, stopGain: Double, waitBars: Int, entryRuleChain: EntryRuleChain?, exitRuleChain: ExitRuleChain?): StrategyInputParams {
        val result = StrategyInputParams()
        result.barMultiplikator = barMultiplikator
        result.extraMultiplikator = extraMultiplikator
        result.extraMultiplikatorValue = extraMultiplikatorValue
        result.sma8 = ma8
        result.sma14 = ma14
        result.sma200 = ma200
        result.sma314 = ma314
        result.barDuration = barDuration
        result.smaShort = smaShort
        result.smaLong = smaLong
        result.emaShort = emaShort
        result.emaLong = emaLong
        result.rsiTimeframeBuy = rsiTimeFrameBuy
        result.rsiTimeframeSell = rsiTimeFrameSell
        result.stoRsiTimeframeBuy = stoRsiTimeframeBuy
        result.stoRsiTimeframeSell = stoRsiTimeframeSell
        result.stoOscKTimeFrame = stoOscKTimeFrame
        result.emaIndicatorTimeframe = emaIndicatorTimeframe
        result.smaIndicatorTimeframe = smaIndicatorTimeframe
        result.priceTimeFrameBuy = priceTimeFrameBuy
        result.priceTimeFrameSell = priceTimeFrameSell
        result.rsiThresholdLow = rsiThresholdLow
        result.rsiThresholdHigh = rsiThresholdHigh
        result.stoThresholdLow = stoThresholdLow
        result.stoThresholdHigh = stoThresholdHigh
        result.stoOscKThresholdLow = stoOscKThresholdLow
        result.stoOscKThresholdHigh = stoOscKThresholdHigh
        result.risingStrenght = isRisingStrenght
        result.fallingStrenght = isFallingStrenght
        result.stopLoss = stopLoss
        result.trailingStopLoss = trailingStopLoss
        result.stopGain = stopGain
        result.waitBars = waitBars
        result.entryRuleChain = entryRuleChain
        result.exitRuleChain = exitRuleChain
        return result
    }
}