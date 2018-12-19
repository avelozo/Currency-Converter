package com.avelozo.currencyconverter.app

import android.app.Application
import com.avelozo.currencyconverter.di.ApplicationModule
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy


class MainApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(ApplicationModule().module)

    }
}