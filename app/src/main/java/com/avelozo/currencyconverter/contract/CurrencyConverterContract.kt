package com.avelozo.currencyconverter.contract

import com.avelozo.currencyconverter.presenter.MvpPresenter
import java.math.BigDecimal

interface CurrencyConverterContract {
    interface View : BaseView {

       fun updateRates(rates: Map<String, BigDecimal>)
       fun showRatesErrorMessage()

    }

    abstract class Presenter : MvpPresenter<View>(){

        abstract fun start(base: String)

    }
}