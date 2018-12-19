package com.avelozo.currencyconverter.presenter



import com.avelozo.currencyconverter.contract.CurrencyConverterContract
import com.avelozo.currencyconverter.repository.IRatesRepository
import com.avelozo.currencyconverter.rx.ComposeRx.applySingleSchedulers
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

class RatePresenter(val ratesRepository: IRatesRepository) : CurrencyConverterContract.Presenter() {

    override fun start(base: String) {

        ratesRepository.getRates(base)
            .compose(applySingleSchedulers())
            .observeOn(AndroidSchedulers.mainThread())
            //.repeatWhen{ done -> done.delay(1, TimeUnit.SECONDS) }
            .subscribe (
                {rateReceiver ->
                    var ratesList: ArrayList<Pair<String,BigDecimal>> = arrayListOf()
                    rateReceiver.rates.entries.map{
                        ratesList.add(Pair(it.key,it.value))
                    }
                    view?.updateRates(ratesList)

                },
        {
            view?.showRatesErrorMessage()
        }
        ).apply {
                disposables.add(this)
            }
    }



}