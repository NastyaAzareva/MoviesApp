package com.example.movies_v8_1.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SeeLaterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(objects: SeeLaterEntity)

    @Query("select * from SeeLater")
    fun getSeeLaterList(): List<SeeLaterEntity>
}