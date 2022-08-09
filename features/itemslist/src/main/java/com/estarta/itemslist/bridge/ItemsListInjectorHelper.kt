package com.estarta.itemslist.bridge

import android.content.Context

object ItemsListInjectorHelper {

    fun provideDependenciesBridge(applicationContext: Context): ItemsListDependenciesBridge {
        return if (applicationContext is ItemsListDependenciesBridgeProvider) {
            (applicationContext as ItemsListDependenciesBridgeProvider).provideItemsListDependenciesBridge()
        } else {
            throw IllegalStateException(
                "The application context you have passed does not implement ItemsListDependenciesBridgeProvider")
        }
    }
}

interface ItemsListDependenciesBridgeProvider {
    fun provideItemsListDependenciesBridge(): ItemsListDependenciesBridge
}