package com.example.movies_v8_1.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PremieresDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(objects: List<PremiereEntity>)

    @Query("select * from Premieres")
    fun getMoviesFromDB(): List<PremiereEntity>
}