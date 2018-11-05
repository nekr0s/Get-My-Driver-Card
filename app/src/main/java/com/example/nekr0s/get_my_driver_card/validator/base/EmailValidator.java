package com.example.nekr0s.get_my_driver_card.validator.base;


import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;


public class EmailValidator implements Validator {

    public EmailValidator() {

    }

    private String regexEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@ [a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\. [a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+";

    @Override
    public ErrorCode isValid(String input) {
        if (input.isEmpty()) return ErrorCode.EMAIL_CARD_NULL;
        else if (!input.matches(regexEmail)) return ErrorCode.EMAIL_INVALID;
        else if (input.length() > 256) return ErrorCode.EMAIL_TOO_LONG;
        return ErrorCode.EMAIL_OK;
    }

}
