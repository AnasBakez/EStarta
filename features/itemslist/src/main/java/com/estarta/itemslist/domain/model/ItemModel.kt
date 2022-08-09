package com.estarta.itemslist.domain.model

import java.util.Date

data class ItemModel(
    val createdAt: Date,
    val price: String,
    val name: String,
    val uid: String,
    val images: List<ImageModel>,
)