package com.isaratech.signalsender.strategy.ta4jindicator.general

import org.ta4j.core.Indicator
import org.ta4j.core.indicators.AbstractEMAIndicator
import org.ta4j.core.num.Num

class RmaIndicator(src: Indicator<Num>, length:Int) : AbstractEMAIndicator(src,length,1.0/length) {

}