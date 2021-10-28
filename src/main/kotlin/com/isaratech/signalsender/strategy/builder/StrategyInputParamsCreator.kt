package com.isaratech.signalsender.strategy.builder

import com.binance.api.client.domain.market.CandlestickInterval


object StrategyInputParamsCreator {
    fun createStrategyInputParams(variant: Int, barDuration: CandlestickInterval): AbstractStrategyInputParams {
        return when (variant) {
           1 ->                 //return createStrategyInputParams1(barDuration);
              createStrategyInputParams53Alt1(barDuration)
           2 ->                 //return createStrategyInputParams2(barDuration);
              createStrategyInputParams53Alt2(barDuration)
           3 ->                 //return createStrategyInputParams3(barDuration);
              createStrategyInputParams53Alt3(barDuration)
           4 ->                 //return createStrategyInputParams4(barDuration);
              createStrategyInputParams53Alt4(barDuration)
           5 -> //                return createStrategyInputParams5Alt2(barDuration);
              // return createStrategyInputParams9Alt4(barDuration);
              //                return createStrategyInputParamsQuadCCI1Alt4(barDuration);
              createStrategyInputParams53Alt5(barDuration)
           6 -> //                return createStrategyInputParams5Alt3(barDuration);
              //return createStrategyInputParams9Alt5(barDuration);
              //                return createStrategyInputParamsQuadCCI1Alt5(barDuration);
              createStrategyInputParams5Alt2(barDuration)
            else -> createStrategyInputParams1Short(barDuration)
        }
    }

