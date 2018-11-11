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
    private String lostDate;
    private String lostPlace;
    private String previousCountryOfIssuing;
    private String previousIssuingAuthority;
    private String previousTachCardNum;
    private String previousDateOfExpiry;
    private String previousLostDate;
    private String previousLostPlace;
    private String currentCountryOfIssuing;
    private String currentTachCardNum;
    private String currentDriverLicenseCountryOfIssuing;
    private String currentDriverLicenseNumber;
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
                return Color.rgb(125, 189, 0);
            case REQUEST_WAITING:
                return Color.rgb(220, 246, 0);
            case REQUEST_DISAPPROVED:
                return Color.rgb(255, 91, 0);
        }
        return Color.rgb(101, 156, 239);
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

    public String getRequestTypeString() {
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

    public RequestType getRequestType() {
        return requestType;
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

    public String getLostDate() {
        return lostDate;
    }

    public void setLostDate(String lostDate) {
        this.lostDate = lostDate;
    }

    public String getLostPlace() {
        return lostPlace;
    }

    public void setLostPlace(String lostPlace) {
        this.lostPlace = lostPlace;
    }

    public String getPreviousCountryOfIssuing() {
        return previousCountryOfIssuing;
    }

    public void setPreviousCountryOfIssuing(String previousCountryOfIssuing) {
        this.previousCountryOfIssuing = previousCountryOfIssuing;
    }

    public String getPreviousIssuingAuthority() {
        return previousIssuingAuthority;
    }

    public void setPreviousIssuingAuthority(String previousIssuingAuthority) {
        this.previousIssuingAuthority = previousIssuingAuthority;
    }

    public String getPreviousTachCardNum() {
        return previousTachCardNum;
    }

    public void setPreviousTachCardNum(String previousTachCardNum) {
        this.previousTachCardNum = previousTachCardNum;
    }

    public String getPreviousDateOfExpiry() {
        return previousDateOfExpiry;
    }

    public void setPreviousDateOfExpiry(String previousDateOfExpiry) {
        this.previousDateOfExpiry = previousDateOfExpiry;
    }

    public String getPreviousLostDate() {
        return previousLostDate;
    }

    public void setPreviousLostDate(String previousLostDate) {
        this.previousLostDate = previousLostDate;
    }

    public String getPreviousLostPlace() {
        return previousLostPlace;
    }

    public void setPreviousLostPlace(String previousLostPlace) {
        this.previousLostPlace = previousLostPlace;
    }

    public String getCurrentCountryOfIssuing() {
        return currentCountryOfIssuing;
    }

    public void setCurrentCountryOfIssuing(String currentCountryOfIssuing) {
        this.currentCountryOfIssuing = currentCountryOfIssuing;
    }

    public String getCurrentTachCardNum() {
        return currentTachCardNum;
    }

    public void setCurrentTachCardNum(String currentTachCardNum) {
        this.currentTachCardNum = currentTachCardNum;
    }

    public String getCurrentDriverLicenseCountryOfIssuing() {
        return currentDriverLicenseCountryOfIssuing;
    }

    public void setCurrentDriverLicenseCountryOfIssuing(String currentDriverLicenseCountryOfIssuing) {
        this.currentDriverLicenseCountryOfIssuing = currentDriverLicenseCountryOfIssuing;
    }

    public String getCurrentDriverLicenseNumber() {
        return currentDriverLicenseNumber;
    }

    public void setCurrentDriverLicenseNumber(String currentDriverLicenseNumber) {
        this.currentDriverLicenseNumber = currentDriverLicenseNumber;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestReasonString() {
        if (requestReason == null) return "Creating a new card.";
        switch (requestReason) {
            case REASON_LOST:
                return "Lost my card.";
            case REASON_STOLEN:
                return "My card got stolen.";
            case REASON_DAMAGED:
                return "My card got damaged.";
            case REASON_EXPIRED:
                return "My card has expired.";
            case REASON_SUSPENDED:
                return "My card got suspended.";
            case REASON_WITHDRAWN:
                return "My card got withdrawn.";
            case REASON_NAME_CHANGE:
                return "I wanted to change my name.";
            case REASON_PHOTO_CHANGE:
                return "I wanted to change my photo.";
            case REASON_DUE_TO_EXPIRE:
                return "My card is about to expire.";
            case REASON_ADDRESS_CHANGE:
                return "I wanted to change my address.";
            case REASON_MALFUNCTIONING:
                return "My card is malfunctioning.";
            default:
                return "Creating a new card.";
        }
    }
}
