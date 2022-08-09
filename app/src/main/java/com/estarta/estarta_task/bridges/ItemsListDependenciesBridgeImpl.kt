package com.estarta.estarta_task.bridges

import androidx.navigation.NavController
import com.estarta.itemslist.bridge.ItemsListDependenciesBridge
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.itemslist.presentation.view.ItemsListFragmentDirections

object ItemsListDependenciesBridgeImpl : ItemsListDependenciesBridge {

    override fun openItemDetails(navController: NavController, item: ItemModel) {
        val action = ItemsListFragmentDirections.openItemDetails(
            createdAt = item.createdAt.time,
            price = item.price,
            name = item.name,
            uid = item.uid,
            images = item.images.toTypedArray()
        )
        navController.navigate(action)
    }

}