package com.estarta.network.handler

import com.estarta.network.models.ErrorEntity
import retrofit2.HttpException
import java.net.UnknownHostException

object ResponseErrorHandler {

    fun handleResponseError(exception: Throwable): ErrorEntity {
        if (exception is UnknownHostException) {
            return ErrorEntity.NetworkError
        } else if (exception is HttpException) {
            return ErrorEntity.ServerError(exception.code(), exception.response()?.errorBody()?.string())
        }

        return ErrorEntity.GeneralError
    }
}