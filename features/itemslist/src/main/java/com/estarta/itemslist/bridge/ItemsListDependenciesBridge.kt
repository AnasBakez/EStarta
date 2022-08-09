package com.estarta.itemslist.bridge

import androidx.navigation.NavController
import com.estarta.itemslist.domain.model.ItemModel

interface ItemsListDependenciesBridge {

    fun openItemDetails(navController: NavController, item: ItemModel)

}