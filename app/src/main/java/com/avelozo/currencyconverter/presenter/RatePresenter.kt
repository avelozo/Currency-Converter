package com.avelozo.currencyconverter.presenter



import com.avelozo.currencyconverter.contract.CurrencyConverterContract
import com.avelozo.currencyconverter.repository.IRatesRepository
import com.avelozo.currencyconverter.rx.ComposeRx.applySingleSchedulers

class RatePresenter(val ratesRepository: IRatesRepository) : CurrencyConverterContract.Presenter() {

    override fun start(base: String) {

        ratesRepository.getRates(base)
            .compose(applySingleSchedulers())
            .subscribe (
                {rateReceiver ->
                    view?.updateRates(rateReceiver.rates)

                },
        {
            view?.showRatesErrorMessage()
        }
        ).apply {
                disposables.add(this)
            }
    }



}