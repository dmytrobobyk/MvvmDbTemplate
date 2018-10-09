package dmytro.com.mvvmdbtemplate.screens.movies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import dmytro.com.database.entity.DbMovie
import dmytro.com.mvvmdbtemplate.common.rx.RxWorkers
import dmytro.com.mvvmdbtemplate.common.util.Resource
import dmytro.com.mvvmdbtemplate.common.util.composeWith
import dmytro.com.mvvmdbtemplate.common.viewmodel.BaseViewModel
import dmytro.com.mvvmdbtemplate.screens.movies.repository.MoviesRepository
import dmytro.com.mvvmdbtemplate.screens.movies.rest.response.MovieResponse
import timber.log.Timber
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val repository: MoviesRepository,
                                          private val workers: RxWorkers) : BaseViewModel() {

    private val liveDataMovies: MutableLiveData<Resource<List<DbMovie>>> by lazy {
        MutableLiveData<Resource<List<DbMovie>>>()
    }

    fun moviesList(): LiveData<Resource<List<DbMovie>>> {
        return liveDataMovies.also {
            if (it.value?.data == null) {
                getMovies()
            }
        }
    }

    fun getMovies() {
        addSubscription(repository.getMovies()
                .composeWith(workers)
                .doOnSubscribe {
                    liveDataMovies.value = Resource.loading()
                }
                .subscribe({
                    liveDataMovies.value = Resource.success(it)
                }, {
                    Timber.e(it)
                    liveDataMovies.value = Resource.error(it, liveDataMovies.value?.data)
                }))
    }

    fun clearLiveData() {
        liveDataMovies.value = Resource.restore()
    }
}