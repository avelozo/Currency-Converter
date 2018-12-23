package com.avelozo.currencyconverter.repository

import java.math.BigDecimal

data class Currency(var base: String, var date: String, var rates: ArrayList<Pair<String, BigDecimal>>)