package com.estarta.core.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageModel(
    val id : String,
    val url : String,
    val thumbnailUrl : String
) : Parcelable
