package com.estarta.itemslist.data.mapper

import com.estarta.core.models.PagingModel
import com.estarta.itemslist.domain.model.ImageModel
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.network.models.GetItemsResponse
import com.estarta.network.models.ItemResponse
import javax.inject.Inject

class ItemModelDataMapperImpl @Inject constructor(
) : ItemModelDataMapper {

    override fun from(response: GetItemsResponse): PagingModel<ItemModel> {
        return PagingModel(mapList(response.items), response.pagination.key)
    }

    private fun mapList(itemResponse: List<ItemResponse>): List<ItemModel> {
        return itemResponse.map { mapItem(it) }
    }

    private fun mapItem(item: ItemResponse): ItemModel {
       return ItemModel(
           createdAt = item.createdAt,
           price = item.price,
           name = item.name,
           uid = item.uid,
           images = createImageModelList(item),
       )
    }

    private fun createImageModelList(item: ItemResponse): List<ImageModel> {
        return item.imagesIds.mapIndexed { index: Int, imageId: String ->
            val url = if (item.imagesUrls.size > index) item.imagesUrls[index] else ""
            val thumbUrl = if (item.imagesThumbUrls.size > index) item.imagesThumbUrls[index] else ""
            ImageModel(imageId, url, thumbUrl)
        }
    }


}