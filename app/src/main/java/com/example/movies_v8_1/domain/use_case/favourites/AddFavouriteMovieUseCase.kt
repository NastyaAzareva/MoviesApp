package com.example.movies_v8_1.domain.use_case.favourites

import com.example.movies_v8_1.data.db.FavouriteMoviesEntity
import com.example.movies_v8_1.domain.repository.MovieRepository
import javax.inject.Inject

class AddFavouriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository
)  {
    suspend operator fun invoke(movie: FavouriteMoviesEntity) {
        repository.addToFavourites(movie)
    }
}