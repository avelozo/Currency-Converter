package com.avelozo.currencyconverter.view

import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avelozo.currencyconverter.contract.CurrencyConverterContract
import java.math.BigDecimal
import com.avelozo.currencyconverter.R
import com.github.salomonbrys.kodein.instance

class CurrencyConverterFragment : FragmentAbstract(), CurrencyConverterContract.View {

    private val presenter: CurrencyConverterContract.Presenter by injector.instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currency_converter, container, false) as View
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)
        presenter.start("EUR")

    }


    override fun updateRates(rates: Map<String, BigDecimal>) {

    }

    override fun showRatesErrorMessage() {
        Log.d("rate","showErrorMessage")
    }

}
