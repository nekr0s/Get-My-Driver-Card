package com.example.nekr0s.get_my_driver_card.views.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.nekr0s.get_my_driver_card.R;
import com.example.nekr0s.get_my_driver_card.utils.dialog.CustomListener;
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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        AlertDialog dialog = builder.create();
        dialog.setView(view);
        dialog.show();
        Button button = view.findViewById(R.id.register_confirm_button);
        button.setOnClickListener(new CustomListener(view.getContext(), dialog, mTILValidator));


        ButterKnife.bind(this, view);
        return dialog;
    }
}
