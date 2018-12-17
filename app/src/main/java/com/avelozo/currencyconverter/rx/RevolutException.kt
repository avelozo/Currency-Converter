package com.avelozo.currencyconverter.rx

import java.lang.Exception

open class RevolutException(val logMessage: String? = null, userMessage: String) : Exception(userMessage)