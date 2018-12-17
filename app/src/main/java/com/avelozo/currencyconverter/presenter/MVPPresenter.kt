package com.avelozo.currencyconverter.presenter

import com.avelozo.currencyconverter.contract.BasePresenter
import com.avelozo.currencyconverter.contract.BaseView
import io.reactivex.disposables.CompositeDisposable

open class MvpPresenter<T : BaseView> : BasePresenter<T> {

    protected val disposables by lazy {
        CompositeDisposable()
    }

    protected var view: T? = null

    override fun attach(view: T) {
        this.view = view
    }

    override fun detach() {
        disposables.clear()
        this.view = null
    }
}