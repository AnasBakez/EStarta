package com.estarta.estarta_task.bridges

import android.os.Bundle
import com.estarta.itemdetails.bridge.ItemsDetailsDependenciesBridge
import com.estarta.itemdetails.domain.model.ItemDetailsModel
import com.estarta.itemdetails.presentation.view.ItemDetailsFragmentArgs
import java.util.Date

object ItemDetailsDependenciesBridgeImpl : ItemsDetailsDependenciesBridge {

    override fun parseNavArgs(bundle: Bundle?): ItemDetailsModel? {
        if (bundle == null) return null

        val args = ItemDetailsFragmentArgs.fromBundle(bundle)
        return ItemDetailsModel(
            createdAt = Date(args.createdAt),
            price = args.price,
            name = args.name,
            uid = args.uid,
            images = args.images.toList()
        )
    }

}