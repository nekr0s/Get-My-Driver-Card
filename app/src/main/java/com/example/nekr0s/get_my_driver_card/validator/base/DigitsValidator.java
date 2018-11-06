package com.example.nekr0s.get_my_driver_card.validator.base;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;

public class DigitsValidator implements ValidatorDigits {

    public DigitsValidator() {
    }

    private String regexNumbersOnly = "^[0-9]*$";
    private String regexPhoneNumber = "(\\+[0-9]+[\\- \\.]*)?(\\([0-9]+\\)[\\- \\.]*)?([0-9][0-9\\- \\.]+[0-9])";

    @Override
    public ErrorCode isPersonalNumberValid(String input) {

        if (input.isEmpty()) return ErrorCode.ID_NULL;
        else if (!(input).matches(regexNumbersOnly)) return ErrorCode.ID_INVALID;
        else if ((input).length() > 10) return ErrorCode.ID_TOO_LONG;
        else return ErrorCode.ID_OK;


    }

    @Override
    public ErrorCode isPhoneNumberValid(String input) {
        if (input.isEmpty()) return ErrorCode.PHONE_NULL;
        else if (!(input).matches(regexPhoneNumber)) return ErrorCode.PHONE_INVALID;
        else if ((input).length() > 20) return ErrorCode.PHONE_TOO_LONG;
        else return ErrorCode.PHONE_OK;
    }

    @Override
    public ErrorCode isTachNumberValid(String input) {
        return null;
    }

    @Override
    public ErrorCode isLicenseNumberValid(String input) {
        return null;
    }
}
