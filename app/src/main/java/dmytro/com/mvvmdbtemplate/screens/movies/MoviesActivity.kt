package dmytro.com.mvvmdbtemplate.screens.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.view.View
import dmytro.com.mvvmdbtemplate.App
import dmytro.com.mvvmdbtemplate.R
import dmytro.com.mvvmdbtemplate.common.MvvmActivity
import dmytro.com.mvvmdbtemplate.common.util.Resource
import dmytro.com.mvvmdbtemplate.common.viewmodel.ViewModelFactory
import dmytro.com.mvvmdbtemplate.screens.movies.adapter.MoviesAdapter
import dmytro.com.mvvmdbtemplate.screens.movies.di.DaggerMoviesComponent
import dmytro.com.mvvmdbtemplate.screens.movies.di.MoviesModule
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MoviesActivity : MvvmActivity() {

    private val component by lazy {
        DaggerMoviesComponent.builder()
                .appComponent((application as App).component)
                .activity(this)
                .plus(MoviesModule(this))
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

        viewModel.moviesList().observe(this, Observer {
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
                    Snackbar.make(window.decorView, "Error", Snackbar.LENGTH_SHORT).show()
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