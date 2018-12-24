package com.avelozo.currencyconverter.view


import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.avelozo.currencyconverter.contract.CurrencyConverterContract
import java.math.BigDecimal
import com.avelozo.currencyconverter.R
import com.github.salomonbrys.kodein.instance
import kotlinx.android.synthetic.main.fragment_currency_converter.*
import java.util.ArrayList

class CurrencyConverterFragment : FragmentAbstract(), CurrencyConverterContract.View {

    private val presenter: CurrencyConverterContract.Presenter by injector.instance()
    private val DEFAULT_BASE = "EUR"
    private  var recyclerViewState : Parcelable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_currency_converter, container, false) as View
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.updateBaseAmount(DEFAULT_BASE)
        presenter.getRates()

        val linearManager = LinearLayoutManager(this.context)
        linearManager.orientation = LinearLayoutManager.VERTICAL
        rateRecycler?.layoutManager = linearManager

    }


    override fun updateRates(rates: ArrayList<Pair<String, BigDecimal>>) {

        rateRecycler?.adapter = CurrencyConverterAdapter(rates){ base , amount ->
            presenter.updateBaseAmount(base , amount)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerViewState = rateRecycler?.layoutManager?.onSaveInstanceState()

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        recyclerViewState?.let {
            rateRecycler?.layoutManager?.onRestoreInstanceState(it)
        }
    }


    override fun showRatesErrorMessage() {
      Toast.makeText(context, getString(R.string.error_msg_get_rates), Toast.LENGTH_LONG).show()
    }

}
