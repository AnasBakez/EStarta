package com.estarta.network.models

sealed class ErrorEntity {

    object GeneralError : ErrorEntity()
    object NetworkError : ErrorEntity()
    class ServerError(code: Int, rawBody: String?) : ErrorEntity()
}