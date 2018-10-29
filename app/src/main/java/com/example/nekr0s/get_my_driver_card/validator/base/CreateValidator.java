package com.example.nekr0s.get_my_driver_card.validator.base;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;

public interface CreateValidator {
    ErrorCode validate(String email, String password, String password2);

    ErrorCode validatePreviousCardfragment(String countryIssuer, String issuingAuthority,
                                           String tCardNumber, String dateOfExpiry);

    ErrorCode validateNcFragment(String firstName, String firstNameCyr, String lastName,
                                 String lastNameCyr, String personalNum, String dateOfBirth,
                                 String address, String phoneNumber, String email);

}
