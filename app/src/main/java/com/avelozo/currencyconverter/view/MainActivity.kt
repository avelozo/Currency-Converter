package com.avelozo.currencyconverter.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.avelozo.currencyconverter.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, CurrencyConverterFragment())
            .commit()
    }
}
