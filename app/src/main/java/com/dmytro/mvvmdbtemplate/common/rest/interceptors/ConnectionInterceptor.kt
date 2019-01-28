package com.dmytro.mvvmdbtemplate.common.rest.interceptors

import android.content.Context
import com.dmytro.mvvmdbtemplate.common.rest.exceptions.NoInternetException
import com.dmytro.mvvmdbtemplate.common.util.NetworkUtil.isNetworkAvailable
import okhttp3.Interceptor
import okhttp3.Response

class ConnectionInterceptor(val context: Context) : Interceptor {

    @Throws(NoInternetException::class)
    override fun intercept(chain: Interceptor.Chain?): Response? {
        return if(!isNetworkAvailable(context)){
            throw NoInternetException()
        } else {
            chain?.proceed(chain.request().newBuilder().build())
        }
    }
}