package com.example.movies_v8_1.domain.repository

import com.example.movies_v8_1.data.db.FavouriteMoviesEntity
import com.example.movies_v8_1.data.db.PremiereEntity
import com.example.movies_v8_1.data.db.SeeLaterEntity
import com.example.movies_v8_1.presentation.ui.home.PremieresPeriod
import com.example.movies_v8_1.data.remote.dto.movie.MovieDTO
import com.example.movies_v8_1.data.remote.dto.moviepremieres.MoviePremieresDTO
import com.example.movies_v8_1.domain.model.MovieModel

interface MovieRepository {

    suspend fun getMovies(): MoviePremieresDTO

    suspend fun getMoviesByPeriod(period: PremieresPeriod): MoviePremieresDTO

    suspend fun getMovieById(id: Long): MovieDTO

    suspend fun getMoviesFromCache(): List<MovieModel>

    suspend fun getFavouritesMovies(): List<MovieModel>

    suspend fun addToFavourites(movie: FavouriteMoviesEntity)

    suspend fun getSeeLaterMovies(): List<MovieModel>

    suspend fun addToSeeLater(movie: SeeLaterEntity)

    suspend fun writeMoviesToPremieresTable(list: List<PremiereEntity>)
}