package com.estarta.itemslist.domain.repository

import com.estarta.core.models.PagingModel
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.itemslist.domain.usecases.GetItemsListUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class ItemsListRepositoryImpl @Inject constructor(
    private val dataSource: ItemsListDataSource,
) : ItemsListRepository {

    override fun getItems(params: GetItemsListUseCase.Params): Single<PagingModel<ItemModel>> {
        return dataSource.getItems()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}