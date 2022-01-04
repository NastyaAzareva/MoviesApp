package com.example.movies_v8_1.presentation.ui.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_v8_1.common.Resource
import com.example.movies_v8_1.data.db.AppDatabase
import com.example.movies_v8_1.data.db.FavouriteMoviesEntity
import com.example.movies_v8_1.domain.use_case.favourites.AddFavouriteMovieUseCase
import com.example.movies_v8_1.domain.use_case.favourites.GetFavouritesMoviesUseCase
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
class FavouritesViewModel @Inject constructor(
    private val getFavouritesMoviesUseCase: GetFavouritesMoviesUseCase,
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase
)  : ViewModel() {
    val state: MutableLiveData<MovieListState> = MutableLiveData()

    private val viewModelJob = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    init {
        getFavouriteMovies()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun addMovieToFavourites(movie: FavouriteMoviesEntity){
        viewModelScope.launch(Dispatchers.IO) {
            addFavouriteMovieUseCase.invoke(movie)
        }
    }

    private fun getFavouriteMovies() {
        getFavouritesMoviesUseCase().onEach { result ->
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
}