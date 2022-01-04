package com.example.movies_v8_1.data.remote.dto.moviepremieres

// для https://kinopoiskapiunofficial.tech/documentation/api/#/films/get_api_v2_2_films_premieres
data class MoviePremieresDTO(
    val total: Long,
    val items: List<MoviePremiereItem>
)

