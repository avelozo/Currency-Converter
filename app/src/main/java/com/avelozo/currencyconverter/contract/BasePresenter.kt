package com.avelozo.currencyconverter.contract

interface BasePresenter<in T: BaseView> {

    fun attach(view: T)
    fun detach()
}