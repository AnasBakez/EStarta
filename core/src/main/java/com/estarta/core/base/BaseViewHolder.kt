package com.estarta.core.base

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.estarta.core.utils.Utils

abstract class BaseViewHolder<V : ViewDataBinding> : RecyclerView.ViewHolder {

    protected var binding: V
    protected lateinit var context : Context

    constructor(parent: ViewGroup, @LayoutRes resId : Int) : this(Utils.createBinding(parent, resId) as V){
        this.context = parent.context
    }

    private constructor(viewBinding: V) : super(viewBinding.root){
        this.binding = viewBinding
    }
}
