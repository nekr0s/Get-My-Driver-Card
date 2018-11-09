package com.example.nekr0s.get_my_driver_card.models;

import android.graphics.Color;

import com.example.nekr0s.get_my_driver_card.utils.enums.RequestReason;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestStatus;
import com.example.nekr0s.get_my_driver_card.utils.enums.RequestType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Request implements Serializable {
    private int requestId;
    private RequestStatus requestStatus;
    private RequestType requestType;
    private RequestReason requestReason;
    private String requestDate;
    private Attachment attachment;
    private User user;

    public Request(RequestStatus status, RequestType requestType, RequestReason reason, User user) {
        this.requestStatus = status;
        this.requestType = requestType;
        this.requestReason = reason;
        setRequestDate(LocalDateTime.now());
        this.user = user;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    // methods
    public int color() {
        switch (requestStatus) {
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
        switch (requestStatus) {
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

    public String getShortStatusString() {
        switch (requestStatus) {
            case REQUEST_NEW:
                return "NEW";
            case REQUEST_WAITING:
                return "WAITING";
            case REQUEST_DISAPPROVED:
                return "DISAPPROVED";
            case REQUEST_APPROVED:
                return "APPROVED";
        }
        return "INVALID";
    }

    public String getRequestType() {
        switch (requestType) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        this.requestDate = requestDate.format(formatter);
    }

    public RequestReason getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(RequestReason requestReason) {
        this.requestReason = requestReason;
    }
}
