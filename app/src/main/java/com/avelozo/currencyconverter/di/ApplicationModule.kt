package com.avelozo.currencyconverter.di

import com.avelozo.currencyconverter.contract.CurrencyConverterContract
import com.avelozo.currencyconverter.presenter.RatePresenter
import com.avelozo.currencyconverter.repository.IRatesRepository
import com.avelozo.currencyconverter.repository.RatesRepository
import com.avelozo.currencyconverter.request.IRateRequest
import com.avelozo.currencyconverter.request.RateRequest
import com.avelozo.currencyconverter.retrofit.RetrofitFactory
import com.github.salomonbrys.kodein.*

class ApplicationModule {

    val module = Kodein.Module {


        bind<IRatesRepository>() with provider { RatesRepository(instance()) }

        bind<IRateRequest>() with provider { RateRequest(instance()) }

        bind<CurrencyConverterContract.Presenter>() with provider { RatePresenter(instance()) }

        bind<RatePresenter>() with provider {
            RatePresenter(instance())
        }

        bind<RetrofitFactory>() with singleton { RetrofitFactory() }


    }

}