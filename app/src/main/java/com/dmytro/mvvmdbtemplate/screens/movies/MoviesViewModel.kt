package com.dmytro.mvvmdbtemplate.screens.movies

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.dmytro.database.entity.DbMovie
import com.dmytro.mvvmdbtemplate.common.rx.RxWorkers
import com.dmytro.mvvmdbtemplate.common.util.Resource
import com.dmytro.mvvmdbtemplate.common.util.composeWith
import com.dmytro.mvvmdbtemplate.common.viewmodel.BaseViewModel
import com.dmytro.mvvmdbtemplate.screens.movies.repository.MoviesRepository
import timber.log.Timber
import javax.inject.Inject

class MoviesViewModel(private val repository: MoviesRepository,
                      private val workers: RxWorkers) : BaseViewModel() {

    private val liveDataMovies: MutableLiveData<Resource<List<DbMovie>>> by lazy {
        MutableLiveData<Resource<List<DbMovie>>>()
    }

    fun getMovies(): LiveData<Resource<List<DbMovie>>> {
        return liveDataMovies.also {
            if (it.value?.data == null) {
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
        }
    }

    fun clearLiveData() {
        liveDataMovies.value = Resource.restore()
    }
}