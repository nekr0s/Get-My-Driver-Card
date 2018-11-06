package com.example.nekr0s.get_my_driver_card.validator.base;


import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmailValidator implements Validator {


    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);

    }

    @Override
    public ErrorCode isValid(String input) {

        if (input.isEmpty()) return ErrorCode.EMAIL_CARD_NULL;
        else if (!validateEmail(input)) return ErrorCode.EMAIL_INVALID;
        else if (input.length() > Constants.EMAIL_MAX_LENGTH) return ErrorCode.EMAIL_TOO_LONG;
        return ErrorCode.EMAIL_OK;
    }

    public boolean validateEmail(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }

}
