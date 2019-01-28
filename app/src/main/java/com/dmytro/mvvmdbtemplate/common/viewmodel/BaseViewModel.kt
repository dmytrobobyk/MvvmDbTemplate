package com.dmytro.mvvmdbtemplate.common.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private var subscriptions = CompositeDisposable()

    protected fun addSubscription(disposable: Disposable) {
        subscriptions.add(disposable)
    }

    protected fun clearSubscriptions() = subscriptions.clear()

    override fun onCleared() {
        super.onCleared()
        clearSubscriptions()
    }
}