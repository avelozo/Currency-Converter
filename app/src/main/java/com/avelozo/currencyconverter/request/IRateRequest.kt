package com.avelozo.currencyconverter.request

import io.reactivex.Single

interface IRateRequest {

    fun getRates(base: String) : Single<RateReceiver>
}