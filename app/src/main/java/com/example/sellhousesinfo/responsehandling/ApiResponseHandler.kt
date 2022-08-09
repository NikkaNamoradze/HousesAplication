package com.example.sellhousesinfo.responsehandling

sealed class ApiResponseHandler<T>(val data: T? = null, val errorMessage: String? = null) {

    class Success<T>(data: T? = null) : ApiResponseHandler<T>(data)
    class Failure<T>(errorMessage: String,data: T? = null) : ApiResponseHandler<T>(data, errorMessage)
    class Loading<T> : ApiResponseHandler<T>()

}
