package com.estarta.itemdetails.presentation

import android.os.Bundle
import com.estarta.core.base.BaseFragment
import com.estarta.itemdetails.R
import com.estarta.itemdetails.databinding.FragmentItemdetailsBinding

class ItemDetailsFragment : BaseFragment<FragmentItemdetailsBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_itemdetails
    }

    override fun bindData(viewDataBinding: FragmentItemdetailsBinding) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            val ss =  it.getString("AAA")

        }
    }
}