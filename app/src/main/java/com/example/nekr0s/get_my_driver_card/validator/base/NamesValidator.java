package com.example.nekr0s.get_my_driver_card.validator.base;

import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;


public class NamesValidator implements NameValidator {


    private String regexCyrillic = "[А-яЁё][-А-яЁё]+";


    @Override
    public ErrorCode isFirstNameValid(String input) {
        if (input.isEmpty()) return ErrorCode.NAME_NULL;
        else if (input.length() > Constants.NAME_MAX_LENGTH) return ErrorCode.NAME_TOO_LONG;
        else return ErrorCode.NAME_OK;

    }

    @Override
    public ErrorCode isLastNameValid(String input) {
        if (input.isEmpty()) return ErrorCode.LAST_NAME_NULL;
        else if (input.length() > Constants.LAST_NAME_MAX_LENGTH)
            return ErrorCode.LAST_NAME_TOO_LONG;
        else return ErrorCode.LAST_NAME_OK;
    }

    @Override
    public ErrorCode isFirstNameCyrValid(String input) {
        if (input.isEmpty()) return ErrorCode.CYR_NAME_NULL;
        else if (!input.matches(regexCyrillic)) return ErrorCode.CYR_NAME_NOT_IN_CYRILLIC;
        else if (input.length() > Constants.NAME_MAX_LENGTH) return ErrorCode.CYR_NAME_TOO_LONG;
        else return ErrorCode.CYR_NAME_OK;
    }

    @Override
    public ErrorCode isLastNameCyrValid(String input) {
        if (input.isEmpty()) return ErrorCode.CYR_LAST_NAME_NULL;
        else if (!input.matches(regexCyrillic)) return ErrorCode.CYR_LAST_NAME_NOT_IN_CYRILLIC;
        else if (input.length() > Constants.NAME_MAX_LENGTH)
            return ErrorCode.CYR_LAST_NAME_TOO_LONG;
        else return ErrorCode.CYR_LAST_NAME_OK;
    }

    @Override
    public ErrorCode isAddressValid(String input) {
        if (input.isEmpty()) return ErrorCode.ADDRESS_NULL;
        else if (input.length() > Constants.ADDRESS_MAX_LENGTH) return ErrorCode.ADDRESS_TOO_LONG;
        else return ErrorCode.ADDRESS_OK;

    }

    @Override
    public ErrorCode isIssuerCountryValid(String input) {
        if (input.isEmpty()) return ErrorCode.COUNTRY_NULL;
        // TODO: 11/6/2018
        return null;
    }

    @Override
    public ErrorCode isIssuerAuthorityValid(String input) {
        // TODO: 11/6/2018
        return null;
    }

    @Override
    public ErrorCode isEuCountryOfIssuingValid(String input) {
        if (input.isEmpty()) return ErrorCode.COUNTRY_NULL;
        else if (input.length() > Constants.COUNTRY_MAX_LENGTH)
            return ErrorCode.COUNTRY_TOO_LONG;
        else return ErrorCode.COUNTRY_OK;
    }

    @Override
    public ErrorCode isLicenseCountryOfIssuingValid(String input) {
        if (input.isEmpty()) return ErrorCode.LICENSE_COUNTRY_ISSUER_NULL;
        else if (input.length() > Constants.COUNTRY_MAX_LENGTH)
            return ErrorCode.LICENSE_COUNTRY_ISSUER_INVALID;
        else return ErrorCode.LICENSE_COUNTRY_ISSUER_OK;
    }
}
