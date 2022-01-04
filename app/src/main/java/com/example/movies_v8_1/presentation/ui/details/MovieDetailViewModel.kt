package com.example.movies_v8_1.presentation.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_v8_1.common.Constants
import com.example.movies_v8_1.common.Resource
import com.example.movies_v8_1.domain.use_case.get_movie.GetMovieUseCase
import com.example.movies_v8_1.presentation.ui.home.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {
    val state: MutableLiveData<MovieDetailState> = MutableLiveData()

    fun getMovies(movieId: Long){
        getMovieUseCase(movieId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    state.value = MovieDetailState(movie = result.data)
                }
                is Resource.Error -> {
                    state.value = MovieDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    state.value = MovieDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}