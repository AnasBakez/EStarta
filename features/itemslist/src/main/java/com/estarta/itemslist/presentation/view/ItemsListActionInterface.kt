package com.estarta.itemslist.presentation.view

import com.estarta.itemslist.domain.model.ItemModel

internal interface ItemsListActionInterface {
    fun onItemClicked(itemModel: ItemModel)
}