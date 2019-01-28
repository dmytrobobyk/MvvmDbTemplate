package com.dmytro.database.dao

import android.arch.persistence.room.*
import com.dmytro.database.entity.DbMovie
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies ORDER BY originalTitle")
    fun getMovies(): Single<List<DbMovie>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId ORDER BY originalTitle")
    fun getMovie(movieId: Int): Single<DbMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: DbMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<DbMovie>)

    @Update
    fun update(movie: DbMovie)

    @Delete
    fun delete(movie: DbMovie)
}