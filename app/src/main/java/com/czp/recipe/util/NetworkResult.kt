package com.czp.recipe.util

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(): NetworkResult<T>()
    class Error<T>(errorMessage: String): NetworkResult<T>(message = errorMessage)
    class Success<T>(data: T): NetworkResult<T>(data)
}