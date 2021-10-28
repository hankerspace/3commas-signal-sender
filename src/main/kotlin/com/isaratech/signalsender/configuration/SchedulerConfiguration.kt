package com.isaratech.signalsender.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler


@Configuration
open class SchedulerConfiguration {
    @Bean
    open fun threadPoolTaskScheduler(): ThreadPoolTaskScheduler? {
        val threadPoolTaskScheduler = ThreadPoolTaskScheduler()
        threadPoolTaskScheduler.poolSize = 100
        return threadPoolTaskScheduler
    }
}