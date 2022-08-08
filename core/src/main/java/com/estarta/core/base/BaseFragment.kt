package com.estarta.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.estarta.core.di.viewmodel.DaggerViewModelFactory
import javax.inject.Inject

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {

    protected lateinit var viewBinding: V
    @LayoutRes
    protected abstract fun getLayoutId(): Int
    protected abstract fun bindData(viewDataBinding: V)

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    /** It's a replacement of **lazy** to lazy initialize the viewModel */
    @MainThread
    inline fun <reified VM : BaseViewModel> viewModelsLazy() = viewModels<VM> { viewModelFactory }

    /** It's a replacement of **lazy** to lazy initialize the activityViewModel */
    @MainThread
    inline fun <reified VM : BaseViewModel> activityViewModelsLazy() = activityViewModels<VM> { viewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        performDataBinding(inflater, container)
        bindData(viewBinding)
        return viewBinding.root
    }

    private fun performDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        viewBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        viewBinding.lifecycleOwner = this
    }
}