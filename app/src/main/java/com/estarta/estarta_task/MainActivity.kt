package com.estarta.estarta_task

import com.estarta.core.base.BaseFragmentActivity
import com.estarta.estarta_task.databinding.ActivityMainBinding

class MainActivity : BaseFragmentActivity<ActivityMainBinding>(){

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun bindData(viewDataBinding: ActivityMainBinding) {

    }

    override fun initDaggerComponent() {

    }
}