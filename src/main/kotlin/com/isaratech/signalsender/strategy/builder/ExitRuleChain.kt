package com.isaratech.signalsender.strategy.builder

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data

@Data
@AllArgsConstructor
@Builder
class ExitRuleChain(
     val rule1_rsiHigh: Boolean = false,
     val rule2_stoHigh: Boolean = false,
     val rule3_8maDown: Boolean = false,
     val rule11_rsiPointingDown: Boolean = false,
     val rule12_StoPointingDown: Boolean = false,
     val rule21_priceFalling: Boolean = false,
     val rule22_stopLoss: Boolean = false,
     val rule22b_trailingStopLoss: Boolean = false,
     val rule23_stopGain: Boolean = false,
     val rule24_macdFalling: Boolean = false,
     val rule25_shortEmaFalling: Boolean = false,
     val rule26_waitbars: Boolean = false
){
    override fun toString(): String {
        return "ExitRuleChain(rule1_rsiHigh=$rule1_rsiHigh, rule2_stoHigh=$rule2_stoHigh, rule3_8maDown=$rule3_8maDown, rule11_rsiPointingDown=$rule11_rsiPointingDown, rule12_StoPointingDown=$rule12_StoPointingDown, rule21_priceFalling=$rule21_priceFalling, rule22_stopLoss=$rule22_stopLoss, rule22b_trailingStopLoss=$rule22b_trailingStopLoss, rule23_stopGain=$rule23_stopGain, rule24_macdFalling=$rule24_macdFalling, rule25_shortEmaFalling=$rule25_shortEmaFalling, rule26_waitbars=$rule26_waitbars)"
    }
}