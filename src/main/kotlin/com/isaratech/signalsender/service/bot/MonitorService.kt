package com.isaratech.signalsender.service.bot

import com.isaratech.signalsender.service.exchange.ExchangeService
import com.isaratech.signalsender.utils.Utils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import javax.annotation.PostConstruct
import kotlin.math.abs

@Service
@EnableScheduling
class MonitorService(@Autowired private val exchangeService: ExchangeService) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @PostConstruct
    fun run() {
        //tickData()
        val diffTime = abs(exchangeService.getServerTime()!! - System.currentTimeMillis())
        if (diffTime > 1000) {
            log.error("Diff between system & server > 1000ms, sync your system clock.")
            //exitProcess(1)
        } else {
            log.info("Timestamps diff $diffTime ms")
        }
        log.info("========== CURRENT STRATEGY : " + SelectedStrategyType.strategyType + " ================")


    }
}
