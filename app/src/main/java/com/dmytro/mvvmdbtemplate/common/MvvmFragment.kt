package com.dmytro.mvvmdbtemplate.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import com.dmytro.mvvmdbtemplate.common.viewmodel.ViewModelFactory

abstract class MvvmFragment : Fragment() {

    inline fun <reified T : ViewModel> ViewModelFactory<T>.get(): T =
            ViewModelProviders.of(this@MvvmFragment, this)[T::class.java]

}