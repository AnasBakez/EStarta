package com.estarta.itemdetails.bridge

import android.content.Context

object ItemDetailsInjectorHelper {

    fun provideDependenciesBridge(applicationContext: Context): ItemsDetailsDependenciesBridge {
        return if (applicationContext is ItemsDetailsDependenciesBridgeProvider) {
            (applicationContext as ItemsDetailsDependenciesBridgeProvider).provideItemDetailsDependenciesBridge()
        } else {
            throw IllegalStateException(
                "The application context you have passed does not implement ItemsDetailsDependenciesBridge")
        }
    }
}

interface ItemsDetailsDependenciesBridgeProvider {
    fun provideItemDetailsDependenciesBridge(): ItemsDetailsDependenciesBridge
}