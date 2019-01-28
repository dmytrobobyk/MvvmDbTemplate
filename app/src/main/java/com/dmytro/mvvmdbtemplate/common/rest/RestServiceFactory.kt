package com.dmytro.mvvmdbtemplate.common.rest

import android.content.Context
import com.dmytro.mvvmdbtemplate.BuildConfig
import com.dmytro.mvvmdbtemplate.common.rest.interceptors.ConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T> createService(clazz: Class<T>, context: Context) = Retrofit.Builder()
        .addCallAdapterFactory(CallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_ENDPOINT)
        .client(OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor())
                .addInterceptor(ConnectionInterceptor(context))
                .build())
        .build()
        .create(clazz)

private fun loggingInterceptor() = HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)