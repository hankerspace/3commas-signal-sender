package com.isaratech.signalsender.strategy

import org.slf4j.LoggerFactory
import org.ta4j.core.BaseStrategy
import org.ta4j.core.Rule
import org.ta4j.core.TradingRecord
import java.lang.StringBuilder
import org.ta4j.core.trading.rules.AndRule

import org.ta4j.core.trading.rules.OrRule
import java.lang.reflect.Field


class LoggedBaseStrategy(entryRule: Rule?, exitRule: Rule?) : BaseStrategy(entryRule, exitRule) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun shouldExit(index: Int, tradingRecord: TradingRecord?): Boolean {
        val result = super.shouldExit(index, tradingRecord)
        if (result) {
            //log.info("BaseStrategy should Exit")
            val rule: Rule = exitRule
            var sb = StringBuilder()
            sb = handleRule(rule, index, tradingRecord, sb)
            //log.info(sb.toString())
        }

        return result
    }

    override fun shouldEnter(index: Int, tradingRecord: TradingRecord?): Boolean {
        val result = super.shouldEnter(index, tradingRecord)
        if (result) {
            //log.info("BaseStrategy should Enter")
            val rule: Rule = entryRule
            var sb = StringBuilder()
            sb = handleRule(rule, index, tradingRecord, sb)
            //log.info(sb.toString())
        }
        else {
            //log.info("BaseStrategy should NOT Enter")
            val rule: Rule = entryRule
            var sb = StringBuilder()
            sb = handleRule(rule, index, tradingRecord, sb)
            //log.info(sb.toString())
        }
        return result
    }

    fun handleRule(rule: Rule, index: Int, tradingRecord: TradingRecord?, sb: StringBuilder): StringBuilder {
        if (rule is AndRule) {
            handleAndRule(rule, index, tradingRecord, sb)
        } else if (rule is OrRule) {
            handleOrRule(rule, index, tradingRecord, sb)
        } else {
            val s = rule.isSatisfied(index, tradingRecord)
            if (s) {
//                System.out.println("Rule satifsfied " + rule.toString());
                sb.append("Rule satifsfied $rule\n")
            }
            else {
                sb.append("Rule NOT satifsfied $rule\n")
            }
        }
        return sb
    }

    private fun handleOrRule(rule: Rule, index: Int, tradingRecord: TradingRecord?, sb: StringBuilder) {
        try {
            val orRule = rule as OrRule
            val fields: Array<Field> = OrRule::class.java.declaredFields
            val fRule1: Field = fields[0]
            val fRule2: Field = fields[1]
            fRule1.setAccessible(true)
            fRule2.setAccessible(true)
            val rule1 = fRule1.get(orRule) as Rule
            val rule2 = fRule2.get(orRule) as Rule
            handleRule(rule1, index, tradingRecord, sb)
            sb.append("OR\n")
            handleRule(rule2, index, tradingRecord, sb)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun handleAndRule(rule: Rule, index: Int, tradingRecord: TradingRecord?, sb: StringBuilder) {
        try {
            val andRule = rule as AndRule
            val fields: Array<Field> = AndRule::class.java.declaredFields
            val fRule1: Field = fields[0]
            val fRule2: Field = fields[1]
            fRule1.setAccessible(true)
            fRule2.setAccessible(true)
            val rule1 = fRule1.get(andRule) as Rule
            val rule2 = fRule2.get(andRule) as Rule
            handleRule(rule1, index, tradingRecord, sb)
            sb.append("AND\n")
            handleRule(rule2, index, tradingRecord, sb)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}