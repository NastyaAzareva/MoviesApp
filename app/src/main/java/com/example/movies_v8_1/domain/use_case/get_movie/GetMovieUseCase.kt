package com.example.movies_v8_1.domain.use_case.get_movie

import com.example.movies_v8_1.common.Resource
import com.example.movies_v8_1.common.toMovieDetailsModel
import com.example.movies_v8_1.domain.model.MovieDetailsModel
import com.example.movies_v8_1.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Long): Flow<Resource<MovieDetailsModel>> = flow {
        try {
            emit(Resource.Loading())
            val movie = repository.getMovieById(movieId).toMovieDetailsModel()
            emit(Resource.Success(movie))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}