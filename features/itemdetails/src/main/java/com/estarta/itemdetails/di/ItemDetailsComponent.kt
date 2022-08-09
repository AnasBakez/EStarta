package com.estarta.itemdetails.di

import com.estarta.core.di.components.CoreComponent
import com.estarta.core.di.scopes.ModuleScope
import com.estarta.core.di.viewmodel.ViewModelFactoryModule
import com.estarta.itemdetails.di.modules.ItemDetailsModule
import com.estarta.itemdetails.presentation.view.ItemDetailsFragment
import dagger.Component

@ModuleScope
@Component(dependencies = [CoreComponent::class],
    modules = [ViewModelFactoryModule::class,
        ItemDetailsModule::class
    ])
internal interface ItemDetailsComponent {

    fun inject(itemDetailsFragment: ItemDetailsFragment)

    @Component.Builder
    interface Builder {

        fun coreComponent(coreComponent: CoreComponent): Builder

        fun build(): ItemDetailsComponent
    }

}