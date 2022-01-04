package com.example.movies_v8_1.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavouriteMoviesDAO {
    @Query("select * from Favourites")
    fun getFavourites(): List<FavouriteMoviesEntity>

    @Insert
    fun addToFavourites(item: FavouriteMoviesEntity)
}