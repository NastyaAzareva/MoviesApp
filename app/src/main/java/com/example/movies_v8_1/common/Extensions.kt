package com.example.movies_v8_1.common

import com.example.movies_v8_1.data.db.FavouriteMoviesEntity
import com.example.movies_v8_1.data.db.PremiereEntity
import com.example.movies_v8_1.data.remote.dto.movie.MovieDTO
import com.example.movies_v8_1.data.remote.dto.moviepremieres.MoviePremiereItem
import com.example.movies_v8_1.domain.model.MovieDetailsModel
import com.example.movies_v8_1.domain.model.MovieModel

fun MovieDTO.toMovieDetailsModel(): MovieDetailsModel {
    return MovieDetailsModel(
        kinopoiskID = kinopoiskID,
        nameRu = nameRu,
        posterURL = posterURL,
        description = description
    )
}

fun MoviePremiereItem.toMovieModel(): MovieModel {
    return MovieModel(
        kinopoiskID = kinopoiskID,
        nameRu = nameRu,
        posterURLPreview = posterURLPreview
    )
}

fun MovieModel.toFavouriteMoviesEntity(): FavouriteMoviesEntity{
    return FavouriteMoviesEntity(
        kinopoiskID = kinopoiskID,
        nameRu = nameRu,
        posterURLPreview = posterURLPreview
    )
}

fun MovieModel.toPremiereEntity(): PremiereEntity {
    return PremiereEntity(
        kinopoiskID = kinopoiskID,
        nameRu = nameRu,
        posterURLPreview = posterURLPreview
    )
}
