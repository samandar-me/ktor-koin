package com.sdk.koinktortest.network

sealed class Response<out T> {
    data class Error(val message: String) : Response<Nothing>()
    data class Success<out T>(val data: T) : Response<T>()
}