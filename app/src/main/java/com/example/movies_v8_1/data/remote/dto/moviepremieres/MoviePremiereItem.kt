package com.example.movies_v8_1.data.remote.dto.moviepremieres

import com.example.movies_v8_1.data.remote.dto.movie.Country
import com.example.movies_v8_1.data.remote.dto.movie.Genre
import com.google.gson.annotations.SerializedName

data class MoviePremiereItem (
    @SerializedName("kinopoiskId")
    val kinopoiskID: Long,

    val nameRu: String,
    val nameEn: String,
    val year: Long,

    @SerializedName("posterUrl")
    val posterURL: String,

    @SerializedName("posterUrlPreview")
    val posterURLPreview: String,

    val countries: List<Country>,
    val genres: List<Genre>,
    val duration: Long,
    val premiereRu: String
)