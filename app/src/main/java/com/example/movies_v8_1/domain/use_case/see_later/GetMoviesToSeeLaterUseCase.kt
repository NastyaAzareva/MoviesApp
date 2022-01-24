package com.example.movies_v8_1.domain.use_case.see_later

import com.example.movies_v8_1.common.Resource
import com.example.movies_v8_1.domain.model.MovieModel
import com.example.movies_v8_1.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesToSeeLaterUseCase @Inject constructor(
    private val repository: MovieRepository
)  {
    operator fun invoke(): Flow<Resource<List<MovieModel>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getSeeLaterMovies()
            emit(Resource.Success(movies))
        } catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred - " + e.message))
        }
    }
}