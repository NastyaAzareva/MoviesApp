package com.example.movies_v8_1.domain.use_case.get_movies

import com.example.movies_v8_1.common.Resource
import com.example.movies_v8_1.common.toMovieModel
import com.example.movies_v8_1.domain.model.MovieModel
import com.example.movies_v8_1.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<MovieModel>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getMovies().items.map { it.toMovieModel() }
            emit(Resource.Success(movies))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}