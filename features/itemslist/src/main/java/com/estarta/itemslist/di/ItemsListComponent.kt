package com.estarta.itemslist.di

import com.estarta.core.di.components.CoreComponent
import com.estarta.core.di.scopes.ModuleScope
import com.estarta.core.di.viewmodel.ViewModelFactoryModule
import com.estarta.itemslist.di.modules.ItemsListModule
import com.estarta.itemslist.presentation.view.ItemsListFragment
import dagger.Component

@ModuleScope
@Component(dependencies = [CoreComponent::class],
    modules = [ViewModelFactoryModule::class,
        ItemsListModule::class
    ])
internal interface ItemsListComponent {

    fun inject(itemsListFragment: ItemsListFragment)

    @Component.Builder
    interface Builder {

        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): ItemsListComponent
    }

}