package com.gabo.quiz10.data.handleResponse

sealed class Resource<T : Any> {
    data class Success<T : Any>(val data: T?) : Resource<T>()
    data class Error<T : Any>(val errorMsg: String?) : Resource<T>()
}