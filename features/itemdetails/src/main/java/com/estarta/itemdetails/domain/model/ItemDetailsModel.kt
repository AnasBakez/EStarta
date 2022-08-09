package com.estarta.itemdetails.domain.model

import com.estarta.core.models.ImageModel
import java.util.Date

data class ItemDetailsModel(
    val createdAt: Date,
    val price: String,
    val name: String,
    val uid: String,
    val images: List<ImageModel>,
)