package com.dmytro.mvvmdbtemplate.screens.movies.repository

import com.dmytro.database.dao.MovieDao
import com.dmytro.database.entity.DbMovie
import com.dmytro.mvvmdbtemplate.BuildConfig
import com.dmytro.mvvmdbtemplate.screens.movies.rest.MoviesApi
import com.dmytro.mvvmdbtemplate.screens.movies.rest.response.MovieResponse
import io.reactivex.Completable
import io.reactivex.Single

class MoviesLocalRepository(private val api: MoviesApi,
                            private val movieDao: MovieDao) : MoviesRepository {

    override fun getMovies(): Single<List<DbMovie>> {
        return api.getPopularMovies(BuildConfig.API_KEY)
                .map { it.movies.map { convertToDbMovie(it) } }
                .flatMapCompletable { Completable.fromCallable { movieDao.insertAll(it) } }
                .andThen(movieDao.getMovies())
    }

    private fun convertToDbMovie(it: MovieResponse): DbMovie {
        return DbMovie(it.id,
                it.posterPath,
                it.overview,
                it.originalTitle,
                it.releaseDate,
                it.voteAverage)
    }
}



