package com.imadev.noteapp.other

import kotlinx.coroutines.flow.*
import retrofit2.Response


inline fun <T> networkBoundResource(
    crossinline fetch: suspend () -> Response<T>
) = flow {


    emit(Resource.Loading(null))
    try {
        val call = fetch()
        val code = call.code()
        when {
            call.isSuccessful -> emit(Resource.Success(call.body()))
            code == 401 -> emit(Resource.Unauthorized(null))
            else -> throw Throwable(call.message(), null)
        }

    } catch (throwable: Throwable) {
        emit(Resource.Error(throwable, null))
    }
}


inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            val call = fetch()
            saveFetchResult(call)
            call as Response<*>
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}
