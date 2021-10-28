package com.isaratech.signalsender

import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class OrderServiceTest {
    @Test
    fun testFormatValue() {
        val amount = BigDecimal(0.66300000)
        val df = DecimalFormat()
        df.maximumFractionDigits = 2
        val rounded = df.format(amount)

        println(rounded)    // Wrong

        println(amount.setScale(2, RoundingMode.HALF_EVEN).toString())
    }
}
