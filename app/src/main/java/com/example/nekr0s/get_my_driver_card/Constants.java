package com.example.nekr0s.get_my_driver_card;

public class Constants {
    public static final int EMAIL_MIN_LENGTH = 6;
    public static final int EMAIL_MAX_LENGTH = 50;
    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 50;

    public static final String USER_OBJ_EXTRA = "USER_EXTRA";

    // Request status
    public static final int REQUEST_NEW = 0;
    public static final int REQUEST_APPROVED = 1;
    public static final int REQUEST_NOT_APPROVED = 2;
    public static final int REQUEST_WAITING = 3;
    public static final int REQUEST_FINISHED = 200;

    // Request type
    public static final int REQUEST_TYPE_NEW = 101;
    public static final int REQUEST_TYPE_EXCHANGE = 102;
    public static final int REQUEST_TYPE_REPLACE = 103;
}
