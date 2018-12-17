package com.avelozo.currencyconverter.request

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RateService {

    companion object {
        val BASE_URL = "https://revolut.duckdns.org/"
    }

    @GET("latest")
    fun getRates(@Query("base") base: String): Call<RateReceiver>
}