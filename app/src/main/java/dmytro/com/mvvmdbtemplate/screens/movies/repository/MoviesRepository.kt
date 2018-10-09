package dmytro.com.mvvmdbtemplate.screens.movies.repository

import dmytro.com.database.entity.DbMovie
import io.reactivex.Single

interface MoviesRepository {
    fun getMovies(): Single<List<DbMovie>>
}