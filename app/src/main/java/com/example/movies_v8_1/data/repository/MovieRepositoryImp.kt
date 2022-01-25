package com.example.movies_v8_1.data.repository

import com.example.movies_v8_1.common.Constants
import com.example.movies_v8_1.data.db.AppDatabase
import com.example.movies_v8_1.data.db.FavouriteMoviesEntity
import com.example.movies_v8_1.data.db.PremiereEntity
import com.example.movies_v8_1.data.db.SeeLaterEntity
import com.example.movies_v8_1.data.remote.Api
import com.example.movies_v8_1.presentation.ui.home.PremieresPeriod
import com.example.movies_v8_1.data.remote.dto.movie.MovieDTO
import com.example.movies_v8_1.data.remote.dto.moviepremieres.MoviePremieresDTO
import com.example.movies_v8_1.domain.model.MovieModel
import com.example.movies_v8_1.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val api: Api,
    private val db: AppDatabase
) : MovieRepository {

    override suspend fun getMovies(): MoviePremieresDTO {
        return api.getMoviePremieres(
            Constants.YEAR,
            Constants.MONTH
        )
    }

    override suspend fun getMoviesByPeriod(period: PremieresPeriod): MoviePremieresDTO {
        return api.getMoviePremieres(
            period.year,
            period.month
        )
    }

    override suspend fun getMovieById(id: Long): MovieDTO {
        return api.getMovieById(id)
    }

    override suspend fun getMoviesFromCache(): List<MovieModel> {
        return db.getPremieresDAO().getMoviesFromDB().map {
            MovieModel(
                it.kinopoiskID,
                it.nameRu,
                it.posterURLPreview
            )
        }
    }

    override suspend fun getFavouritesMovies(): List<MovieModel> {
        return db.getFavouritesDao().getFavourites().map {
            MovieModel(
                it.kinopoiskID,
                it.nameRu,
                it.posterURLPreview
            )
        }
    }

    override suspend fun addToFavourites(movie: FavouriteMoviesEntity) {
        db.getFavouritesDao().addToFavourites(movie)
    }

    override suspend fun getSeeLaterMovies(): List<MovieModel> {
    //TODO дата
        return db.getSeeLaterDao().getSeeLaterList().map {
            MovieModel(
                it.kinopoiskID,
                it.nameRu,
                it.posterURLPreview
            )
        }
    }

    override suspend fun addToSeeLater(movie: SeeLaterEntity) {
        db.getSeeLaterDao().addMovie(movie)
    }

    override suspend fun writeMoviesToPremieresTable(list: List<PremiereEntity>) {
        db.getPremieresDAO().addAll(list)
    }
}