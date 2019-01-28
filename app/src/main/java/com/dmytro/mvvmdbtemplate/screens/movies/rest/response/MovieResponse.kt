package com.dmytro.mvvmdbtemplate.screens.movies.rest.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(@SerializedName("id") val id: Long,
                         @SerializedName("poster_path") val posterPath: String,
                         @SerializedName("overview") val overview: String,
                         @SerializedName("original_title") val originalTitle: String,
                         @SerializedName("release_date") val releaseDate: String,
                         @SerializedName("vote_average") val voteAverage: Double)