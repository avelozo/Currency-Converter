package com.avelozo.currencyconverter.presenter

import com.avelozo.currencyconverter.contract.RateContract
import com.avelozo.currencyconverter.repository.IRatesRepository
import com.avelozo.currencyconverter.rx.ComposeRx.applySingleSchedulers

class RatePresenter(val ratesRepository: IRatesRepository) : RateContract.Presenter() {


    override fun start(base: String) {

        ratesRepository.getRates(base)
            .compose(applySingleSchedulers())
            .doOnSubscribe {
                view?.showProgress()
            }
            .doFinally {
                view?.hideProgress()
            }.subscribe (
                {rateReceiver ->
                    view?.updateRates(rateReceiver.rates)

                },
        {
            view?.showMessage("Could not find rates. Please, try again later")
        }
        ).apply {
                disposables.add(this)
            }
    }



}