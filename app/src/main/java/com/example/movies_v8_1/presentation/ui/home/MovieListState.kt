package com.example.movies_v8_1.presentation.ui.home

import com.example.movies_v8_1.domain.model.MovieModel

sealed class MovieListState() {
    class Success(val data: List<MovieModel>) : MovieListState()
    class Error(val message: String) : MovieListState()
    class Loading() : MovieListState()
}