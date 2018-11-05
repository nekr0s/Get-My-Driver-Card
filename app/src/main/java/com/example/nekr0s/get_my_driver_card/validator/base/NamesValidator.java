package com.example.nekr0s.get_my_driver_card.validator.base;

import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;


public class NamesValidator implements NameValidator {


    private String regexNames = "^[a-zA-Z]*$";
    private String regexCyrillic = "[А-яЁё][-А-яЁё]+";


    @Override
    public ErrorCode isFirstNameValid(String input) {
        if (input.isEmpty()) return ErrorCode.NAME_NULL;
        else if (!input.matches(regexNames)) return ErrorCode.NAME_NOT_VALID;
        else if (input.length() > Constants.NAME_MAX_LENGTH) return ErrorCode.NAME_TOO_LONG;
        else return ErrorCode.NAME_OK;

    }

    @Override
    public ErrorCode isLastNameValid(String input) {
        if (input.isEmpty()) return ErrorCode.LAST_NAME_NULL;
        else if (!input.matches(regexNames)) return ErrorCode.LAST_NAME_NOT_VALID;
        else if (input.length() > Constants.LAST_NAME_MAX_LENGTH)
            return ErrorCode.LAST_NAME_TOO_LONG;
        else return ErrorCode.LAST_NAME_OK;
    }

    @Override
    public ErrorCode isFirstNameCyrValid(String input) {
        if (input.isEmpty()) return ErrorCode.CYR_NAME_NULL;
        else if (!input.matches(regexCyrillic)) return ErrorCode.CYR_NAME_NOT_VALID;
        else if (input.length() > Constants.NAME_MAX_LENGTH) return ErrorCode.CYR_NAME_TOO_LONG;
        else return ErrorCode.CYR_NAME_OK;
    }

    @Override
    public ErrorCode isLastNameCyrValid(String input) {
        return null;
    }

    @Override
    public ErrorCode isIssuerCountryValid(String input) {
        return null;
    }

    @Override
    public ErrorCode isIssuerAuthorityValid(String input) {
        return null;
    }
}
