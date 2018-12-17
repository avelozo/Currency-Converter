package com.avelozo.currencyconverter.repository

import com.avelozo.currencyconverter.request.RateReceiver
import io.reactivex.Single

interface IRatesRepository {
    fun getRates(base : String) : Single<RateReceiver>
}