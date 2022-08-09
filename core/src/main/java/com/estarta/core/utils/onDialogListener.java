package com.estarta.core.utils;

import android.app.Dialog;
import android.content.Context;
import androidx.fragment.app.DialogFragment;

/**
 * Created by Lenovo on 11/18/2015.
 */
public abstract class onDialogListener {
    public void onPositiveClickListener(DialogFragment fragment){}
    public void onNegativeClickListener(DialogFragment fragment){}
    public void onNeutralClickListener(DialogFragment fragment){}
    public Dialog onBuild(Context context, int styleId){return null;}
    public void onBuildDone(Dialog dialog){}
}

