package com.estarta.itemslist.presentation.view.adapter

import android.view.ViewGroup
import com.estarta.core.base.BaseViewHolder
import com.estarta.core.utils.AppImageLoader
import com.estarta.itemslist.R
import com.estarta.itemslist.databinding.ItemRowBinding
import com.estarta.itemslist.domain.model.ImageModel
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.itemslist.presentation.view.ItemsListActionInterface

internal class ItemViewHolder constructor(
    parent: ViewGroup,
    private val actionInterface: ItemsListActionInterface,
) : BaseViewHolder<ItemRowBinding>(parent, R.layout.item_row) {

    fun bindData(item: ItemModel?) {
        item?.let {
            performDataBinding(item)
            loadImage(item.images[0])
        }
    }

    private fun performDataBinding(item: ItemModel) {
        binding.item = item
        binding.actionInterface = actionInterface
        binding.executePendingBindings()
    }


    private fun loadImage(image: ImageModel) {
        AppImageLoader.loadImage(binding.itemListImage, image.thumbnailUrl, null)
    }

}