package com.dmytro.mvvmdbtemplate.screens.movies.di

import android.content.Context
import com.dmytro.database.AppDatabase
import com.dmytro.database.dao.MovieDao
import com.dmytro.mvvmdbtemplate.common.di.ActivityScope
import com.dmytro.mvvmdbtemplate.common.rest.createService
import com.dmytro.mvvmdbtemplate.common.rx.RxWorkers
import com.dmytro.mvvmdbtemplate.screens.movies.MoviesViewModel
import com.dmytro.mvvmdbtemplate.screens.movies.repository.MoviesLocalRepository
import com.dmytro.mvvmdbtemplate.screens.movies.repository.MoviesRepository
import com.dmytro.mvvmdbtemplate.screens.movies.rest.MoviesApi
import dagger.Module
import dagger.Provides

@Module
class MoviesModule {

    @Provides
    @ActivityScope
    fun provideMovieDao(database: AppDatabase): MovieDao = database.movieDao()

    @Provides
    @ActivityScope
    fun api(context: Context) = createService(MoviesApi::class.java, context)

    @Provides
    @ActivityScope
    fun repository(api: MoviesApi, movieDao: MovieDao): MoviesRepository = MoviesLocalRepository(api, movieDao)

    @Provides
    @ActivityScope
    fun viewModel(repository: MoviesRepository, workers: RxWorkers) = MoviesViewModel(repository, workers)
}