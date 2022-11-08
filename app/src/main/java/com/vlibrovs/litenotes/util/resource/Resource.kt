package com.vlibrovs.litenotes.util.resource

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Loading<T> : Resource<T>()
    class Error<T>(val data: T? = null, val error: Throwable? = null) : Resource<T>()
}
