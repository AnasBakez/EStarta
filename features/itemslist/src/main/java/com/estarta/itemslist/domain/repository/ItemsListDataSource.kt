package com.estarta.itemslist.domain.repository

import com.estarta.core.models.PagingModel
import com.estarta.itemslist.domain.model.ItemModel
import io.reactivex.Single

internal interface ItemsListDataSource {

    fun getItems(): Single<PagingModel<ItemModel>>
}