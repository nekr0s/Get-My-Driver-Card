package com.example.nekr0s.get_my_driver_card.validator;

import android.util.Patterns;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.base.CreateValidator;

import java.util.regex.Pattern;

public class UserCreateValidator implements CreateValidator {

    private ErrorCode errorCode;

    private String regexNames = "^[a-zA-Z]*$";

    private String regexNumbersOnly = "^[0-9]*$";

    private String regexDate = "^(?:(?:31(\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\.)" +
            "(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)" +
            "?\\d{2})$|^(?:29(\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|" +
            "[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])" +
            "(\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    // dd.mm.yyyy (regex for correct date in correct format)

    private static final Pattern CYRILLIC_PATTERN = Pattern.compile("[А-яЁё][-А-яЁё]+");
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    @Override
    public ErrorCode validate(String email, String password, String password2) {
        if (email.isEmpty()) {
            return ErrorCode.EMAIL_NULL;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ErrorCode.EMAIL_NOT_CORRECT;
        } else if (password.isEmpty()) {
            return ErrorCode.PASSWORD_NULL;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return ErrorCode.PASSWORD_TOO_SIMPLE;
        } else if (!password.equals(password2)) {
            return ErrorCode.PASSWORDS_DONT_MATCH;
        }
        return ErrorCode.EVERYTHING_OK;
    }



    @Override
    public ErrorCode validatePreviousCardfragment(String countryIssuer, String issuingAuthority, String tCardNumber, String dateOfExpiry) {
        if (countryIssuer.isEmpty()) {
            return ErrorCode.COUNTRY_NULL;
        } else if (countryIssuer.length() > 25) {
            return ErrorCode.COUNTRY_INVALID;

        } else if (issuingAuthority.isEmpty()) {
            return ErrorCode.ISSUING_AUTHORITY_NULL;
        } else if (issuingAuthority.length() > 25) {
            return ErrorCode.ISSUING_AUTHORITY_INVALID;
        } else if (tCardNumber.isEmpty()) {
            return ErrorCode.TACH_NULL;
        } else if (!tCardNumber.matches(regexNumbersOnly)) {
            return ErrorCode.TACH_NOT_VALID;
        } else if (dateOfExpiry.isEmpty()) {
            return ErrorCode.DATE_OF_EXPIRY_NULL;
        } else if (!dateOfExpiry.matches(regexDate)) {
            return ErrorCode.DATE_NOT_VALID;

        }
        return ErrorCode.EVERYTHING_OK;
    }


}
