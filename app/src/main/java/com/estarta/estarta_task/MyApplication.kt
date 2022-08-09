package com.estarta.estarta_task

import android.app.Application
import com.estarta.core.di.components.CoreComponent
import com.estarta.core.di.components.DaggerCoreComponent
import com.estarta.core.di.helpers.CoreComponentProvider
import com.estarta.estarta_task.bridges.ItemsListDependenciesBridgeImpl
import com.estarta.itemslist.bridge.ItemsListDependenciesBridgeProvider

class MyApplication() : Application(), CoreComponentProvider, ItemsListDependenciesBridgeProvider {

    override fun provideCoreComponent(): CoreComponent {
        return DaggerCoreComponent.builder().application(this).build();
    }

    override fun provideItemsListDependenciesBridge() = ItemsListDependenciesBridgeImpl
}