package com.avelozo.currencyconverter

import com.avelozo.currencyconverter.contract.CurrencyConverterContract
import com.avelozo.currencyconverter.presenter.RatePresenter
import com.avelozo.currencyconverter.repository.Currency
import com.avelozo.currencyconverter.repository.RatesRepository
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import java.lang.Exception
import java.math.BigDecimal
import org.junit.*


class CurrencyConverterTest {

    private  var rateRepository: RatesRepository = mock()
    private  var view : CurrencyConverterContract.View = mock()
    private  var presenter : RatePresenter =  RatePresenter(rateRepository)
    private  var countAttempts = 0


    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setup() {
        presenter.attach(view)
    }

    @Test
    fun onGetRatesSuccess(){
        val rates = createRatesList()
        doAnswer {mockedObservableWithOneSuccessfulResult()}.whenever(rateRepository).getRates()
            presenter.getRates()
            verify(view, times(1)).updateRates(rates)
        }


    @Test
    fun onGetRatesFailure(){
        doAnswer { Single.error<Currency>(Exception("GetRatesError"))}.whenever(rateRepository).getRates()
        presenter.getRates()
        verify(view, times(1)).showRatesErrorMessage()
    }

    @Test
    fun onUpdateRatesFailure(){
        doAnswer { Single.error<Currency>(Exception("UpdateRatesError"))}.whenever(rateRepository).updateBaseAmount("EUR", 200.toBigDecimal())
        presenter.updateBaseAmount("EUR", 200.toBigDecimal())
        verify(view, times(1)).showRatesErrorMessage()

    }


    private fun createRatesList() : ArrayList<Pair<String,BigDecimal>>{
        val rates: ArrayList<Pair<String,BigDecimal>> = arrayListOf()
        rates.add(Pair("EUR", 2.3.toBigDecimal()))
        rates.add(Pair("BRL", 8.toBigDecimal()))
        rates.add(Pair("USD", 2.toBigDecimal()))
        return rates
    }

    private fun mockedObservableWithOneSuccessfulResult(): Single<Currency> {
        val rates = createRatesList()
            return Single.create { e -> if(countAttempts == 0) {
                countAttempts++
                e.onSuccess(Currency("EUR", "12/31/2018", rates))
            }else{
                e.onError(Throwable("Get Currency Error"))
            }
        }
    }
}

