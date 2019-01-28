package com.dmytro.mvvmdbtemplate

import android.support.multidex.MultiDexApplication
import com.dmytro.mvvmdbtemplate.di.AppModule
import com.dmytro.mvvmdbtemplate.di.DaggerAppComponent
import timber.log.Timber

class App : MultiDexApplication() {

    val component by lazy {
        DaggerAppComponent.builder()
                .context(this)
                .plus(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)

        Timber.plant(Timber.DebugTree())
    }
}