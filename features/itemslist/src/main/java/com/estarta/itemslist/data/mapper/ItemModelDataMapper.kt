package com.estarta.itemslist.data.mapper

import com.estarta.core.models.PagingModel
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.network.models.GetItemsResponse

interface ItemModelDataMapper {

    fun from(response: GetItemsResponse): PagingModel<ItemModel>

}