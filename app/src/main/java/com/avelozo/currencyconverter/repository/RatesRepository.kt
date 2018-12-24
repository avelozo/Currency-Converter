package com.avelozo.currencyconverter.repository

import com.avelozo.currencyconverter.request.IRateRequest
import io.reactivex.Single
import java.math.BigDecimal

class RatesRepository (val rateRequest: IRateRequest) : IRatesRepository{

     private var currencyRepository : Currency? = null
     private var  base = ""
     private var amount: BigDecimal = 1.toBigDecimal()

   override fun getRates(): Single<Currency> {
       return rateRequest.getRates(base).map {rateReceiver ->
           val remoteRates = rateReceiver.rates.map { entry ->
               entry.toPair() } as ArrayList<Pair<String, BigDecimal>>
           if (currencyRepository == null ) {
               remoteRates.add(0, Pair(base,amount))
               currencyRepository = Currency(rateReceiver.base, rateReceiver.date, remoteRates)

               currencyRepository
           } else {
               currencyRepository?.base = rateReceiver.base
               currencyRepository?.date = rateReceiver.date

               val mainRatePair = currencyRepository?.rates?.find { it.first == base }
               mainRatePair?.let {
                   if(currencyRepository?.rates?.contains(mainRatePair) == true)
                   currencyRepository?.rates?.remove(mainRatePair)
                   currencyRepository?.rates?.add(0, Pair(base, amount))
               }
                val iterator = rateReceiver.rates.iterator()
               iterator.forEach{ rate ->
                  val index =  currencyRepository?.rates?.indexOf(currencyRepository?.rates?.find { it.first == rate.key })
                   if(index == null || index < 0){
                       currencyRepository?.rates?.add(Pair(rate.key,rate.value * amount))
                   } else if(index!= 0)
                     currencyRepository?.rates?.set(index,Pair(rate.key,rate.value * amount) )
               }
               currencyRepository
           }
       }
   }

    override fun updateBaseAmount(base: String, amount: BigDecimal) : Single<Currency> {
        this.amount = amount
        this.base = base
        return getRates()
    }

}