package com.avelozo.currencyconverter.presenter



import com.avelozo.currencyconverter.contract.CurrencyConverterContract
import com.avelozo.currencyconverter.repository.IRatesRepository
import com.avelozo.currencyconverter.rx.ComposeRx.applySingleSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

class RatePresenter(private val ratesRepository: IRatesRepository) : CurrencyConverterContract.Presenter() {
    override fun updateBaseAmount(base : String, amount: BigDecimal) {
        ratesRepository.
            updateBaseAmount(base, amount)
            .compose(applySingleSchedulers())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {currency ->
                    view?.updateRates(currency.rates)

                },
                {
                    view?.showRatesErrorMessage()
                }
            ).apply {
                disposables.add(this)
            }
    }


    override fun getRates() {
        ratesRepository.getRates()
            .compose(applySingleSchedulers())
            .observeOn(AndroidSchedulers.mainThread())
            .repeatWhen{ done -> done.delay(1, TimeUnit.SECONDS) }
            .subscribe (
                {currency ->
                    view?.updateRates(currency.rates)

                },
        {
            view?.showRatesErrorMessage()
        }
        ).apply {
                disposables.add(this)
            }
    }



}