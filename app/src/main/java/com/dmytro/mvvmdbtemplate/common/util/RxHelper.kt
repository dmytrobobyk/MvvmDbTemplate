package com.dmytro.mvvmdbtemplate.common.util

import com.dmytro.mvvmdbtemplate.common.rx.RxWorkers
import io.reactivex.Completable
import io.reactivex.Single

fun <T> Single<T>.composeWith(workers: RxWorkers) = this.compose {
    it.subscribeOn(workers.subscribeWorker).observeOn(workers.observeWorker)
}

fun Completable.composeWith(workers: RxWorkers) = this.compose {
    it.subscribeOn(workers.subscribeWorker).observeOn(workers.observeWorker)
}