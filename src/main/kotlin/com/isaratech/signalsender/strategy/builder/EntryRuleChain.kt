package com.isaratech.signalsender.strategy.builder

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data


@Data
@AllArgsConstructor
@Builder
class EntryRuleChain(
    //1- RSI is low and pointing up (v)
    //2- Stochastic is low and pointing up (v)
    //3- Price is above SMA200&314 ???? really ?
    //4- 8-MA is pointing up (v)
    //5- Price is near or below the 8-MA (v) (the further away from the 8-MA price is, the higher probability price will turn back towards it)
    //6- Price is _above_ a known area of resistance (use Fib levels to determine those zones)
    //7- Moving EMA bands are angled up
    //8- Price is not approaching prior resistance
    //9- Price is near the bottom of an identified cycle
    //10- Still room to grow in larger time frames
         val rule1_rsiLow: Boolean = false,
         val rule2_stoLow: Boolean = false,
         val rule3_priceAboveSMA200: Boolean = false,
         val rule3b_priceAboveSMA314: Boolean = false,
         val rule4_ma8PointingUp: Boolean = false,
         val rule5_priceBelow8MA: Boolean = false,
         val rule7_emaBandsPointingUp: Boolean = false,
         val rule11_isRsiPointingUp: Boolean = false,
         val rule12_isStoPointingUp: Boolean = false,
         val rule13_movingMomentum: Boolean = false
){
    override fun toString(): String {
        return "EntryRuleChain(rule1_rsiLow=$rule1_rsiLow, rule2_stoLow=$rule2_stoLow, rule3_priceAboveSMA200=$rule3_priceAboveSMA200, rule3b_priceAboveSMA314=$rule3b_priceAboveSMA314, rule4_ma8PointingUp=$rule4_ma8PointingUp, rule5_priceBelow8MA=$rule5_priceBelow8MA, rule7_emaBandsPointingUp=$rule7_emaBandsPointingUp, rule11_isRsiPointingUp=$rule11_isRsiPointingUp, rule12_isStoPointingUp=$rule12_isStoPointingUp, rule13_movingMomentum=$rule13_movingMomentum)"
    }
}