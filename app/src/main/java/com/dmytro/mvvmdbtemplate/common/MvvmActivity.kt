package com.dmytro.mvvmdbtemplate.common

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.dmytro.mvvmdbtemplate.R
import com.dmytro.mvvmdbtemplate.common.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.toolbar.*

abstract class MvvmActivity : AppCompatActivity() {

    val toolbar: Toolbar by lazy {
        findViewById<Toolbar>(R.id.toolbar)
    }

    fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    fun setToolbarTitle(title: String) {
        toolbarTitleTextView.text = title
    }

    inline fun <reified T : ViewModel> ViewModelFactory<T>.get(): T =
            ViewModelProviders.of(this@MvvmActivity, this)[T::class.java]

}