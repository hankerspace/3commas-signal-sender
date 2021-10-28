package com.isaratech.signalsender.service.analysis

import com.isaratech.signalsender.strategy.implementations.*
import com.isaratech.signalsender.service.analysis.AbstractAnalysisService
import java.util.HashMap

class StrategyAnalysisFactory {
    companion object {
        private val strategyAnalysisBySymbol: HashMap<String, AbstractAnalysisService> = HashMap<String, AbstractAnalysisService>()
        
        fun getStrategy(symbol: String, strategyAnalysisType: StrategyAnalysisType = StrategyAnalysisType.SIMPLE_STRATEGY): AbstractAnalysisService {
            // Cache strategies by symbol
            if(strategyAnalysisBySymbol.containsKey(symbol)) return strategyAnalysisBySymbol[symbol]!!

            val strategyAnalysis = StrategyBasedAnalysisService()
            when (strategyAnalysisType) {
                StrategyAnalysisType.ADX_STRATEGY -> strategyAnalysis.strategy = ADXStrategy()
                StrategyAnalysisType.ALEX_STRATEGY -> strategyAnalysis.strategy = AlexStrategy()
                StrategyAnalysisType.CCI_CORRECTION_STRATEGY -> strategyAnalysis.strategy = CCICorrectionStrategy()
                StrategyAnalysisType.EMA_STRATEGY -> strategyAnalysis.strategy = EMAStrategy()
                StrategyAnalysisType.GLOBAL_EXTREMA_STRATEGY -> strategyAnalysis.strategy = GlobalExtremaStrategy()
                StrategyAnalysisType.MACD_STRATEGY -> strategyAnalysis.strategy = MacdStrategy()
                StrategyAnalysisType.MOVING_MOMENTUM_STRATEGY -> strategyAnalysis.strategy = MovingMomentumStrategy()
                StrategyAnalysisType.PIVOT_POINT_REVERSAL_STRATEGY -> strategyAnalysis.strategy = PivotPointReversalStrategy()
                StrategyAnalysisType.RANDOM_STRATEGY -> strategyAnalysis.strategy = RandomStrategy()
                StrategyAnalysisType.RSI2_STRATEGY -> strategyAnalysis.strategy = RSI2Strategy()
                StrategyAnalysisType.TOTO_STRATEGY -> strategyAnalysis.strategy = TotoStrategy()
                StrategyAnalysisType.SIMPLE_RANGE_SCALPER_LONG_TERM_STRATEGY -> strategyAnalysis.strategy = SimpleRangeScalperLongTermStrategy()
                StrategyAnalysisType.SIMPLE_RANGE_SCALPER_SHORT_TERM_STRATEGY -> strategyAnalysis.strategy = SimpleRangeScalperShortTermStrategy()
                StrategyAnalysisType.FINAL_TRADING_STRATEGY_V1B -> strategyAnalysis.strategy = FinalTradingStrategyV1b()
                StrategyAnalysisType.FINAL_STRATEGY_SHORT_V2 -> strategyAnalysis.strategy = FinalStrategyShortV2()
            }

            val analysis = if(strategyAnalysisType == StrategyAnalysisType.SIMPLE_STRATEGY)
                SimpleAnalysisService()
            else if(strategyAnalysisType == StrategyAnalysisType.ANALYSIS_TOTO_STRATEGY)
                SimpleTotoAnalysisService()
            else
                strategyAnalysis

            strategyAnalysisBySymbol[symbol] = analysis
            return strategyAnalysisBySymbol[symbol]!!
        }
    }
}
