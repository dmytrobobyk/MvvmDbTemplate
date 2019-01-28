package com.dmytro.mvvmdbtemplate.screens.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.dmytro.mvvmdbtemplate.App
import com.dmytro.mvvmdbtemplate.R
import com.dmytro.mvvmdbtemplate.common.MvvmActivity
import com.dmytro.mvvmdbtemplate.common.rest.exceptions.NetworkException
import com.dmytro.mvvmdbtemplate.common.util.Resource
import com.dmytro.mvvmdbtemplate.common.viewmodel.ViewModelFactory
import com.dmytro.mvvmdbtemplate.screens.movies.adapter.MoviesAdapter
import com.dmytro.mvvmdbtemplate.screens.movies.di.DaggerMoviesComponent
import com.dmytro.mvvmdbtemplate.screens.movies.di.MoviesModule
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MoviesActivity : MvvmActivity() {

    private val component by lazy {
        DaggerMoviesComponent.builder()
                .appComponent((application as App).component)
                .activity(this)
                .plus(MoviesModule())
                .build()
    }

    @Inject lateinit var viewModelFactory: ViewModelFactory<MoviesViewModel>
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[MoviesViewModel::class.java]

        initToolbar()
        setToolbarTitle(getString(R.string.app_name))
        initRecyclerView()
        initAdapter()

        viewModel.getMovies().observe(this, Observer {
            when(it?.status){
                Resource.Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    moviesRecyclerView.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    (moviesRecyclerView.adapter as MoviesAdapter).replaceAll(it.data!!)
                    progressBar.visibility = View.GONE
                    moviesRecyclerView.visibility = View.VISIBLE
                }
                Resource.Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    moviesRecyclerView.visibility = View.VISIBLE
                    if (it.throwable is NetworkException) {
                        Snackbar.make(window.decorView, it.throwable.description!!, Snackbar.LENGTH_SHORT).show()
                    } else {
                        Snackbar.make(window.decorView, it.throwable?.message.toString(), Snackbar.LENGTH_SHORT).show()
                    }
                    viewModel.clearLiveData()
                }
            }
        })
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(applicationContext, 2)
        moviesRecyclerView.layoutManager = gridLayoutManager
    }

    private fun initAdapter() {
        moviesRecyclerView.adapter = MoviesAdapter()
    }

    private fun initViews() {

    }


}