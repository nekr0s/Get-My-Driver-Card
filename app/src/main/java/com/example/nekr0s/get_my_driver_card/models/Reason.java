package com.example.nekr0s.get_my_driver_card.models;

import com.example.nekr0s.get_my_driver_card.utils.enums.RequestReason;

public class Reason {
    private boolean isSelected;
    private RequestReason requestReason;
    private String requestReasonString;

    public Reason(boolean isSelected, RequestReason requestReason, String requestReasonString) {
        this.isSelected = isSelected;
        this.requestReason = requestReason;
        this.requestReasonString = requestReasonString;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public RequestReason getRequestReason() {
        return requestReason;
    }

    public void setRequestReason(RequestReason requestReason) {
        this.requestReason = requestReason;
    }

    public String getRequestReasonString() {
        return requestReasonString;
    }

    public void setRequestReasonString(String requestReasonString) {
        this.requestReasonString = requestReasonString;
    }
}
