package com.example.nekr0s.get_my_driver_card.models;

import java.io.Serializable;

public class Attachment implements Serializable {

    // We use strings and logtext because of bugs with Gson

    private int attachmentId;

    private String faceShot;

    private String idShot;

    private String signature;

    private String driverLicense;

    private String previousTachCard;

    private String previousEuCard;

    public Attachment() {
        // keep empty pls
    }

    public Attachment(String faceShot, String idShot, String signature, String driverLicense) {
        this.faceShot = faceShot;
        this.idShot = idShot;
        this.signature = signature;
        this.driverLicense = driverLicense;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getFaceShot() {
        return faceShot;
    }

    public void setFaceShot(String faceShot) {
        this.faceShot = faceShot;
    }

    public String getIdShot() {
        return idShot;
    }

    public void setIdShot(String idShot) {
        this.idShot = idShot;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getPreviousTachCard() {
        return previousTachCard;
    }

    public void setPreviousTachCard(String previousTachCard) {
        this.previousTachCard = previousTachCard;
    }

    public String getPreviousEuCard() {
        return previousEuCard;
    }

    public void setPreviousEuCard(String previousEuCard) {
        this.previousEuCard = previousEuCard;
    }
}
