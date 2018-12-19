package com.avelozo.currencyconverter.view

import android.os.Bundle
import android.support.v4.app.Fragment
import com.avelozo.currencyconverter.contract.BaseView
import com.github.salomonbrys.kodein.KodeinInjected
import com.github.salomonbrys.kodein.KodeinInjector
import com.github.salomonbrys.kodein.android.appKodein


open class FragmentAbstract : Fragment(), KodeinInjected, BaseView {

    override val injector = KodeinInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(appKodein())
        retainInstance = true
    }

}