package com.example.nekr0s.get_my_driver_card.utils.enums;

public enum RequestReason {
    REASON_LOST(100),
    REASON_STOLEN(200),
    REASON_MALFUNCTIONING(300),
    REASON_DAMAGED(400),
    REASON_ADDRESS_CHANGE(500),
    REASON_NAME_CHANGE(600),
    REASON_PHOTO_CHANGE(700);

    private int num;

    RequestReason(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
