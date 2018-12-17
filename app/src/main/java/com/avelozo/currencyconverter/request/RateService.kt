package com.avelozo.currencyconverter.request

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface RateService {

    @GET("latest")
    fun getRates(@Query("base") base: String): Single<RateReceiver>
}