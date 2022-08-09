package com.estarta.estarta_task.bridges

import android.os.Bundle
import androidx.navigation.NavController
import com.estarta.estarta_task.R
import com.estarta.itemslist.bridge.ItemsListDependenciesBridge
import com.estarta.itemslist.domain.model.ItemModel

object ItemsListDependenciesBridgeImpl : ItemsListDependenciesBridge {

    override fun openItemDetails(navController: NavController, item: ItemModel) {
        val bundle = Bundle()
        bundle.putString("AAA", "Anas")
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }

}