package com.estarta.itemslist.domain.repository

import com.estarta.core.models.PagingModel
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.itemslist.domain.usecases.GetItemsListUseCase
import io.reactivex.Single

internal interface ItemsListRepository {

    fun getItems(params: GetItemsListUseCase.Params): Single<PagingModel<ItemModel>>

}