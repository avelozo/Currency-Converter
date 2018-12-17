package com.avelozo.currencyconverter.rx

import android.util.Log
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ComposeRx {

    fun <T> applySingleSchedulers(): SingleTransformer<T, T> {
        return SingleTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext { it: Throwable ->
                    if (it is RevolutException) {
                        Log.d("logError", it.logMessage)
                        Single.error(it)
                    } else {
                        Single.error(handleIfNetworkingError(it))
                    }
                }
        }
    }


    private fun handleIfNetworkingError(throwable: Throwable): Throwable {
        return when {
            isConnectionTimeout(throwable) -> NetworkError("Connection timeout.")
            noInternetAvailable(throwable) -> NetworkError("No internet available.")
            isCanceledIOException(throwable) -> NetworkError("Connection interrupted error.")
            else -> throwable
        }
    }

    private fun isCanceledIOException(error: Throwable): Boolean =
        error is IOException && error.message?.contentEquals("Canceled") ?: false

    private fun noInternetAvailable(error: Throwable): Boolean =
        error is UnknownHostException

    private fun isConnectionTimeout(throwable: Throwable): Boolean =
        throwable is SocketTimeoutException
}