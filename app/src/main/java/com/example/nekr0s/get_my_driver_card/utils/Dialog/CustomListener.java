package com.example.nekr0s.get_my_driver_card.utils.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.widget.Toast;

import com.example.nekr0s.get_my_driver_card.validator.base.CreateValidator;

public class CustomListener implements DialogInterface.OnClickListener {
    private final Dialog mDialog;
    private final CreateValidator<TextInputLayout> mValidator;
    private final Context ctx;

    public CustomListener(Context ctx, Dialog mDialog, CreateValidator<TextInputLayout> mValidator) {
        this.mDialog = mDialog;
        this.mValidator = mValidator;
        this.ctx = ctx;
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

        Toast.makeText(ctx, "sadasda", Toast.LENGTH_SHORT).show();
//        TextInputLayout mTIL_email_register = mDialog.findViewById(R.id.text_input_email_register);
//        TextInputLayout mTIL_password_register = mDialog.findViewById(R.id.text_input_password_one);
//        TextInputLayout mTIL_confirm_password = mDialog.findViewById(R.id.text_input_password_two);
//        if (mValidator.isValid(mTIL_email_register, mTIL_password_register,
//                mTIL_confirm_password)) {
//            mDialog.dismiss();
//        } else {
//
//        }
//
    }
}
