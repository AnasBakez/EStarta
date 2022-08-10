package com.estarta.itemdetails.presentation.view

import android.os.Bundle
import android.view.View
import com.estarta.core.base.BaseFragment
import com.estarta.core.di.helpers.CoreInjectHelper
import com.estarta.core.utils.AppImageLoader
import com.estarta.itemdetails.R
import com.estarta.itemdetails.bridge.ItemDetailsInjectorHelper
import com.estarta.itemdetails.databinding.FragmentItemdetailsBinding
import com.estarta.itemdetails.di.DaggerItemDetailsComponent
import com.estarta.itemdetails.di.ItemDetailsComponent
import com.estarta.itemdetails.domain.model.ItemDetailsModel
import com.estarta.itemdetails.presentation.viewmodel.ItemDetailsVM

class ItemDetailsFragment : BaseFragment<FragmentItemdetailsBinding>() {

    private val viewModel: ItemDetailsVM by viewModelsLazy()
    private lateinit var itemsListComponent: ItemDetailsComponent

    override fun getLayoutId(): Int {
        return R.layout.fragment_itemdetails
    }

    override fun bindData(viewDataBinding: FragmentItemdetailsBinding) {
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
        itemsListComponent = DaggerItemDetailsComponent.builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(requireContext().applicationContext))
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setItemDetailsModel(generateItemDetailsFromBundle())
    }

    private fun generateItemDetailsFromBundle(): ItemDetailsModel? {
        return ItemDetailsInjectorHelper.provideDependenciesBridge(requireActivity().applicationContext)
            .parseNavArgs(arguments)
    }

    override fun onResume() {
        super.onResume()
        listenItemsListChanges()
    }

    private fun listenItemsListChanges() {
        viewModel.itemDetails.observe(viewLifecycleOwner) { item ->
            if (item != null) {
                val image = item.images[0]
                AppImageLoader.loadImage(viewBinding.itemDetailsImage, image.url, image.thumbnailUrl)
            }
        }
    }
}