package dmytro.com.mvvmdbtemplate.screens.movies.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dmytro.com.database.dao.MovieDao
import dmytro.com.mvvmdbtemplate.common.di.ActivityScope
import dmytro.com.mvvmdbtemplate.common.rest.createService
import dmytro.com.mvvmdbtemplate.common.rx.RxWorkers
import dmytro.com.mvvmdbtemplate.screens.movies.MoviesViewModel
import dmytro.com.mvvmdbtemplate.screens.movies.repository.MoviesLocalRepository
import dmytro.com.mvvmdbtemplate.screens.movies.repository.MoviesRepository
import dmytro.com.mvvmdbtemplate.screens.movies.rest.MoviesApi

@Module
class MoviesModule(private val context: Context) {

    @Provides
    @ActivityScope
    fun api() = createService(MoviesApi::class.java)

    @Provides
    @ActivityScope
    fun context(): Context = context

    @Provides
    @ActivityScope
    fun repository(context: Context, api: MoviesApi, movieDao: MovieDao): MoviesRepository = MoviesLocalRepository(context, api, movieDao)

    @Provides
    @ActivityScope
    fun viewModel(repository: MoviesRepository, workers: RxWorkers) = MoviesViewModel(repository, workers)
}