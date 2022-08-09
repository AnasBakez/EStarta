package com.estarta.itemdetails.di.modules

import androidx.lifecycle.ViewModel
import com.estarta.core.di.viewmodel.ViewModelKey
import com.estarta.itemdetails.presentation.viewmodel.ItemDetailsVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ItemDetailsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ItemDetailsVM::class)
    internal abstract fun bindMyViewModel(myViewModel: ItemDetailsVM): ViewModel

}