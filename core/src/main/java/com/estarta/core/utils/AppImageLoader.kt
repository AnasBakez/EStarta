package com.estarta.core.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.estarta.core.R

object AppImageLoader {

    fun loadImage(view: ImageView, imageUrl: String, thumbnailUrl: String? = null) {
        Glide.with(view)
            .load(imageUrl)
            .centerCrop()
            .thumbnail(generateThumbnailRequest(view, thumbnailUrl))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_placeholder)
            .into(view)
    }

    private fun generateThumbnailRequest(view: ImageView, thumbnailUrl: String?): RequestBuilder<Drawable> {
        return Glide.with(view)
            .load(thumbnailUrl)
    }
}