package dmytro.com.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import dmytro.com.database.dao.MovieDao
import dmytro.com.database.entity.DbMovie

@Database(entities = [DbMovie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}