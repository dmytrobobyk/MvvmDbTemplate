package com.dmytro.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dmytro.database.dao.MovieDao
import com.dmytro.database.entity.DbMovie

@Database(entities = [DbMovie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}