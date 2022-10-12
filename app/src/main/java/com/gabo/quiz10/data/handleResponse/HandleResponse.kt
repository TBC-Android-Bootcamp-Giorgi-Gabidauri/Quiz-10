package com.gabo.quiz10.data.handleResponse

import retrofit2.Response

interface HandleResponse {
    suspend fun <T : Any> handleResponse(
        request: suspend () -> Response<T>
    ): Resource<T> {
        return try {
            val result = request.invoke()
            val body = result.body()
            if (result.isSuccessful && body != null) {
                return Resource.Success(body)
            } else {
                Resource.Error(result.message() ?: "Unexpected error occurred!")
            }
        } catch (e: Throwable) {
            Resource.Error("Something went wrong!")
        }
    }
}