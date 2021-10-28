package com.isaratech.signalsender.utils

import java.lang.StringBuilder
import java.time.temporal.ChronoUnit

import java.text.DecimalFormat
import java.text.NumberFormat

import java.time.LocalDateTime

import java.text.SimpleDateFormat
import java.time.Duration

import java.time.format.DateTimeFormatter
import java.util.*


class Formatter {
    companion object {
        val simpleFormatter = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        private val ONLY_DATE_FORMATTER = SimpleDateFormat("yyyy.MM.dd")
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        private val PERCENT_FORMAT: NumberFormat = DecimalFormat("0.000%")
        fun formatDate(date: LocalDateTime?): String {
            return DATE_TIME_FORMATTER.format(date)
        }

        fun formatDate(timestamp: Long): String {
            return simpleFormatter.format(Date(timestamp))
        }

        fun formatOnlyDate(timestamp: Long): String {
            return ONLY_DATE_FORMATTER.format(Date(timestamp))
        }

        fun formatPercent(percentage: Double): String {
            return PERCENT_FORMAT.format(percentage)
        }

        fun formatDecimal(decimal: Double): String {
            if (decimal == Math.floor(decimal) && java.lang.Double.isFinite(decimal)) return decimal.toString()
            var zeroes = 0
            val s = String.format("%.12f", decimal).replace("[,.]".toRegex(), "")
            for (c in s.toCharArray()) {
                if (c == '0') {
                    zeroes++
                } else if (c != '-') {
                    break
                }
            }
            val decimalFormat: NumberFormat = DecimalFormat("0." + "0".repeat(3 + zeroes))
            return decimalFormat.format(decimal)
        }

        fun formatLarge(large: Long): String {
            val s = large.toString()
            val builder = StringBuilder()
            var count = 0
            val chars = s.toCharArray()
            for (i in chars.indices.reversed()) {
                builder.append(chars[i])
                count++
                if (count == 3 && i != 0) {
                    count = 0
                    builder.append(",")
                }
            }
            return builder.reverse().toString()
        }

        fun formatDuration(duration: Long): String {
            return formatDuration(Duration.of(duration, ChronoUnit.MILLIS))
        }

        fun formatDuration(duration: Duration): String {
            val seconds: Long = duration.getSeconds()
            val absSeconds = Math.abs(seconds)
            val positive = String.format(
                    "%d:%02d:%02d",
                    absSeconds / 3600,
                    absSeconds % 3600 / 60,
                    absSeconds % 60)
            return if (seconds < 0) "-$positive" else positive
        }
    }
}