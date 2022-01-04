package com.example.movies_v8_1.domain.use_case.get_movies

import com.example.movies_v8_1.domain.model.MovieModel
import com.example.movies_v8_1.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesFromCache@Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun eee(): List<MovieModel>{
        return repository.getMoviesFromCache()
    }
}