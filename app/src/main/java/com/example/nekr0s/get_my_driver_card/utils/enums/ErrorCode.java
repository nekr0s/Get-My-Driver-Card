package com.example.nekr0s.get_my_driver_card.utils.enums;

import android.content.Context;
import android.content.res.Resources;

public enum ErrorCode {
    EMAIL_NULL("Email can't be empty"),
    EMAIL_NOT_CORRECT("Email is not valid"),
    PASSWORD_NULL("Password can't be empty"),
    PASSWORD_TOO_SIMPLE("Password is too simple"),
    PASSWORDS_DONT_MATCH("Passwords dont' match"),
    EVERYTHING_OK("OK");

    private String string;

    ErrorCode(String string) {
        this.string = string;
    }


    public String getString() {
        return string;
    }

    public String getLabel(Context context) {
        Resources res = context.getResources();
        int resId = res.getIdentifier(this.name(), "string", context.getPackageName());
        if (0 != resId) {
            return (res.getString(resId));
        }
        return (name());
    }

}
