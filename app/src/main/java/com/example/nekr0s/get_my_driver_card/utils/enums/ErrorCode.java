package com.example.nekr0s.get_my_driver_card.utils.enums;

import android.content.Context;
import android.content.res.Resources;

public enum ErrorCode {
    //Registration Validation


    USERNAME_NULL,
    USERNAME_TOO_SIMPLE,
    USERNAME_INVALID,
    USERNAME_OK,

    PASSWORD_NULL,
    PASSWORD_TOO_SIMPLE,
    PASSWORDS_DONT_MATCH,
    PASSWORD_OK,
    EVERYTHING_OK,

    //New Card Info Validation
    NAME_NULL,
    NAME_NOT_VALID,
    NAME_TOO_LONG,
    NAME_OK,

    CYR_NAME_NULL,
    CYR_NAME_TOO_LONG,
    CYR_NAME_NOT_IN_CYRILLIC,
    CYR_NAME_OK,


    LAST_NAME_NULL,
    LAST_NAME_NOT_VALID,
    LAST_NAME_TOO_LONG,
    LAST_NAME_OK,

    CYR_LAST_NAME_NULL,
    CYR_LAST_NAME_TOO_LONG,
    CYR_LAST_NAME_NOT_IN_CYRILLIC,
    CYR_LAST_NAME_OK,

    ID_NULL,
    NOT_DIGIT,
    ID_TOO_LONG,
    ID_INVALID,
    ID_OK,

    ADDRESS_TOO_LONG,
    ADDRESS_NULL,
    ADDRESS_OK,

    PHONE_NULL,
    PHONE_TOO_LONG,
    PHONE_INVALID,
    PHONE_OK,

    DATE_NULL,
    DATE_INVALID,
    DATE_OK,

    EMAIL_CARD_NULL,
    EMAIL_INVALID,
    EMAIL_TOO_LONG,
    EMAIL_OK,

    // EXCHANGE Fragment
    COUNTRY_NULL,
    COUNTRY_INVALID,
    COUNTRY_TOO_LONG,
    COUNTRY_OK,

    TACH_NULL,
    TACH_NOT_VALID,
    TACH_OK,

    LICENSE_COUNTRY_ISSUER_NULL,
    LICENSE_COUNTRY_ISSUER_INVALID,
    LICENSE_COUNTRY_ISSUER_OK,

    LICENSE_NUMBER_NULL,
    LICENSE_NUMBER_INVALID,
    LICENSE_NUMBER_OK,


    // Prev Card Info

    ISSUING_AUTHORITY_NULL,
    ISSUING_AUTHORITY_INVALID,
    ISSUING_AUTHORITY_OK;


    public String getLabel(Context context) {
        Resources res = context.getResources();
        int resId = res.getIdentifier(this.name(), "string", context.getPackageName());
        if (0 != resId) {
            return (res.getString(resId));
        }
        return (name());
    }

}
