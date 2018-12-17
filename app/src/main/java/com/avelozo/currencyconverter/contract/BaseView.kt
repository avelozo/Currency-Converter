package com.avelozo.currencyconverter.contract

interface BaseView {

    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)

}