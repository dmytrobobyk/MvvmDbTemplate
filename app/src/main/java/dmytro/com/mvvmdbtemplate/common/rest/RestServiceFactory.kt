package dmytro.com.mvvmdbtemplate.common.rest

import dmytro.com.mvvmdbtemplate.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun <T> createService(clazz: Class<T>) = Retrofit.Builder()
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addCallAdapterFactory(CallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.API_ENDPOINT)
        .client(OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor())
                .build())
        .build()
        .create(clazz)

private fun loggingInterceptor() = HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)