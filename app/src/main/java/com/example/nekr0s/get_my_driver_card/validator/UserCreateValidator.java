package com.example.nekr0s.get_my_driver_card.validator;

import android.util.Patterns;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.base.CreateValidator;

import java.util.regex.Pattern;

public class UserCreateValidator implements CreateValidator {

    private ErrorCode errorCode;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    public ErrorCode validate(String email, String password, String password2) {
        if (email.isEmpty()) {
            return ErrorCode.EMAIL_NULL;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ErrorCode.EMAIL_NOT_CORRECT;
        } else if (password.isEmpty()) {
            return ErrorCode.PASSWORD_NULL;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return ErrorCode.PASSWORD_TOO_SIMPLE;
        } else if (!password.equals(password2)) {
            return ErrorCode.PASSWORDS_DONT_MATCH;
        }
        return ErrorCode.EVERYTHING_OK;
    }

}
