package com.example.nekr0s.get_my_driver_card.validator;

import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorLogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidator implements ValidatorLogin {

    private Pattern pattern;
    private Pattern patterntwo;
    private Matcher matcher;
    private Matcher matchertwo;


    private static final String PASSWORD_PATTERN =
            ("^" + "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");


    private static final String USERNAME_PATTERN =
            "/^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$/";

    public RegisterValidator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        patterntwo = Pattern.compile(USERNAME_PATTERN);
    }

    @Override
    public ErrorCode isUsernameValid(String input) {
        if (input.isEmpty()) return ErrorCode.USERNAME_NULL;
        if (input.length() < Constants.EMAIL_MIN_LENGTH || input.length() > Constants.EMAIL_MAX_LENGTH)
            return ErrorCode.USERNAME_TOO_SIMPLE;
        else if (!validateUsername(input)) return ErrorCode.USERNAME_INVALID;
        else return ErrorCode.USERNAME_OK;


    }

    @Override
    public ErrorCode isPasswordValid(String input, String confirmPassword) {
        if (input.isEmpty()) return ErrorCode.PASSWORD_NULL;
        else if (!validatePassword(input)) return ErrorCode.PASSWORD_TOO_SIMPLE;
        else if (!input.equals(confirmPassword)) return ErrorCode.PASSWORDS_DONT_MATCH;
        else return ErrorCode.PASSWORD_OK;
    }

    private boolean validatePassword(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }

    private boolean validateUsername(final String hex) {

        matchertwo = patterntwo.matcher(hex);
        return matchertwo.matches();

    }

}
