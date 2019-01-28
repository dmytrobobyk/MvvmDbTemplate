package com.dmytro.mvvmdbtemplate.screens.movies.repository

import com.dmytro.database.entity.DbMovie
import io.reactivex.Single

interface MoviesRepository {
    fun getMovies(): Single<List<DbMovie>>
}