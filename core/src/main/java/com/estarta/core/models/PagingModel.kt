package com.estarta.core.models

data class PagingModel<T>(
    val list: List<T>,
    val nextPage: String? = null,
)
