package com.estarta.itemslist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.estarta.core.base.BaseViewModel
import com.estarta.core.models.PagingModel
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.itemslist.domain.usecases.GetItemsListUseCase
import com.estarta.network.handler.ResponseErrorHandler
import com.estarta.network.models.ErrorEntity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class ItemsListVM @Inject constructor(
    application: Application,
    private var getItemsListUseCase: GetItemsListUseCase
) : BaseViewModel(application) {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private var nextPage : String? = null

    private val _viewStatus = MutableLiveData<ViewStatus>()
    val viewStatus: LiveData<ViewStatus>
        get() = _viewStatus

    private val _noContent = MutableLiveData<Boolean>()
    val noContent: LiveData<Boolean>
        get() = _noContent


    internal fun loadData() {
        getItems()
    }


    private fun getItems() {
        _viewStatus.value = ViewStatus.Loading
        disposables.add(getItemsListUseCase.execute(GetItemsListUseCase.Params())
            .subscribe(
                { items -> updateItemsList(items) },
                { err -> handleFailedGettingItemsList(err) }))
    }

    private fun updateItemsList(items: PagingModel<ItemModel>) {
        nextPage = items.nextPage
        _viewStatus.value = ViewStatus.Success(items.list)
        _noContent.value = items.list.isEmpty()

    }

    private fun handleFailedGettingItemsList(err: Throwable) {
        _viewStatus.value = ViewStatus.Failed(ResponseErrorHandler.handleResponseError(err))
        _noContent.value = true
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


    sealed class ViewStatus{
        object Loading : ViewStatus()
        class Success(val items : List<ItemModel>) : ViewStatus()
        class Failed(val error : ErrorEntity) : ViewStatus()
    }
}