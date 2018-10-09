package dmytro.com.mvvmdbtemplate.screens.movies.rest.response

import com.google.gson.annotations.SerializedName
import dmytro.com.mvvmdbtemplate.common.rest.reseponse.BaseResponse

data class MoviesResponse(@SerializedName("results") val movies: List<MovieResponse>) : BaseResponse() {
    override fun isResponseValid(): Boolean {
        return movies.isNotEmpty()
    }
}