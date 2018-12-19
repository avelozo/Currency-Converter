package com.avelozo.currencyconverter.view

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.avelozo.currencyconverter.R
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList


class CurrencyConverterAdapter(
    private var mValues: ArrayList<Pair<String, BigDecimal>>) : RecyclerView.Adapter<CurrencyConverterAdapter.ViewHolder>() {
    var defaultValue : BigDecimal = 1.toBigDecimal()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val convertedValue = defaultValue * mValues[position].second

        holder.txtCurrencyName.text = mValues[position].first

        if(!holder.editTextCurrencyValue.hasFocus())
            holder.editTextCurrencyValue.setText(convertedValue.toString())

        holder.editTextCurrencyValue.onFocusChangeListener =  View.OnFocusChangeListener { _, hasFocus ->

            if(!hasFocus){
              return@OnFocusChangeListener
            }

            Collections.swap(mValues, position, 0)
            notifyItemMoved(position, 0)

        }

        holder.editTextCurrencyValue.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                defaultValue = holder.editTextCurrencyValue.text.toString().toBigDecimal()
                notifyDataSetChanged()

            }

        })

    }

    override fun getItemCount(): Int {
        return mValues.size
    }


    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val imgFlag : ImageView  = mView.findViewById(R.id.imgFlag)
        val txtCurrencyName: TextView = mView.findViewById(R.id.txtCurrencyName)
        val editTextCurrencyValue: EditText = mView.findViewById(R.id.editTextCurrencyValue)
        val currencyItem : ConstraintLayout = mView.findViewById(R.id.currencyItem)
    }
}
