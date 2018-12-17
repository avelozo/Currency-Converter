package com.avelozo.currencyconverter.request

import com.avelozo.currencyconverter.retrofit.RetrofitFactory
import io.reactivex.Single
import java.lang.Exception

class RateRequest (val retrofitFactory: RetrofitFactory) : IRateRequest {


   override fun getRates(base: String) : Single<RateReceiver>{

        try {
            return retrofitFactory.baseRequest()
                .create(RateService::class.java)
                .getRates(base)

        }catch(ex:Exception){

            return Single.error(ex)

        }


    }



}