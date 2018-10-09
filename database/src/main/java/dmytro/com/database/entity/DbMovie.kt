package dmytro.com.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "movies")
data class DbMovie(
        @PrimaryKey(autoGenerate = true) val movieId: Long,
        val posterPath: String,
        val overview: String,
        val originalTitle: String,
        val releaseDate: String,
        val voteAverage: Double
)
