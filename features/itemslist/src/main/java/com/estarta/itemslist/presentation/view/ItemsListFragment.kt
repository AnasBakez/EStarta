package com.estarta.itemslist.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.estarta.core.base.BaseFragment
import com.estarta.core.di.helpers.CoreInjectHelper
import com.estarta.core.extensions.addToolbar
import com.estarta.core.extensions.removeProgressDialog
import com.estarta.core.extensions.showProgressDialog
import com.estarta.core.extensions.showToast
import com.estarta.itemslist.R
import com.estarta.itemslist.databinding.FragmentItemsListBinding
import com.estarta.itemslist.di.DaggerItemsListComponent
import com.estarta.itemslist.di.ItemsListComponent
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.itemslist.presentation.view.adapter.ItemListAdapter
import com.estarta.itemslist.presentation.viewmodel.ItemsListVM
import com.estarta.network.models.ErrorEntity

class ItemsListFragment() : BaseFragment<FragmentItemsListBinding>(),
    ItemsListActionInterface {

    private val viewModel: ItemsListVM by viewModelsLazy()
    private lateinit var itemsListComponent: ItemsListComponent
    private var adapter: ItemListAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_items_list
    }

    override fun bindData(viewDataBinding: FragmentItemsListBinding) {
        viewBinding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFragment()
    }

    private fun injectFragment() {
        createDaggerComponent()
        itemsListComponent.inject(this)
    }

    private fun createDaggerComponent() {
        itemsListComponent = DaggerItemsListComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireContext().applicationContext))
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        assignAdapterToRecycler()

    }

    private fun setupToolbar() {
        addToolbar(viewBinding.toolbar as Toolbar, getString(R.string.items_list_title), enableHomeAsUp = false)
    }

    private fun assignAdapterToRecycler() {
        adapter = ItemListAdapter(this)
        viewBinding.editAddCategoriesIconsRecycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
        listenItemsListChanges()
    }

    private fun listenItemsListChanges() {
        viewModel.viewStatus.observe(viewLifecycleOwner) { status ->
            when (status){
                ItemsListVM.ViewStatus.Loading -> {
                    showProgressDialog(msg = getString(R.string.items_list_loading_items), isRemovable = false)
                }
                is ItemsListVM.ViewStatus.Failed -> {
                    removeProgressDialog()
                    handleFailed(status.error)
                }
                is ItemsListVM.ViewStatus.Success -> {
                    adapter?.submitList(status.items)
                    removeProgressDialog()
                }
            }
        }
    }

    private fun handleFailed(error: ErrorEntity) {
        when (error){
            ErrorEntity.GeneralError -> showToast(getString(R.string.items_list_failed_getting_items))
            ErrorEntity.NetworkError -> showToast(getString(R.string.no_internet_connection))
            is ErrorEntity.ServerError -> showToast(getString(R.string.items_list_failed_getting_items))
        }
    }

    override fun onItemClicked(itemModel: ItemModel) {
        Toast.makeText(requireContext(), itemModel.name, Toast.LENGTH_SHORT).show() //TODO
    }
}


