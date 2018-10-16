package com.example.nekr0s.get_my_driver_card.models;

import com.example.nekr0s.get_my_driver_card.models.translator.Translator;

import java.util.Date;

public class Card {
    private String ID;
    private String firstName;
    private String lastName;
    private String firstNameCyrillic;
    private String lastNameCyrillic;
    private String dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;

    public Card(String ID, String firstName, String lastName, String address, String phoneNumber) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        setDateOfBirth(getID());
        setCyrillicNames(firstName, lastName);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    private void setDateOfBirth(String id) {
        String year = "19" + id.substring(0, 2);
        String month = id.substring(2, 4);
        String day = id.substring(4, 6);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setCyrillicNames(String firstName, String lastName) {
        Translator translator = new Translator();
        this.firstNameCyrillic = translator.translate(firstName);
        this.lastNameCyrillic = translator.translate(lastName);
    }
}
