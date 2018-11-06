package com.example.nekr0s.get_my_driver_card.validator.base;

import com.example.nekr0s.get_my_driver_card.utils.Constants;
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
        else if ((input).length() > Constants.ID_MAX_LENGTH) return ErrorCode.ID_TOO_LONG;
        else return ErrorCode.ID_OK;

    }

    @Override
    public ErrorCode isPhoneNumberValid(String input) {
        if (input.isEmpty()) return ErrorCode.PHONE_NULL;
        else if (!(input).matches(regexPhoneNumber)) return ErrorCode.PHONE_INVALID;
        else if ((input).length() > Constants.PHONE_NUMBER_MAX_LENGTH)
            return ErrorCode.PHONE_TOO_LONG;
        else return ErrorCode.PHONE_OK;
    }

    @Override
    public ErrorCode isTachNumberValid(String input) {
        if (input.isEmpty()) return ErrorCode.TACH_NULL;
        else if (!(input).matches(regexNumbersOnly)) return ErrorCode.TACH_NOT_VALID;
        else if ((input).length() > Constants.TACH_NUMBER_MAX_LENGTH)
            return ErrorCode.TACH_NOT_VALID;
        else return ErrorCode.TACH_OK;
    }

    @Override
    public ErrorCode isLicenseNumberValid(String input) {
        if (input.isEmpty()) return ErrorCode.LICENSE_NUMBER_NULL;
        else if (!(input).matches(regexNumbersOnly)) return ErrorCode.LICENSE_NUMBER_INVALID;
        else if ((input).length() > Constants.LICENSE_NUMBER_MAX_LENGTH)
            return ErrorCode.LICENSE_NUMBER_INVALID;
        else return ErrorCode.LICENSE_NUMBER_OK;
    }
}
