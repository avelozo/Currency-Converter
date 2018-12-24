package com.avelozo.currencyconverter.view

import android.os.Handler
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
import android.os.Looper
import java.text.NumberFormat


class CurrencyConverterAdapter(
    private var mValues: ArrayList<Pair<String, BigDecimal>>,val smoothScroll: () -> Unit,
    val onUpdateAmount: (mainCurrency: String, amount : BigDecimal) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var handler = Handler(Looper.getMainLooper())
    private var workRunnable: Runnable? = null
    private val TYPE_MAIN = 0
    private val TYPE_REGULAR = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == TYPE_MAIN){
          MyMainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_currency_item, parent, false))
        } else {
           MyRegularViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currencyCode = mValues[position].first
        val currencyName =  holder.itemView.context.getString(CurrencyInfo.getCurrencyName(currencyCode))
        val currencyFlagId = CurrencyInfo.getFlagId(currencyCode)

        if(position == 0){
            holder as MyMainViewHolder
            val format = NumberFormat.getInstance(Locale.UK)
            format.maximumFractionDigits = 2
            val currencyValue = format.format(mValues[position].second)

            holder.editTextCurrencyValue.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable?) {


                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    if(!holder.editTextCurrencyValue.isFocused) return

                    handler.removeCallbacks(workRunnable)
                    workRunnable = Runnable {
                        if(holder.editTextCurrencyValue.text.toString().isNotBlank()) {

                            var value =  format.parse(holder.editTextCurrencyValue.text.toString()).toString()
                            onUpdateAmount(
                                holder.txtCurrencyCode.text.toString(),
                                BigDecimal(value)
                            )

                        }
                    }
                    handler.postDelayed(workRunnable, 500)
                }
            })

            holder.txtCurrencyCode.text = currencyCode
            holder.txtCurrencyName.text = currencyName
            holder.editTextCurrencyValue.setText(currencyValue)
            holder.editTextCurrencyValue.setSelection(holder.editTextCurrencyValue.length())
            holder.imgFlag.setImageResource(currencyFlagId)

        }else{
            holder as MyRegularViewHolder
            setRegularViewHolder(holder,position,currencyCode,currencyName,currencyFlagId)
        }
    }

    private fun setRegularViewHolder( holder : MyRegularViewHolder , position: Int ,
                                      currencyCode : String , currencyName : String, currencyFlagId : Int){

        val format = NumberFormat.getInstance(Locale.UK)
        format.minimumFractionDigits = 2
        format.maximumFractionDigits = 2
        val currencyValue = format.format(mValues[position].second)
        holder.txtCurrencyCode.text = currencyCode
        holder.txtCurrencyName.text = currencyName
        holder.txtCurrencyValue.text = currencyValue
        holder.imgFlag.setImageResource(currencyFlagId)

        holder.currencyItem.setOnClickListener {
            onUpdateAmount(mValues[position].first, mValues[0].second)
            val main = mValues[position]
            mValues.remove(main)
            mValues.add(0, main)
            notifyItemMoved(position, 0)
            smoothScroll()
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun getItemViewType(position: Int): Int {
        if(position == 0) return TYPE_MAIN
        return TYPE_REGULAR
    }

    inner class MyRegularViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val imgFlag : ImageView  = mView.findViewById(R.id.imgFlag)
        val txtCurrencyCode: TextView = mView.findViewById(R.id.txtCurrencyCode)
        val txtCurrencyName: TextView = mView.findViewById(R.id.txtCurrencyName)
        val txtCurrencyValue: TextView = mView.findViewById(R.id.txtCurrencyValue)
        val currencyItem : ConstraintLayout = mView.findViewById(R.id.currencyItem)
    }

    inner class MyMainViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val imgFlag : ImageView  = mView.findViewById(R.id.imgFlag)
        val txtCurrencyCode: TextView = mView.findViewById(R.id.txtCurrencyCode)
        val txtCurrencyName: TextView = mView.findViewById(R.id.txtCurrencyName)
        val editTextCurrencyValue: EditText = mView.findViewById(R.id.editTextCurrencyValue)
        val currencyItem : ConstraintLayout = mView.findViewById(R.id.currencyItem)
    }
}
