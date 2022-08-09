package com.estarta.itemslist.di.modules

import androidx.lifecycle.ViewModel
import com.estarta.core.di.viewmodel.ViewModelKey
import com.estarta.itemslist.data.mapper.ItemModelDataMapper
import com.estarta.itemslist.data.mapper.ItemModelDataMapperImpl
import com.estarta.itemslist.domain.repository.ItemsListDataSource
import com.estarta.itemslist.domain.repository.ItemsListRepository
import com.estarta.itemslist.presentation.viewmodel.ItemsListVM
import com.estarta.itemslist.data.datasources.ItemsListDataSourceImpl
import com.estarta.itemslist.domain.repository.ItemsListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ItemsListModule {
    @Binds
    @IntoMap
    @ViewModelKey(ItemsListVM::class)
    internal abstract fun bindMyViewModel(myViewModel: ItemsListVM): ViewModel


    @Binds
    abstract fun bindRepository(itemsListRepositoryImpl: ItemsListRepositoryImpl): ItemsListRepository


    @Binds
    abstract fun bindDataMapper(dataMapperImpl: ItemModelDataMapperImpl) : ItemModelDataMapper


    @Binds
    abstract fun bindDataSource(dataSource: ItemsListDataSourceImpl): ItemsListDataSource

}