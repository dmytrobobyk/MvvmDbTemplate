package dmytro.com.mvvmdbtemplate.screens.movies.adapter

import android.content.Context
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import dmytro.com.database.entity.DbMovie
import dmytro.com.mvvmdbtemplate.BuildConfig
import dmytro.com.mvvmdbtemplate.R
import dmytro.com.mvvmdbtemplate.common.adapter.BaseRecyclerAdapter
import dmytro.com.mvvmdbtemplate.screens.movies.rest.response.MovieResponse
import kotlinx.android.synthetic.main.item_movie.view.*


class MoviesAdapter : BaseRecyclerAdapter<DbMovie, MoviesAdapter.MovieItemViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        context = parent.context
        return MovieItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movieItem = getItem(position)

        loadImage(movieItem.posterPath, holder.itemImageView)
    }

    class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImageView = itemView.itemImageView
    }

    private fun loadImage(@NonNull posterPath: String, @NonNull posterImageView: ImageView) {
        Glide.with(context!!).load(BuildConfig.IMAGES_BASE_URL + "w185" + posterPath).into(posterImageView)
    }
}