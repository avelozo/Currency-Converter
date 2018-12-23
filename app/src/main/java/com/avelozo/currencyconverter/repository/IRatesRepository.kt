package com.avelozo.currencyconverter.repository

import io.reactivex.Single
import java.math.BigDecimal

interface IRatesRepository {
    fun getRates() : Single<Currency>
    fun updateBaseAmount(base: String, amount: BigDecimal = 1.toBigDecimal()) : Single<Currency>
}