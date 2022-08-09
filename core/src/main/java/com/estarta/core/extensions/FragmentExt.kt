package com.estarta.core.extensions

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.estarta.core.base.BaseFragmentActivity
import com.estarta.core.base.BaseViewModel
import com.estarta.core.utils.onDialogListener

fun <T : BaseViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProvider(this)[viewModelClass]

fun <T : BaseViewModel> Fragment.obtainViewModelForActivity(viewModelClass: Class<T>) =
    ViewModelProvider(this.requireActivity())[viewModelClass]

fun Fragment.showDialog(
    title: String? = null, msg: String? = null, contentView: View? = null,
    positiveBtnTxt: String? = null, negativeBtnTxt: String? = null, neutralBtnTxt: String? = null,
    listener: onDialogListener?,
) {
    (activity as AppCompatActivity).showDialog(title, msg, contentView, positiveBtnTxt, negativeBtnTxt, neutralBtnTxt,
        listener)
}
fun Fragment.showToast(msg: String) {
    (activity as AppCompatActivity).showToast(msg)
}

fun Fragment.showProgressDialog(
    title: String? = null, msg: String,
    isRemovable: Boolean,
) {
    (activity as BaseFragmentActivity<*>).showProgressDialog(title, msg, isRemovable)
}

fun Fragment.removeProgressDialog() {
    (activity as BaseFragmentActivity<*>).removeProgressDialog()
}

fun Fragment.addToolbar(
    toolbar: Toolbar, title: String = "", enableHomeAsUp: Boolean = false,
    homeAsUpDrawable: Drawable? = null,
) {
    activity?.let {
        if (it is AppCompatActivity) {
            (activity as AppCompatActivity).addToolbar(toolbar, title, enableHomeAsUp, homeAsUpDrawable)
        }
    }
}

