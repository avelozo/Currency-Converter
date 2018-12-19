package com.avelozo.currencyconverter.view


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avelozo.currencyconverter.contract.CurrencyConverterContract
import java.math.BigDecimal
import com.avelozo.currencyconverter.R
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.fragment_currency_converter.*
import java.util.ArrayList

class CurrencyConverterFragment : FragmentAbstract(), CurrencyConverterContract.View {

    private val presenter: CurrencyConverterContract.Presenter by injector.instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_currency_converter, container, false) as View
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearManager = LinearLayoutManager(this.context)
        linearManager.orientation = LinearLayoutManager.VERTICAL
        rateRecycler.layoutManager = linearManager


        presenter.attach(this)
        presenter.start("EUR")

    }


    override fun updateRates(rates: ArrayList<Pair<String, BigDecimal>>) {

        rateRecycler.adapter = CurrencyConverterAdapter(rates)

    }

    override fun showRatesErrorMessage() {
        Log.d("rate","showErrorMessage")
    }

}
