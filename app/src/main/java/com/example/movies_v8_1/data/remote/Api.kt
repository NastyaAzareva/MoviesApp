package com.example.movies_v8_1.data.remote

import com.example.movies_v8_1.common.Constants
import com.example.movies_v8_1.data.remote.dto.movie.MovieDTO
import com.example.movies_v8_1.data.remote.dto.moviepremieres.MoviePremieresDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/api/v2.2/films/premieres")
    @Headers("X-API-KEY: " + Constants.TOKEN)
    suspend fun getMoviePremieres(
        @Query("year") year: Int,
        @Query("month") month: String
    ): MoviePremieresDTO

    @GET("/api/v2.2/films/{id}")
    @Headers("X-API-KEY: " + Constants.TOKEN)
    suspend fun getMovieById(
        @Path("id") id: Long
    ): MovieDTO
}