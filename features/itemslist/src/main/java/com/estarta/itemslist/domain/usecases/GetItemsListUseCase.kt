package com.estarta.itemslist.domain.usecases

import com.estarta.core.models.PagingModel
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.itemslist.domain.repository.ItemsListRepository
import io.reactivex.Single
import javax.inject.Inject

internal class GetItemsListUseCase @Inject constructor(
    private val repository: ItemsListRepository,
) {

    internal fun execute(params: Params): Single<PagingModel<ItemModel>> {
        return repository.getItems(params)
    }

    class Params constructor()
}