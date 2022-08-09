package com.estarta.itemslist.data.datasources

import com.estarta.core.models.PagingModel
import com.estarta.itemslist.data.mapper.ItemModelDataMapper
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.itemslist.domain.repository.ItemsListDataSource
import com.estarta.network.api.ApiService
import io.reactivex.Single
import javax.inject.Inject

internal class ItemsListDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: ItemModelDataMapper,
) : ItemsListDataSource {

    override fun getItems(): Single<PagingModel<ItemModel>> {
        return apiService.getItemsList()
            .map {
                mapper.from(it)
            }
    }
}