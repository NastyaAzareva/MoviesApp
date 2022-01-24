package com.example.movies_v8_1.domain.use_case.see_later

import com.example.movies_v8_1.data.db.SeeLaterEntity
import com.example.movies_v8_1.domain.repository.MovieRepository
import javax.inject.Inject

class AddMovieToLaterListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movie: SeeLaterEntity) {
        repository.addToSeeLater(movie)
    }
}