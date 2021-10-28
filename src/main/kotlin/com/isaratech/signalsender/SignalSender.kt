package com.isaratech.signalsender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SignalSender

fun main(args: Array<String>) {
    runApplication<SignalSender>(*args)
}
