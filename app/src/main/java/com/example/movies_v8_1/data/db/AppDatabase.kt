package com.example.movies_v8_1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(
        FavouriteMoviesEntity::class,
        PremiereEntity::class,
        SeeLaterEntity::class
    ),
    version = 4,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getFavouritesDao(): FavouriteMoviesDAO
    abstract fun getPremieresDAO(): PremieresDAO
    abstract fun getSeeLaterDao(): SeeLaterDAO

}


