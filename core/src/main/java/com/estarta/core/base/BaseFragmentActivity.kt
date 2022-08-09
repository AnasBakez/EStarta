package com.estarta.core.base

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.estarta.core.di.viewmodel.DaggerViewModelFactory
import javax.inject.Inject

abstract class BaseFragmentActivity<V : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewBinding: V

    var pdLoadingView: ProgressDialog? = null

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    /** It's a replacement of **lazy** to lazy initialize the viewModel */
    @MainThread
    inline fun <reified VM : BaseViewModel> viewModelsLazy() = viewModels<VM> { viewModelFactory }

    @LayoutRes
    protected abstract fun getLayoutId(): Int
    protected abstract fun bindData(viewDataBinding: V)
    protected abstract fun initDaggerComponent()
    protected open fun getExtrasFromIntent() {}


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        getExtrasFromIntent()
        initDaggerComponent()
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        performDataBinding()
        bindData(viewBinding)
    }

    private fun performDataBinding() {
        viewBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewBinding.lifecycleOwner = this
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addFragment(
        fragment: BaseFragment<*>?,
        addToBackStack: Boolean,
//        animation: FragmentAnimation? = DefaultFragAnimation(),
    ) {
        val ft = supportFragmentManager.beginTransaction()
//        if (animation != null) {
//            ft.setCustomAnimations(animation.enterAnimationRes, 0, 0, animation.exitAnimationRes)
//        }
        ft.add(fragmentContainerID, fragment!!)
        if (addToBackStack) {
            ft.addToBackStack(null)
        }
        ft.commit()
    }

    protected open val fragmentContainerID: Int
        protected get() {
            throw RuntimeException(
                String.format("Please implement getFragmentContainerID() in %s to give the container ID",
                    this.javaClass.name))
        }
}