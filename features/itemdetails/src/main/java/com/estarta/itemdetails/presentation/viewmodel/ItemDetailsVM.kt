package com.estarta.itemdetails.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.estarta.core.base.BaseViewModel
import com.estarta.itemdetails.domain.model.ItemDetailsModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

internal class ItemDetailsVM @Inject constructor(
    application: Application,
) : BaseViewModel(application) {

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _itemDetails = MutableLiveData<ItemDetailsModel?>()
    val itemDetails: LiveData<ItemDetailsModel?>
        get() = _itemDetails


    internal fun setItemDetailsModel(itemDetailsModel: ItemDetailsModel?) {
        this._itemDetails.value = itemDetailsModel
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}