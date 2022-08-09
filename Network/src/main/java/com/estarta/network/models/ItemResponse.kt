package com.estarta.network.models

import com.estarta.network.calladapter.DateTypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.util.Date

data class ItemResponse(
    @SerializedName("created_at")
    @JsonAdapter(DateTypeAdapter::class)
    val createdAt: Date,
    @SerializedName("price")
    val price: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("image_ids")
    val imagesIds: List<String>,
    @SerializedName("image_urls")
    val imagesUrls: List<String>,
    @SerializedName("image_urls_thumbnails")
    val imagesThumbUrls: List<String>,
)

