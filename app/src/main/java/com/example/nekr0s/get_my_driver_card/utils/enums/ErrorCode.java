package com.example.nekr0s.get_my_driver_card.utils.enums;

import android.content.Context;
import android.content.res.Resources;

public enum ErrorCode {
    //Registration Validation

    FIELD_TOO_LONG, //


    PASSWORD_NULL,
    PASSWORD_TOO_SIMPLE,
    PASSWORDS_DONT_MATCH,
    EVERYTHING_OK,

    //New Card Info Validation
    NAME_NULL,
    NAME_NOT_VALID,
    NAME_TOO_LONG, //
    NAME_OK,

    CYR_NAME_NULL,
    CYR_NAME_NOT_VALID,
    CYR_NAME_TOO_LONG, //
    NAME_NOT_IN_CYRILLIC,
    CYR_NAME_OK,


    LAST_NAME_NULL,
    LAST_NAME_NOT_VALID,
    LAST_NAME_TOO_LONG,
    LAST_NAME_OK,

    CYR_LAST_NAME_NULL,
    CYR_LAST_NAME_NOT_VALID,
    CYR_LAST_NAME_TOO_LONG,
    LAST_NAME_NOT_IN_CYRILLIC,
    CYR_LAST_NAME_OK,

    ID_NULL,
    NOT_DIGIT,
    ID_INVALID,

    ADDRESS_TOO_LONG,
    ADDRESS_NULL,

    PHONE_NULL,
    PHONE_TOO_LONG, //
    PHONE_INVALID,

    DATE_NULL,
    DATE_INVALID,

    EMAIL_CARD_NULL,
    EMAIL_INVALID,
    EMAIL_TOO_LONG, //
    EMAIL_OK,

    // Previous Card Info Validation
    COUNTRY_NULL,
    COUNTRY_INVALID,
    ISSUING_AUTHORITY_NULL,
    ISSUING_AUTHORITY_INVALID,
    TACH_NULL,
    TACH_NOT_VALID,
    DATE_OF_EXPIRY_NULL,
    DATE_NOT_VALID;


    public String getLabel(Context context) {
        Resources res = context.getResources();
        int resId = res.getIdentifier(this.name(), "string", context.getPackageName());
        if (0 != resId) {
            return (res.getString(resId));
        }
        return (name());
    }

}
