package dmytro.com.mvvmdbtemplate.screens.movies.repository

import android.content.Context
import dmytro.com.database.dao.MovieDao
import dmytro.com.database.entity.DbMovie
import dmytro.com.mvvmdbtemplate.BuildConfig
import dmytro.com.mvvmdbtemplate.screens.movies.rest.MoviesApi
import dmytro.com.mvvmdbtemplate.screens.movies.rest.response.MovieResponse
import dmytro.com.mvvmdbtemplate.utils.isInternetAvailable
import io.reactivex.Completable
import io.reactivex.Single

class MoviesLocalRepository(private val context: Context,
                            private val api: MoviesApi,
                            private val movieDao: MovieDao) : MoviesRepository {

    override fun getMovies(): Single<List<DbMovie>> {
        return if (isInternetAvailable(context)) {
            api.getPopularMovies(BuildConfig.API_KEY)
                    .map { it.movies.map { convertToDbMovie(it) } }
                    .flatMapCompletable { Completable.fromCallable { movieDao.insertAll(it) } }
                    .andThen(movieDao.getMovies())
        } else {
            movieDao.getMovies()
        }
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



