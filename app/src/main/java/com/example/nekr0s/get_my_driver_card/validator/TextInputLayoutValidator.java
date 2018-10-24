package com.example.nekr0s.get_my_driver_card.validator;

import android.support.design.widget.TextInputLayout;
import android.util.Patterns;

import com.example.nekr0s.get_my_driver_card.validator.base.CreateValidator;

import java.util.Objects;
import java.util.regex.Pattern;

public class TextInputLayoutValidator implements CreateValidator<TextInputLayout> {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    public boolean isValid(TextInputLayout email, TextInputLayout password
            , TextInputLayout confirmPassword) {
        return password.equals(confirmPassword) &&
                validateEmail(email) &&
                validatePassword(password) &&
                validatePasswordTwo(confirmPassword);
    }

    private boolean validateEmail(TextInputLayout mTIL_email_register) {
        String emailInput = mTIL_email_register.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            mTIL_email_register.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mTIL_email_register.setError("Please enter a valid email address");
            return false;
        } else {
            mTIL_email_register.setError(null);
            return true;
        }
    }

    private boolean validatePassword(TextInputLayout mTIL_password_register) {
        String passwordInput = Objects.requireNonNull(mTIL_password_register.getEditText()).getText().toString().trim();

        if (passwordInput.isEmpty()) {
            mTIL_password_register.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            mTIL_password_register.setError("Password too weak");
            return false;
        } else {
            mTIL_password_register.setError(null);
            return true;
        }
    }

    private boolean validatePasswordTwo(TextInputLayout mTIL_confirm_password) {
        String passwordInput = Objects.requireNonNull(mTIL_confirm_password.getEditText()).getText().toString().trim();

        if (passwordInput.isEmpty()) {
            mTIL_confirm_password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            mTIL_confirm_password.setError("Password too weak");
            return false;
        } else {
            mTIL_confirm_password.setError(null);
            return true;
        }
    }
}

