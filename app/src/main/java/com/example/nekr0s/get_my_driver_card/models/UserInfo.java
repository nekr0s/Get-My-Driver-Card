package com.example.nekr0s.get_my_driver_card.models;

import com.example.nekr0s.get_my_driver_card.utils.translator.Translator;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private int userInfoId;
    private String firstName;
    private String firstNameCyrillic;
    private String lastName;
    private String lastNameCyrillic;
    private String personalNumber;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;

    public UserInfo(String firstName, String firstNameCyrillic, String lastName, String lastNameCyrillic,
                    String personalNumber, String dateOfBirth, String address,
                    String phoneNumber, String email) {

        this.firstName = firstName;
        this.firstNameCyrillic = firstNameCyrillic;
        this.lastName = lastName;
        this.lastNameCyrillic = lastNameCyrillic;
        this.personalNumber = personalNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;

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

    private void setCyrillicNames(String firstName, String lastName) throws Exception {
        Translator translator = new Translator();
        this.firstNameCyrillic = translator.translate(firstName);
        this.lastNameCyrillic = translator.translate(lastName);
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
