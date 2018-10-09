package dmytro.com.mvvmdbtemplate.di

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import dmytro.com.database.AppDatabase
import dmytro.com.database.BuildConfig
import dmytro.com.database.dao.MovieDao
import dmytro.com.mvvmdbtemplate.App
import dmytro.com.mvvmdbtemplate.common.rx.RxWorkers
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
    fun provideMovieDatabase(): AppDatabase = Room.databaseBuilder(app, AppDatabase::class.java, BuildConfig.DB_NAME).build()

    @Provides
    @Singleton
    fun provideMovieDao(database: AppDatabase): MovieDao = database.movieDao()
}