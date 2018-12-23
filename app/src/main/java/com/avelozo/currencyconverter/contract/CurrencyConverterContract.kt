package com.avelozo.currencyconverter.contract

import com.avelozo.currencyconverter.presenter.MvpPresenter
import java.math.BigDecimal
import java.util.ArrayList

interface CurrencyConverterContract {
    interface View : BaseView {

       fun updateRates(rates: ArrayList<Pair<String, BigDecimal>>)
       fun showRatesErrorMessage()

    }

    abstract class Presenter : MvpPresenter<View>(){

        abstract fun getRates()
        abstract fun updateBaseAmount(base: String , amount: BigDecimal = 1.toBigDecimal())

    }
}