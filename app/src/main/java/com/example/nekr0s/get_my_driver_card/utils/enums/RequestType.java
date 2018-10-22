package com.example.nekr0s.get_my_driver_card.utils.enums;

public enum RequestType {
    TYPE_NEW(10),
    TYPE_EXCHANGE(20),
    TYPE_REPLACE(30),
    TYPE_RENEW(40);

    private int num;

    RequestType(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}