    /**
     * configuration 1
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams1(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.85
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 1
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams1Alt1(barDuration: CandlestickInterval): StrategyInputParams {
        // alteration 1 : rising + falling 0.5
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.85
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 1
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams1Alt2(barDuration: CandlestickInterval): StrategyInputParams {
        // alteration 2
        // rsi + sto thresholds lower
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams1Alt3(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell sto time frame 1
        // rsi -> down, sto -> down, 8ma down ---
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 1
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.85
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = false
        val rule2_stoHigh = false
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = false
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 1
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams1Alt5(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.85
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = true
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = false
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams1Alt6(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.85
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = true
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams2(barDuration: CandlestickInterval): StrategyInputParams {
        // config 2: with trailing stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down, trailing stop loss
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.85
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams3(barDuration: CandlestickInterval): StrategyInputParams {

        // config 3: no stop loss
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = false
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = false
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams4(barDuration: CandlestickInterval): StrategyInputParams {

        // config 4: with trailing loss
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams5(barDuration: CandlestickInterval): StrategyInputParams {

        // config 5: no stop loss
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up, rsi -> down, sto->down
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdHigh = 70
        val rsiThresholdLow = 30
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = false
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = false
        val rule2_stoHigh = false
        val rule3_8maDown = false
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams5Alt1(barDuration: CandlestickInterval): StrategyInputParams {

        // config 5: no stop loss
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up, rsi -> down, sto->down + price below8MA
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdHigh = 70
        val rsiThresholdLow = 30
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = true
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams5Alt2(barDuration: CandlestickInterval): StrategyInputParams {

        // config 5: no stop loss
        // time frames rsi 2 ; sto 4
        // buy thresholds 20 + 80
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up, rsi -> down, sto->down
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdHigh = 80
        val rsiThresholdLow = 20
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams5Alt3(barDuration: CandlestickInterval): StrategyInputParams {

        // config 5: with tr. stop loss
        // time frames rsi 2 ; sto 4
        // buy thresholds 20 + 80
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up, rsi -> down, sto->down
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdHigh = 80
        val rsiThresholdLow = 20
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams6(barDuration: CandlestickInterval): StrategyInputParams {

        // config 6: with trailing stop loss
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up, rsi -> down, sto->down
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 1
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams7(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 4 ; sto 8
        // buy
        // rsi low, sto low, rsi pointing up
        //sell
        // rsi high, sto high, rsi-> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 4
        val rsiTimeframeSell = 4
        val rsiStoTimeframeBuy = 8
        val rsiStoTimeframeSell = 8
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = false
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = false
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = false
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = false
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams7Alt1(barDuration: CandlestickInterval): StrategyInputParams {
        // config Alt1: w tr. stop loss 6
        // time frames rsi 4 ; sto 8
        // buy
        // rsi low, sto low, rsi pointing up
        //sell
        // rsi high, sto high, rsi-> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 4
        val rsiTimeframeSell = 4
        val rsiStoTimeframeBuy = 8
        val rsiStoTimeframeSell = 8
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = false
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = false
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = false
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams7Alt2(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: tr stop loss
        // time frames rsi 4 ; sto 8
        // buy
        // rsi low, sto low, rsi pointing up, above sma200
        //sell
        // rsi high, sto high, rsi-> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 4
        val rsiTimeframeSell = 4
        val rsiStoTimeframeBuy = 8
        val rsiStoTimeframeSell = 8
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = false
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = false
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = false
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams7Alt3(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: tr stop loss 6
        // time frames rsi 4 ; sto 8
        // buy
        // rsi low, sto low, rsi pointing up, above sma200, 8MA pointing up
        //sell
        // rsi high, sto high, rsi-> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 4
        val rsiTimeframeSell = 4
        val rsiStoTimeframeBuy = 8
        val rsiStoTimeframeSell = 8
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = false
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = false
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = false
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams7Alt4(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: tr stop loss 6
        // time frames rsi 4 ; sto 8
        // buy
        // rsi low, sto low, rsi pointing up, above sma200, 7EMA bands pointing up
        //sell
        // rsi high, sto high, rsi-> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 4
        val rsiTimeframeSell = 4
        val rsiStoTimeframeBuy = 8
        val rsiStoTimeframeSell = 8
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = false
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = false
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = false
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams7Alt5(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: tr stop loss
        // time frames rsi 4 ; sto 8
        // buy
        // rsi low, sto low, rsi pointing up, above sma200, 8MA pointing up, 7 EMA bands up
        //sell
        // rsi high, sto high, rsi-> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 4
        val rsiTimeframeSell = 4
        val rsiStoTimeframeBuy = 8
        val rsiStoTimeframeSell = 8
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = false
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 8
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams8(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, rsi+ sto pointing up
        //sell
        // rsi high, sto high, rsi + sot -> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.25
        val stoThresholdHigh = 0.75
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = false
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 8
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams8Alt1(barDuration: CandlestickInterval): StrategyInputParams {
        // config 8 :trai stop loss
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, rsi+ sto pointing up, 8ma up, 7ema up
        //sell
        // rsi high, sto high, rsi + sot -> down, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.25
        val stoThresholdHigh = 0.75
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 8
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams8Alt2(barDuration: CandlestickInterval): StrategyInputParams {
        // config 8 :trai stop loss
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, rsi+ sto pointing up, 8ma up, 7ema up, above ma200
        //sell
        // rsi high, sto high, rsi + sot -> down, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.25
        val stoThresholdHigh = 0.75
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 8
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams9(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: trailing stop loss 8
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, rsi+ sto pointing up
        //sell
        // rsi high, sto high, rsi + sot -> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 0.24
        val stoThresholdHigh = 0.76
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 8.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 8
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams9Alt1(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: trailing stop loss 8
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, rsi+ sto pointing up + above ma200
        //sell
        // rsi high, sto high, rsi + sot -> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 0.24
        val stoThresholdHigh = 0.76
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 8.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 8
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams9Alt2(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: trailing stop loss 8
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, rsi+ sto pointing up
        //sell
        // rsi high, sto high, rsi + sot -> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 28
        val rsiThresholdHigh = 72
        val stoThresholdLow = 0.28
        val stoThresholdHigh = 0.72
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 8.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 8
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams9Alt3(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: trailing stop loss 8
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, rsi+ sto pointing up + above ma200
        //sell
        // rsi high, sto high, rsi + sot -> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 28
        val rsiThresholdHigh = 72
        val stoThresholdLow = 0.28
        val stoThresholdHigh = 0.72
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 8.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 8
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams9Alt4(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: trailing stop loss 8
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, rsi+ sto pointing up
        //sell
        // rsi high, sto high, rsi + sot -> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 8.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    /**
     * configuration 8
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams9Alt5(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: trailing stop loss 8
        // time frames rsi 2 ; sto 4
        // buy
        // rsi low, sto low, rsi+ sto pointing up + above ma200
        //sell
        // rsi high, sto high, rsi + sot -> down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 8.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams19_1(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams19_2(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams19_3(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 4
        val rsiTimeframeSell = 4
        val rsiStoTimeframeBuy = 6
        val rsiStoTimeframeSell = 6
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 25
        val rsiThresholdHigh = 75
        val stoThresholdLow = 0.25
        val stoThresholdHigh = 0.75
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams19_4(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 4
        val rsiTimeframeSell = 4
        val rsiStoTimeframeBuy = 8
        val rsiStoTimeframeSell = 8
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 25
        val rsiThresholdHigh = 75
        val stoThresholdLow = 0.25
        val stoThresholdHigh = 0.75
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams19_5(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 6
        val rsiTimeframeSell = 6
        val rsiStoTimeframeBuy = 10
        val rsiStoTimeframeSell = 10
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 25
        val rsiThresholdHigh = 75
        val stoThresholdLow = 0.25
        val stoThresholdHigh = 0.75
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams19_6(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 6
        val rsiTimeframeSell = 6
        val rsiStoTimeframeBuy = 12
        val rsiStoTimeframeSell = 12
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 25
        val rsiThresholdHigh = 75
        val stoThresholdLow = 0.25
        val stoThresholdHigh = 0.75
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    ////
     fun createStrategyInputParams23_1(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    ////
     fun createStrategyInputParams23_2(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.7
        val fallingStrenght = 0.7
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    ////
     fun createStrategyInputParams23_3(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 25
        val rsiThresholdHigh = 75
        val stoThresholdLow = 0.25
        val stoThresholdHigh = 0.75
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    ////
     fun createStrategyInputParams23_4(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 15
        val rsiThresholdHigh = 85
        val stoThresholdLow = 0.15
        val stoThresholdHigh = 0.85
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    ////
     fun createStrategyInputParams23_5(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 10
        val rsiThresholdHigh = 90
        val stoThresholdLow = 0.1
        val stoThresholdHigh = 0.9
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    ////
     fun createStrategyInputParams23_6(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 40
        val ma14 = 70
        val ma200 = 1000
        val ma314 = 1600
        val smaShort = 15
        val smaLong = 50
        val emaShort = 25
        val emaLong = 60
        val rsiTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeBuy = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.8
        val fallingStrenght = 0.8
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }
    ////////////////// short strategies
    /**
     * configuration 1
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams1Short(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.85
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 5.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = false
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams37_1(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 20.0
        val stoThresholdHigh = 80.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams37_2(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 24.0
        val stoThresholdHigh = 76.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams37_3(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 28
        val rsiThresholdHigh = 72
        val stoThresholdLow = 28.0
        val stoThresholdHigh = 72.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams37_4(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 32
        val rsiThresholdHigh = 68
        val stoThresholdLow = 32.0
        val stoThresholdHigh = 68.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams37_5(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 22
        val rsiThresholdHigh = 78
        val stoThresholdLow = 30.0
        val stoThresholdHigh = 70.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams37_6(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 30.0
        val stoThresholdHigh = 70.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    ///
     fun createStrategyInputParams39_1(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 24.0
        val stoThresholdHigh = 76.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

    ///
     fun createStrategyInputParams39_2(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 28
        val rsiThresholdHigh = 72
        val stoThresholdLow = 28.0
        val stoThresholdHigh = 72.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams39_3(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 26
        val rsiThresholdHigh = 74
        val stoThresholdLow = 26.0
        val stoThresholdHigh = 74.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams39_4(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 30.0
        val stoThresholdHigh = 70.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams39_5(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 26
        val rsiThresholdHigh = 74
        val stoThresholdLow = 30.0
        val stoThresholdHigh = 70.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams39_6(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 28
        val rsiThresholdHigh = 72
        val stoThresholdLow = 30.0
        val stoThresholdHigh = 70.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams41_1(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 30.0
        val stoThresholdHigh = 70.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams41_2(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 28.0
        val stoThresholdHigh = 72.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams41_3(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 26.0
        val stoThresholdHigh = 74.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams41_4(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 8
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 30.0
        val stoThresholdHigh = 70.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams41_5(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 30.0
        val stoThresholdHigh = 70.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams41_6(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 24
        val rsiThresholdHigh = 76
        val stoThresholdLow = 30.0
        val stoThresholdHigh = 70.0
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 7.0
        val stopGain = 5.0
        val waitBars = 35

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }
    //
    /**
     * configuration 1
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams51Alt1(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams51Alt2(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 25
        val rsiThresholdHigh = 75
        val stoThresholdLow = 0.25
        val stoThresholdHigh = 0.75
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams51Alt3(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams51Alt4(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams51Alt5(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 8
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.6
        val fallingStrenght = 0.6
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams51Alt6(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.8
        val fallingStrenght = 0.8
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams53Alt1(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.8
        val fallingStrenght = 0.8
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams53Alt2(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.9
        val fallingStrenght = 0.9
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams53Alt3(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 1.0
        val fallingStrenght = 1.0
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams53Alt4(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 8
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.8
        val fallingStrenght = 0.8
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams53Alt5(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 7
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 8
        val rsiTimeframeBuy = 1
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 1
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.8
        val fallingStrenght = 0.8
        val stopLoss = 2.0
        val trailingStopLoss = 10.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

//    //// quad cci
//     fun createStrategyInputParamsQuadCCI1(barDuration: CandlestickInterval): StrategyInputParamsQuadCCI {
//        return StrategyInputParamsQuadCCI.builder().cci14(14).cci50(50).cci100(100).cci200(200)
//                .cci200ThresholdBuy(0).cci100ThresholdBuy(-25).cci50ThresholdBuy(-50).cci14ThresholdBuy(-100).cci14ThresholdSell(100).cci50ThresholdSell(100)
//                .stopLoss(6).trStopLoss(2).stopLossActive(false).trStopLossActive(true).cci14SellActive(false).cci50SellActive(false),
//    }
//
//     fun createStrategyInputParamsQuadCCI1Alt1(barDuration: CandlestickInterval): StrategyInputParamsQuadCCI {
//        return StrategyInputParamsQuadCCI.builder().cci14(14).cci50(50).cci100(100).cci200(200)
//                .cci200ThresholdBuy(0).cci100ThresholdBuy(-25).cci50ThresholdBuy(-50).cci14ThresholdBuy(-100).cci14ThresholdSell(100).cci50ThresholdSell(100)
//                .stopLoss(6).trStopLoss(4).stopLossActive(false).trStopLossActive(true).cci14SellActive(false).cci50SellActive(false),
//    }
//
//     fun createStrategyInputParamsQuadCCI1Alt2(barDuration: CandlestickInterval): StrategyInputParamsQuadCCI {
//        return StrategyInputParamsQuadCCI.builder().cci14(14).cci50(50).cci100(100).cci200(200)
//                .cci200ThresholdBuy(0).cci100ThresholdBuy(-25).cci50ThresholdBuy(-50).cci14ThresholdBuy(-100).cci14ThresholdSell(100).cci50ThresholdSell(100)
//                .stopLoss(6).trStopLoss(6).stopLossActive(false).trStopLossActive(true).cci14SellActive(false).cci50SellActive(false),
//    }
//
//     fun createStrategyInputParamsQuadCCI1Alt3(barDuration: CandlestickInterval): StrategyInputParamsQuadCCI {
//        return StrategyInputParamsQuadCCI.builder().cci14(14).cci50(50).cci100(100).cci200(200)
//                .cci200ThresholdBuy(0).cci100ThresholdBuy(-25).cci50ThresholdBuy(-50).cci14ThresholdBuy(-100).cci14ThresholdSell(100).cci50ThresholdSell(100)
//                .stopLoss(6).trStopLoss(8).stopLossActive(false).trStopLossActive(true).cci14SellActive(false).cci50SellActive(false),
//    }
//
//    // double time frame
//     fun createStrategyInputParamsQuadCCI1Alt4(barDuration: CandlestickInterval): StrategyInputParamsQuadCCI {
//        return StrategyInputParamsQuadCCI.builder().cci14(28).cci50(100).cci100(200).cci200(400)
//                .cci200ThresholdBuy(0).cci100ThresholdBuy(0).cci50ThresholdBuy(-20).cci14ThresholdBuy(-100).cci14ThresholdSell(100).cci50ThresholdSell(100)
//                .stopLoss(5).trStopLoss(5).stopLossActive(false).trStopLossActive(true),
//    }
//
//     fun createStrategyInputParamsQuadCCI1Alt5(barDuration: CandlestickInterval): StrategyInputParamsQuadCCI {
//        return StrategyInputParamsQuadCCI.builder().cci14(28).cci50(100).cci100(200).cci200(400)
//                .cci200ThresholdBuy(100).cci100ThresholdBuy(100).cci50ThresholdBuy(0).cci14ThresholdBuy(-100).cci14ThresholdSell(100).cci50ThresholdSell(100)
//                .stopLoss(5).trStopLoss(5).stopLossActive(false).trStopLossActive(true),
//    }
    //
    /**
     * configuration 1
     *
     * @param barDuration
     * @return
     */
     fun createStrategyInputParams28_1(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams28_2(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams28_3(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 4
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 4
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams28_4(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 4
        val rsiStoTimeframeBuy = 6
        val rsiTimeframeSell = 4
        val rsiStoTimeframeSell = 6
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = false
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams30_1(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 30
        val rsiThresholdHigh = 70
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = true
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams30_2(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 35
        val rsiThresholdHigh = 65
        val stoThresholdLow = 0.35
        val stoThresholdHigh = 0.65
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = true
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams30_3(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 25
        val rsiThresholdHigh = 75
        val stoThresholdLow = 0.25
        val stoThresholdHigh = 0.75
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = true
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams30_4(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 20
        val rsiThresholdHigh = 80
        val stoThresholdLow = 0.2
        val stoThresholdHigh = 0.8
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = true
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams30_5(barDuration: CandlestickInterval): StrategyInputParams {
        // config 1: no stop loss
        // time frames rsi 1 ; sto 2
        // buy
        // rsi low, sto low, above sma200, 8ma pointin up, ema bands up
        //sell
        // rsi high, sto high, 8ma down
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 2
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 2
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdLow = 15
        val rsiThresholdHigh = 85
        val stoThresholdLow = 0.15
        val stoThresholdHigh = 0.85
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 2.0
        val trailingStopLoss = 6.0
        val stopGain = 5.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = true
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = true
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh 
                , rule3_8maDown, rule11_rsiPointingDown 
                , rule12_StoPointingDown, rule21_priceFalling
                , rule23_stopGain, rule22_stopLoss, rule22b_trailingStopLoss) 
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams101(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdHigh = 70
        val rsiThresholdLow = 30
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 3.2
        val trailingStopLoss = 0.5
        val stopGain = -1.0
        val waitBars = 90

        //entry rules
        val rule1_rsiLow = false
        val rule2_stoLow = false
        val rule3_priceAboveSMA200 = false
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = false
        val rule5_priceBelow8MA = true
        val rule7_emaBandsPointingUp = false
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = false
        val rule11_rsiPointingDown = true
        val rule12_StoPointingDown = false
        val rule21_priceFalling = true
        val rule22_stopLoss = true
        val rule22b_trailingStopLoss = false
        val rule23_stopGain = true
        val rule24_macdFalling = false
        val rule25_shortEmaFalling = false
        val rule26_waitbars = true
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule22_stopLoss, rule22b_trailingStopLoss, rule23_stopGain, rule24_macdFalling, rule25_shortEmaFalling, rule26_waitbars)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams102(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 5.5f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdHigh = 70
        val rsiThresholdLow = 30
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 5.4
        val trailingStopLoss = 7.0
        val stopGain = 5.0
        val waitBars = 90

        //entry rules
        val rule1_rsiLow = false
        val rule2_stoLow = false
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = true
        val rule5_priceBelow8MA = false
        val rule7_emaBandsPointingUp = true
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = false
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = false
        val rule2_stoHigh = false
        val rule3_8maDown = false
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = true
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = true
        val rule24_macdFalling = true
        val rule25_shortEmaFalling = true
        val rule26_waitbars = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule22_stopLoss, rule22b_trailingStopLoss, rule23_stopGain, rule24_macdFalling, rule25_shortEmaFalling, rule26_waitbars)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams103(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdHigh = 70
        val rsiThresholdLow = 30
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 3.0
        val trailingStopLoss = 2.5
        val stopGain = -1.0
        val waitBars = 90

        //entry rules
        val rule1_rsiLow = false
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = false
        val rule4_ma8PointingUp = false
        val rule5_priceBelow8MA = true
        val rule7_emaBandsPointingUp = false
        val rule11_isRsiPointingUp = false
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = false
        val rule2_stoHigh = false
        val rule3_8maDown = true
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = true
        val rule21_priceFalling = false
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = false
        val rule23_stopGain = true
        val rule24_macdFalling = true
        val rule25_shortEmaFalling = false
        val rule26_waitbars = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule22_stopLoss, rule22b_trailingStopLoss, rule23_stopGain, rule24_macdFalling, rule25_shortEmaFalling, rule26_waitbars)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }

     fun createStrategyInputParams104(barDuration: CandlestickInterval): StrategyInputParams {
        val result: StrategyInputParams
        val barMultiplikator = true
        val extraMultiplikator = false
        val extraMultiplikatorValue = 1f
        val ma8 = 8
        val ma14 = 14
        val ma200 = 200
        val ma314 = 314
        val smaShort = 3
        val smaLong = 10
        val emaShort = 5
        val emaLong = 12
        val rsiTimeframeBuy = 2
        val rsiStoTimeframeBuy = 4
        val rsiTimeframeSell = 2
        val rsiStoTimeframeSell = 4
        val stoOscKTimeFrame = 4
        val emaIndicatorTimeframe = 4
        val smaIndicatorTimeframe = 4
        val priceTimeFrameBuy = 1
        val priceTimeFrameSell = 1
        val rsiThresholdHigh = 70
        val rsiThresholdLow = 30
        val stoThresholdLow = 0.3
        val stoThresholdHigh = 0.7
        val stoOscKThresholdLow = 20
        val stoOscKThresholdHigh = 80
        val risingStrenght = 0.5
        val fallingStrenght = 0.5
        val stopLoss = 0.5
        val trailingStopLoss = 4.0
        val stopGain = -1.0
        val waitBars = 50

        //entry rules
        val rule1_rsiLow = false
        val rule2_stoLow = true
        val rule3_priceAboveSMA200 = true
        val rule3b_priceAboveSMA314 = true
        val rule4_ma8PointingUp = false
        val rule5_priceBelow8MA = true
        val rule7_emaBandsPointingUp = false
        val rule11_isRsiPointingUp = true
        val rule12_isStoPointingUp = true
        val rule13_movingMomentum = false

        //exit rules
        val rule1_rsiHigh = true
        val rule2_stoHigh = true
        val rule3_8maDown = false
        val rule11_rsiPointingDown = false
        val rule12_StoPointingDown = false
        val rule21_priceFalling = true
        val rule22_stopLoss = false
        val rule22b_trailingStopLoss = true
        val rule23_stopGain = true
        val rule24_macdFalling = true
        val rule25_shortEmaFalling = true
        val rule26_waitbars = false
        val entryruleChain: EntryRuleChain = EntryRuleChain(rule1_rsiLow, rule2_stoLow, rule3_priceAboveSMA200, rule3b_priceAboveSMA314, rule4_ma8PointingUp, rule5_priceBelow8MA, rule7_emaBandsPointingUp
                , rule11_isRsiPointingUp, rule12_isStoPointingUp, rule13_movingMomentum)
        val exitRuleChain: ExitRuleChain = ExitRuleChain(rule1_rsiHigh, rule2_stoHigh
                , rule3_8maDown, rule11_rsiPointingDown
                , rule12_StoPointingDown, rule21_priceFalling
                , rule22_stopLoss, rule22b_trailingStopLoss, rule23_stopGain, rule24_macdFalling, rule25_shortEmaFalling, rule26_waitbars)
        result = StrategyInputParamsBuilder.createStrategyInputParams(barDuration, barMultiplikator, extraMultiplikator, extraMultiplikatorValue, ma8,
                ma14, ma200, ma314, smaShort, smaLong, emaShort, emaLong, rsiTimeframeBuy, rsiTimeframeSell,
                rsiStoTimeframeBuy, rsiStoTimeframeSell, stoOscKTimeFrame, emaIndicatorTimeframe, smaIndicatorTimeframe, priceTimeFrameBuy,
                priceTimeFrameSell, rsiThresholdLow, rsiThresholdHigh, stoThresholdLow, stoThresholdHigh,
                stoOscKThresholdLow, stoOscKThresholdHigh, risingStrenght, fallingStrenght, stopLoss, trailingStopLoss, stopGain, waitBars, entryruleChain, exitRuleChain)
        return result
    }
}