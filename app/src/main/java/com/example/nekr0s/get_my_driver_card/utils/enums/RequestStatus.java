package com.example.nekr0s.get_my_driver_card.utils.enums;

public enum RequestStatus {
    REQUEST_NEW(0),
    REQUEST_APPROVED(1),
    REQUEST_DISAPPROVED(2),
    REQUEST_WAITING(3);

    private int num;

    RequestStatus(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
