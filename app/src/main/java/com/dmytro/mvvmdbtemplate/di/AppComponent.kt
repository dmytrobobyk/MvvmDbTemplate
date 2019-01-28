package com.dmytro.mvvmdbtemplate.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import com.dmytro.database.AppDatabase
import com.dmytro.database.dao.MovieDao
import com.dmytro.mvvmdbtemplate.App
import com.dmytro.mvvmdbtemplate.common.rx.RxWorkers
import com.dmytro.mvvmtemplate.common.data.TokenStorage
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: App)

    fun app(): App

    fun workers(): RxWorkers

    fun context(): Context

    fun token(): TokenStorage

    fun appDatabase(): AppDatabase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(app: App): Builder

        fun plus(module: AppModule): Builder

        fun build(): AppComponent
    }
}