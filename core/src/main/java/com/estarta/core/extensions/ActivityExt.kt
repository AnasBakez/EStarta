package com.estarta.core.extensions

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.estarta.core.base.BaseFragmentActivity
import com.estarta.core.utils.CustomDialog
import com.estarta.core.utils.onDialogListener

fun AppCompatActivity.showDialog(
    title: String? = null, msg: String? = null, contentView: View? = null,
    positiveBtnTxt: String? = null, negativeBtnTxt: String? = null, neutralBtnTxt: String? = null,
    listener: onDialogListener?,
) {

    val builder: CustomDialog.Builder = object : CustomDialog.Builder() {
        override fun onPositiveActionClicked(fragment: DialogFragment) {
            if (listener != null) {
                listener.onPositiveClickListener(fragment)
            } else {
                super.onPositiveActionClicked(fragment)
            }
        }

        override fun onNegativeActionClicked(fragment: DialogFragment) {
            if (listener != null) {
                listener.onNegativeClickListener(fragment)
            } else {
                super.onNegativeActionClicked(fragment)
            }
        }

        override fun onNeutralActionClicked(fragment: DialogFragment) {
            if (listener != null) {
                listener.onNeutralClickListener(fragment)
            } else {
                super.onNeutralActionClicked(fragment)
            }
        }

        override fun onBuildDone(dialog: Dialog) {
            listener?.onBuildDone(dialog)
            super.onBuildDone(dialog)
        }
    }
    builder.title(title)

    if (contentView != null) {
        builder.contentView(contentView)
    } else {
        builder.message(msg)
    }
    if (positiveBtnTxt?.isEmpty() == false) {
        builder.positiveAction(positiveBtnTxt)
    }
    if (negativeBtnTxt?.isEmpty() == false) {
        builder.negativeAction(negativeBtnTxt)
    }
    if (neutralBtnTxt?.isEmpty() == false) {
        builder.neutralAction(neutralBtnTxt)
    }

    val dialogFragment = CustomDialog.newInstance(builder)
    dialogFragment.show(supportFragmentManager, null)
}

fun AppCompatActivity.addToolbar(
    toolbarId: Int, title: String = "", enableHomeAsUp: Boolean = false,
    homeAsUpDrawable: Drawable? = null,
) {
    val toolbar: Toolbar = try {
        findViewById<View>(toolbarId) as Toolbar
    } catch (e: Exception) {
        throw IllegalStateException(
            "Toolbar Id is not correct, Please make sure that the id sent is for support toolbar")
    }
    addToolbar(toolbar, title, enableHomeAsUp, homeAsUpDrawable)
}

fun AppCompatActivity.addToolbar(
    toolbar: Toolbar, title: String = "", enableHomeAsUp: Boolean = false,
    homeAsUpDrawable: Drawable? = null,
) {
    setSupportActionBar(toolbar)
    supportActionBar!!.setDisplayHomeAsUpEnabled(enableHomeAsUp)
    homeAsUpDrawable?.let {
        supportActionBar!!.setHomeAsUpIndicator(it)
    }
    this.title = title
}

fun <V : ViewDataBinding> BaseFragmentActivity<V>.showProgressDialog(
    title: String? = null, msg: String,
    isRemovable: Boolean,
) {

    pdLoadingView?.let {
        if (it.isShowing) return
    }

    pdLoadingView = ProgressDialog(this)
    pdLoadingView!!.setMessage(msg)
    title?.let { pdLoadingView!!.setTitle(it) }

    pdLoadingView!!.setCancelable(isRemovable)
    pdLoadingView!!.show()
}

fun <V : ViewDataBinding> BaseFragmentActivity<V>.removeProgressDialog() {
    pdLoadingView?.let {
        if (it.isShowing) it.dismiss()
    }
}

fun AppCompatActivity.showToast(msg : String) {
   Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.hideKeyboard(isInOnCreate: Boolean) {
    if (isInOnCreate) {
        window.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    } else {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Activity.showKeyboard(editText: EditText) {
    editText.requestFocus()
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}
