package com.example.movies_v8_1.presentation.ui.details

import com.example.movies_v8_1.domain.model.MovieDetailsModel

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetailsModel? = null,
    val error: String = ""
)