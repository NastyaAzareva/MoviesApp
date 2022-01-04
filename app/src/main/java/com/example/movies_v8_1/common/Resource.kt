package com.example.movies_v8_1.common

sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)
    class Loading<T>(data: T? = null) : Resource<T>(data, null)
}
