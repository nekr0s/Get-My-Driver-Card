package com.example.nekr0s.get_my_driver_card.validator.base;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;

public interface NameValidator {

    ErrorCode isFirstNameValid(String input);

    ErrorCode isLastNameValid(String input);

    ErrorCode isFirstNameCyrValid(String input);

    ErrorCode isLastNameCyrValid(String input);

    ErrorCode isAddressValid(String input);

    ErrorCode isIssuerCountryValid(String input);

    ErrorCode isIssuerAuthorityValid(String input);

    ErrorCode isEuCountryOfIssuingValid(String input);

    ErrorCode isLicenseCountryOfIssuingValid(String input);



}
