package com.estarta.core.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

/**
 * Created by Anas on 10/23/2017.
 */


public class CustomDialog extends DialogFragment {

    private Builder mBuilder;
    private Dialog mDialog;



    /**
     * Interface definition for passing style data.
     */
    public interface Listener{
        /**
         * Get a Dialog instance used for this fragment.
         * @param dialog
         */
        public void onBuildDone(Dialog dialog);

        /**
         * Handle click event on Positive Action.
         */
        public void onPositiveActionClicked(DialogFragment fragment);

        /**
         * Handle click event on Negative Action.
         */
        public void onNegativeActionClicked(DialogFragment fragment);

        /**
         * Handle click event on Neutral Action.
         */
        public void onNeutralActionClicked(DialogFragment fragment);

        /**
         * Handle OnCancel event of dialog.
         * @param dialog
         */
        public void onCancel(DialogInterface dialog);

        /**
         * Handle OnDismiss event of dialog.
         * @param dialog
         */
        public void onDismiss(DialogInterface dialog);
    }



    public static class Builder implements Listener{

        protected View mContentView;
        protected CharSequence mTitle;
        protected CharSequence mMessage;
        protected CharSequence mPositive;
        protected CharSequence mNegative;
        protected CharSequence mNeutral;



        public Builder contentView(View view){
            mContentView = view;
            return this;
        }

        public Builder title(CharSequence title){
            mTitle = title;
            return this;
        }

        public Builder message(CharSequence message) {
            this.mMessage = message;
            return this;
        }

        public Builder positiveAction(CharSequence action){
            mPositive = action;
            return this;
        }

        public Builder negativeAction(CharSequence action){
            mNegative = action;
            return this;
        }

        public Builder neutralAction(CharSequence action){
            mNeutral = action;
            return this;
        }


        @Override
        public void onPositiveActionClicked(DialogFragment fragment) {
            fragment.dismiss();
        }

        @Override
        public void onNegativeActionClicked(DialogFragment fragment) {
            fragment.dismiss();
        }

        @Override
        public void onNeutralActionClicked(DialogFragment fragment) {
            fragment.dismiss();
        }

        @Override
        public void onCancel(DialogInterface dialog) {}

        @Override
        public void onDismiss(DialogInterface dialog) {}


        /**
         * This function will be called after Builder done apply styling to Dialog.
         *
         * @param dialog The Dialog instance.
         */
        public void onBuildDone(Dialog dialog){}
    }


    public static DialogFragment newInstance(Builder builder){
        CustomDialog fragment = new CustomDialog();
        fragment.mBuilder = builder;
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(mBuilder.mTitle);


        if (mBuilder.mMessage != null)
            builder.setMessage(mBuilder.mMessage);


        if (mBuilder.mContentView != null)
            builder.setView(mBuilder.mContentView);


        builder.setPositiveButton(mBuilder.mPositive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       mBuilder.onPositiveActionClicked(CustomDialog.this);
                    }
                })
                .setNegativeButton(mBuilder.mNegative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mBuilder.onNegativeActionClicked(CustomDialog.this);
                    }
                })
                .setNeutralButton(mBuilder.mNeutral, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mBuilder.onNeutralActionClicked(CustomDialog.this);
                    }
                });
        // Create the AlertDialog object and return it
        mDialog = builder.create();



        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mBuilder.onBuildDone(mDialog);
            }
        });

        return mDialog;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public Dialog getDialog() {
        return mDialog;
    }
}