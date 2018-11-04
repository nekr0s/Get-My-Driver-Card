package com.example.nekr0s.get_my_driver_card.models;

import com.example.nekr0s.get_my_driver_card.utils.Constants;

import java.io.Serializable;

public class Attachment implements Serializable {

    private int attachmentId;

    private byte[] faceShot;

    private byte[] idShot;

    private byte[] signature;

    private byte[] driverLicense;

    private byte[] previousTachCard;

    private byte[] previousEuCard;

    public Attachment() {
        // keep empty pls
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public byte[] getFaceShot() {
        return faceShot;
    }

    public void setFaceShot(byte[] faceShot) {
        this.faceShot = faceShot;
    }

    public byte[] getIdShot() {
        return idShot;
    }

    public void setIdShot(byte[] idShot) {
        this.idShot = idShot;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public byte[] getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(byte[] driverLicense) {
        this.driverLicense = driverLicense;
    }

    public byte[] getPreviousTachCard() {
        return previousTachCard;
    }

    public void setPreviousTachCard(byte[] previousTachCard) {
        this.previousTachCard = previousTachCard;
    }

    public byte[] getPreviousEuCard() {
        return previousEuCard;
    }

    public void setPreviousEuCard(byte[] previousEuCard) {
        this.previousEuCard = previousEuCard;
    }

    public boolean checkPictureBytes(int flag) throws NullPointerException {
        if (flag != Constants.NEEDS_BONUS)
            return idShot.length != 0 && driverLicense.length != 0 && faceShot.length != 0;
        else return idShot.length != 0 && driverLicense.length != 0 && faceShot.length != 0 &&
                previousTachCard.length != 0;
    }
}
