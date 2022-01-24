package com.example.movies_v8_1.presentation.ui.later

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_v8_1.common.Resource
import com.example.movies_v8_1.data.db.SeeLaterEntity
import com.example.movies_v8_1.domain.use_case.see_later.AddMovieToLaterListUseCase
import com.example.movies_v8_1.domain.use_case.see_later.GetMoviesToSeeLaterUseCase
import com.example.movies_v8_1.presentation.ui.home.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaterViewModel @Inject constructor(
    private val getSeeLaterMoviesUseCase: GetMoviesToSeeLaterUseCase,
    private val addMovieToLaterListUseCase: AddMovieToLaterListUseCase
): ViewModel() {
    val state: MutableLiveData<MovieListState> = MutableLiveData()

    private val viewModelJob = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    init {
        getSeeLaterMovies()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun getSeeLaterMovies() {
        getSeeLaterMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state.postValue(MovieListState.Success(result.data ?: emptyList()))
                }
                is Resource.Error -> {
                    state.postValue(MovieListState.Error(result.message ?: "An unexpected error occurred"))
                }
                is Resource.Loading -> {
                    state.postValue(MovieListState.Loading())
                }
            }

        }.launchIn(ioScope)
    }

    fun addToSeeLaterList(movie: SeeLaterEntity){
        viewModelScope.launch(Dispatchers.IO){
            addMovieToLaterListUseCase.invoke(movie)
        }
    }
}