package com.reeta.triveouscryptocurrencyassignment.apiResponse

import retrofit2.HttpException
import java.net.SocketTimeoutException

/* This is like a gneric class that will handle different types of result that our api will
give.
*/
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}


enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(
                getErrorMessage(e.code()),
                null
            )
            is SocketTimeoutException -> Resource.error(
                getErrorMessage(ErrorCodes.SocketTimeOut.code),
                null
            )
            else -> Resource.error(
                getErrorMessage(Int.MAX_VALUE),
                null
            )
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            409 -> "Conflicts found"
            500 -> "Server Error"
            else -> "Something went wrong"
        }
    }
}