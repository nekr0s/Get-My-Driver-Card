package com.example.nekr0s.get_my_driver_card.models;

public class Reason {
    private boolean isSelected;
    private String reasonName;

    public Reason(boolean isSelected, String reasonName) {
        this.isSelected = isSelected;
        this.reasonName = reasonName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }
}
