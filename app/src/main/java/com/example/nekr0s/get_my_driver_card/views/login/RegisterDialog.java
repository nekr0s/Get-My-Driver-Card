package com.example.nekr0s.get_my_driver_card.views.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.utils.Dialog.CustomListener;
import com.example.nekr0s.get_my_driver_card.validator.TextInputLayoutValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.CreateValidator;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterDialog extends AppCompatDialogFragment {


    @BindView(R.id.text_input_email_register)
    TextInputLayout mTIL_email_register;

    @BindView(R.id.text_input_password_one)
    TextInputLayout mTIL_password_register;

    @BindView(R.id.text_input_password_two)
    TextInputLayout mTIL_confirm_password;


    private final CreateValidator<TextInputLayout> mTILValidator = new TextInputLayoutValidator();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
//        button.setOnClickListener(new CustomListener(dialog, mTILValidator));

        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)

                .setTitle("Create new account")

                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("register", new CustomListener(getContext(), getDialog(), mTILValidator));


        ButterKnife.bind(this, view);
        return builder.create();
    }
}
