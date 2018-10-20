package com.example.nekr0s.get_my_driver_card.models;

import com.example.nekr0s.get_my_driver_card.utils.translator.Translator;

import java.io.FileNotFoundException;

public class UserInfo {
    private long EGN;
    private String firstName;
    private String lastName;
    private String firstNameCyrillic;
    private String lastNameCyrillic;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;

    public UserInfo(long EGN, String firstName, String lastName, String address, String phoneNumber) {
        this.EGN = EGN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        setDateOfBirth(EGN);
    }

    public long getEGN() {
        return EGN;
    }

    public void setEGN(long EGN) {
        this.EGN = EGN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstNameCyrillic() {
        return firstNameCyrillic;
    }

    public String getLastNameCyrillic() {
        return lastNameCyrillic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    private void setDateOfBirth(long EGN) {
        String egnString = String.valueOf(EGN);
        String year = "19" + egnString.substring(0, 2);
        String month = egnString.substring(2, 4);
        String day = egnString.substring(4, 6);
        this.dateOfBirth = day + '.' + month + '.' + year;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void setCyrillicNames(String firstName, String lastName) throws FileNotFoundException {
        Translator translator = new Translator();
//        this.firstNameCyrillic = translator.translate(firstName);
//        this.lastNameCyrillic = translator.translate(lastName);
    }
}
