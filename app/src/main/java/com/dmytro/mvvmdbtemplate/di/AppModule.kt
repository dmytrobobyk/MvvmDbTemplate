package com.dmytro.mvvmdbtemplate.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import com.dmytro.database.AppDatabase
import com.dmytro.database.BuildConfig
import com.dmytro.database.dao.MovieDao
import com.dmytro.mvvmdbtemplate.common.rx.RxWorkers
import com.dmytro.mvvmtemplate.common.data.TokenLocalStorage
import com.dmytro.mvvmtemplate.common.data.TokenStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun workers() = RxWorkers(Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    @Singleton
    fun app(): Application = app

    @Provides
    @Singleton
    fun context(): Context = app

    @Provides
    @Singleton
    fun token(context: Context): TokenStorage = TokenLocalStorage(context)

    @Provides
    @Singleton
    fun provideMovieDatabase(): AppDatabase = Room.databaseBuilder(app, AppDatabase::class.java, BuildConfig.DB_NAME).build()

}