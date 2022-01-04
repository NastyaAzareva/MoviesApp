package com.example.movies_v8_1.data.remote.dto.movie

import com.google.gson.annotations.SerializedName

// для https://kinopoiskapiunofficial.tech/documentation/api/#/films/get_api_v2_2_films__id_
data class MovieDTO(
    @SerializedName("kinopoiskId")
    val kinopoiskID: Long,

    @SerializedName("imdbId")
    val imdbID: String,

    val nameRu: String,
    val nameEn: String,
    val nameOriginal: String,

    @SerializedName("posterUrl")
    val posterURL: String,

    @SerializedName("posterUrlPreview")
    val posterURLPreview: String,

    val reviewsCount: Long,
    val ratingGoodReview: Double,
    val ratingGoodReviewVoteCount: Long,
    val ratingKinopoisk: Double,
    val ratingKinopoiskVoteCount: Long,
    val ratingImdb: Double,
    val ratingImdbVoteCount: Long,
    val ratingFilmCritics: Double,
    val ratingFilmCriticsVoteCount: Long,
    val ratingAwait: Double,
    val ratingAwaitCount: Long,

    @SerializedName("ratingRfCritics")
    val ratingRFCritics: Double,

    @SerializedName("ratingRfCriticsVoteCount")
    val ratingRFCriticsVoteCount: Long,

    @SerializedName("webUrl")
    val webURL: String,

    val year: Long,
    val filmLength: Long,
    val slogan: String,
    val description: String,
    val shortDescription: String,
    val editorAnnotation: String,
    val isTicketsAvailable: Boolean,
    val productionStatus: String,
    val type: String,

    @SerializedName("ratingMpaa")
    val ratingMPAA: String,

    val ratingAgeLimits: String,
    val hasImax: Boolean,
    val has3D: Boolean,
    val lastSync: String,
    val countries: List<Country>,
    val genres: List<Genre>,
    val startYear: Long,
    val endYear: Long,
    val serial: Boolean,
    val shortFilm: Boolean,
    val completed: Boolean
)

