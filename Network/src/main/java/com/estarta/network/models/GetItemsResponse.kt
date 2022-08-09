package com.estarta.network.models

import com.google.gson.annotations.SerializedName

data class GetItemsResponse(
    @SerializedName("results")
    val items: List<ItemResponse>,
    @SerializedName("pagination")
    val pagination: PaginationResponse,
)

