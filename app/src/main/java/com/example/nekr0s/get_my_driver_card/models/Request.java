package com.example.nekr0s.get_my_driver_card.models;

import android.graphics.Color;

import com.example.nekr0s.get_my_driver_card.utils.Constants;

public class Request {
    private int requestId;
    private int status;
    private int type;
    private User user;

    public Request(int requestId, int status, int type, User user) {
        this.requestId = requestId;
        this.status = status;
        this.type = type;
        this.user = user;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
            case Constants.REQUEST_TYPE_NEW:
                return Color.GREEN;
            case Constants.REQUEST_WAITING:
                return Color.YELLOW;
            case Constants.REQUEST_NOT_APPROVED:
                return Color.RED;
        }
        return status;
    }

    public String getStatusString() {
        switch (status) {
            case Constants.REQUEST_NEW:
                return "New";
            case Constants.REQUEST_WAITING:
                return "Waiting for approval";
            case Constants.REQUEST_NOT_APPROVED:
                return "Request not approved";
        }
        return "Invalid";
    }

    public String getRequestType() {
        switch (type) {
            case Constants.REQUEST_TYPE_NEW:
                return "Brand new card";
            case Constants.REQUEST_TYPE_EXCHANGE:
                return "UserInfo exchange";
            case Constants.REQUEST_TYPE_REPLACE:
                return "UserInfo replace";
        }
        return "Invalid";
    }
}
