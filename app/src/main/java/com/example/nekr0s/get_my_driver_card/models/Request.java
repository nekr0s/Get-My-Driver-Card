package com.example.nekr0s.get_my_driver_card.models;

import android.graphics.Color;

import com.example.nekr0s.get_my_driver_card.utils.enums.RequestStatus;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestType;

import java.time.LocalDateTime;

public class Request {
    private int requestId;
    private RequestStatus status;
    private RequestType type;
    private String requestDate;
    private User user;

    public Request(int requestId, RequestStatus status, RequestType type, User user) {
        this.requestId = requestId;
        this.status = status;
        this.type = type;
        setRequestDate(LocalDateTime.now());
        this.user = user;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // methods
    public int color() {
        switch (status) {
            case REQUEST_APPROVED:
                return Color.GREEN;
            case REQUEST_WAITING:
                return Color.YELLOW;
            case REQUEST_DISAPPROVED:
                return Color.RED;
        }
        return Color.CYAN;
    }

    public String getStatusString() {
        switch (status) {
            case REQUEST_NEW:
                return "New";
            case REQUEST_WAITING:
                return "Waiting for approval.";
            case REQUEST_DISAPPROVED:
                return "Request not approved.";
            case REQUEST_APPROVED:
                return "Request approved.";
        }
        return "Invalid";
    }

    public String getRequestType() {
        switch (type) {
            case TYPE_NEW:
                return "Brand new card";
            case TYPE_EXCHANGE:
                return "UserInfo exchange";
            case TYPE_REPLACE:
                return "UserInfo replace";
            case TYPE_RENEW:
                return "Renew card";
        }
        return "Invalid";
    }

    public String getRequestDate() {
        return requestDate;
    }

    private void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate.getDayOfMonth() + "." + requestDate.getMonthValue() + "."
                + requestDate.getYear() + " " + requestDate.getHour() + ":" + requestDate.getMinute();
    }
}
