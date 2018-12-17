package com.avelozo.currencyconverter.repository

import com.avelozo.currencyconverter.request.IRateRequest
import com.avelozo.currencyconverter.request.RateReceiver
import io.reactivex.Single

class RatesRepository (val rateRequest: IRateRequest) : IRatesRepository{

   override fun getRates(base: String): Single<RateReceiver> {
        return rateRequest.getRates(base)
    }

}