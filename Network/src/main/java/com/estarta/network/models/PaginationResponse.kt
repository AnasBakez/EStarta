package com.estarta.network.models

import com.google.gson.annotations.SerializedName

data class PaginationResponse(
    @SerializedName("key")
    val key: String?,
)

