package com.estarta.core.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

object Utils {

    fun createBinding(parent: ViewGroup, @LayoutRes resId: Int): ViewDataBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), resId, parent, false)
    }
}