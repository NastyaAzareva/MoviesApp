package com.example.movies_v8_1.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies_v8_1.common.Resource
import com.example.movies_v8_1.domain.model.MovieModel
import com.example.movies_v8_1.domain.use_case.get_movies.GetMoviesByPeriodUseCase
import com.example.movies_v8_1.domain.use_case.get_movies.GetMoviesFromCache
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMoviesByPeriodUseCase: GetMoviesByPeriodUseCase,
    private val getMoviesFromCache: GetMoviesFromCache
) : ViewModel() {
    var state: MutableLiveData<MovieListState> = MutableLiveData()
    var list: ArrayList<MovieModel> = arrayListOf()
    private var currentPage: Int
    private var isDataFromCachePresented: Boolean = true

    private val viewModelJob = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    init {
        getMovies()
        currentPage = 1
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun loadMoreMovies() {
        currentPage++
        getMovies()
    }

    fun getMovies() {
        getMoviesByPeriodUseCase(PremieresPeriod(currentPage))
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        if(isDataFromCachePresented) {
                            list.clear()
                            isDataFromCachePresented = false
                        }
                        list.addAll(result.data ?: emptyList())
                        state.postValue(MovieListState.Success(result.data ?: emptyList()))
                    }
                    is Resource.Error -> {
                        state.postValue(MovieListState.Error(result.message ?: "An unexpected error occurred"))
                    }
                    is Resource.Loading -> {
                        if(isDataFromCachePresented){
                            list.addAll(getMoviesFromCache.list())
                        }
                        state.postValue(MovieListState.Loading())
                    }
                }
            }.launchIn(ioScope)
    }

    fun getMovieByID(id: Long): MovieModel? {
        var movieModel: MovieModel? = null

        when (state.value) {
            is MovieListState.Success -> {
                movieModel = (state.value as MovieListState.Success).data.findLast {
                    it.kinopoiskID == id
                }
            }
        }
        return movieModel
    }
}