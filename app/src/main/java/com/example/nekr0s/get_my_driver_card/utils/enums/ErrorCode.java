package com.example.nekr0s.get_my_driver_card.utils.enums;

public enum ErrorCode {
    EMAIL_NULL("Email can't be empty."),
    EMAIL_NOT_CORRECT("Email is not valid."),
    PASSWORD_NULL("Password can't be null."),
    PASSWORD_TOO_SIMPLE("Password is too simple."),
    PASSWORDS_DONT_MATCH("Passwords don't match."),
    EVERYTHING_OK("OK");

    private String string;

    ErrorCode(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
