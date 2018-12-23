package com.avelozo.currencyconverter.request

import java.math.BigDecimal

data class RateReceiver(val base: String, val date: String, var rates: Map<String, BigDecimal>)