package com.task.searchbar.data.resource

class Resource<T>(
    val status: Status, val data: T?, val error_msg: String?,
    val responseCode: Int?, val throwable: Throwable?
) {

    enum class Status {
        SUCCESS, DOMAIN_ERROR, API_ERROR, LOADING
    }

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null, null)
        }

        fun <T> apiError(error_msg: String, responseCode: Int): Resource<T> {
            return Resource(Status.API_ERROR, null, error_msg, responseCode, null)
        }

        fun <T> domainError(throwable: Throwable): Resource<T> {
            return Resource(Status.DOMAIN_ERROR, null, null, null, throwable)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null, null, null)
        }
    }
}
