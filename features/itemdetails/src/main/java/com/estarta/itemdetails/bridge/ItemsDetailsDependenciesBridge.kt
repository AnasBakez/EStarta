package com.estarta.itemdetails.bridge

import android.os.Bundle
import com.estarta.itemdetails.domain.model.ItemDetailsModel

interface ItemsDetailsDependenciesBridge {

    fun parseNavArgs(bundle: Bundle?): ItemDetailsModel?

}