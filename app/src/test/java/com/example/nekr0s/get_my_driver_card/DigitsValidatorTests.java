package com.example.nekr0s.get_my_driver_card;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.DigitsValidator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DigitsValidatorTests {


    //isPersonalNumberValid Tests

    @Test
    public void DigitsValidator_Should_Return_ID_NULL_ErrorCode_When_ID_Field_IsEmpty() {
        DigitsValidator validator = new DigitsValidator();

        String input = "";

        assertEquals(ErrorCode.ID_NULL, validator.isPersonalNumberValid(input));
    }

    @Test
    public void DigitsValidator_Should_Return_ID_INVALID_ErrorCode_When_ID_Field_Contaits_Anything_other_than_Numbers() {
        DigitsValidator validator = new DigitsValidator();

        String input = "zddsd";

        assertEquals(ErrorCode.ID_INVALID, validator.isPersonalNumberValid(input));

        String inputtwo = "$935252";

        assertEquals(ErrorCode.ID_INVALID, validator.isPersonalNumberValid(inputtwo));

        String inputthree = "6463.52352";

        assertEquals(ErrorCode.ID_INVALID, validator.isPersonalNumberValid(inputthree));
    }

    @Test
    public void DigitsValidator_Should_Return_ID_TOO_LONG_ErrorCode_When_ID_Field_isOver_10digits() {
        DigitsValidator validator = new DigitsValidator();

        String input = "12345678910";

        assertEquals(ErrorCode.ID_TOO_LONG, validator.isPersonalNumberValid(input));
    }

    @Test
    public void DigitsValidator_Should_Return_ID_OK_ErrorCode_When_ID_Field_IsCorrect() {
        DigitsValidator validator = new DigitsValidator();

        String input = "1234567890";

        assertEquals(ErrorCode.ID_OK, validator.isPersonalNumberValid(input));
    }


    //isPhoneNumberValid Tests

    @Test
    public void DigitsValidator_Should_Return_PHONE_NULL_ErrorCode_When_Phone_Field_IsEmpty() {
        DigitsValidator validator = new DigitsValidator();

        String input = "";

        assertEquals(ErrorCode.PHONE_NULL, validator.isPhoneNumberValid(input));
    }

    @Test
    public void DigitsValidator_Should_Return_PHONE_INVALID_ErrorCode_When_Phone_Field_Does_Not_Match_Phone_Regex() {
        DigitsValidator validator = new DigitsValidator();

        String input = "ff241512";

        assertEquals(ErrorCode.PHONE_INVALID, validator.isPhoneNumberValid(input));

        String inputtwo = "$935252";

        assertEquals(ErrorCode.PHONE_INVALID, validator.isPhoneNumberValid(inputtwo));

    }

    @Test
    public void DigitsValidator_Should_Return_PHONE_TOO_LONG_ErrorCode_When_PHONE_Field_isOver_20digits() {
        DigitsValidator validator = new DigitsValidator();

        String input = "12345678910" +
                "12345678910";

        assertEquals(ErrorCode.PHONE_TOO_LONG, validator.isPhoneNumberValid(input));
    }

    @Test
    public void DigitsValidator_Should_Return_Phone_OK_ErrorCode_When_ID_Field_IsCorrect() {
        DigitsValidator validator = new DigitsValidator();

        String input = "08884951245";

        assertEquals(ErrorCode.PHONE_OK, validator.isPhoneNumberValid(input));
    }


    //isTachNumberValid

    @Test
    public void DigitsValidator_Should_Return_TACH_NULL_ErrorCode_When_TachNumber_Field_IsEmpty() {
        DigitsValidator validator = new DigitsValidator();

        String input = "";

        assertEquals(ErrorCode.TACH_NULL, validator.isTachNumberValid(input));
    }

    @Test
    public void DigitsValidator_Should_Return_TACH_NOT_VALID_ErrorCode_When_TachNumber_contains_Non_Digits() {
        DigitsValidator validator = new DigitsValidator();

        String input = "ff241512";

        assertEquals(ErrorCode.TACH_NOT_VALID, validator.isTachNumberValid(input));

        String inputtwo = "$935252";

        assertEquals(ErrorCode.TACH_NOT_VALID, validator.isTachNumberValid(inputtwo));

    }

    @Test
    public void DigitsValidator_Should_Return_TACH_NOT_VALID_ErrorCode_When_TachNumber_Field_isOver_20digits() {
        DigitsValidator validator = new DigitsValidator();

        String input = "12345678910" +
                "12345678910";

        assertEquals(ErrorCode.TACH_NOT_VALID, validator.isTachNumberValid(input));
    }

    @Test
    public void DigitsValidator_Should_Return_TACH_OK_ErrorCode_When_TachNumber_Field_IsCorrect() {
        DigitsValidator validator = new DigitsValidator();

        String input = "1234567890";

        assertEquals(ErrorCode.TACH_OK, validator.isTachNumberValid(input));
    }


    //isLicenseNumberValid

    @Test
    public void DigitsValidator_Should_Return_LICENSE_NUMBER_NULL_ErrorCode_When_LicenseNumber_Field_IsEmpty() {
        DigitsValidator validator = new DigitsValidator();

        String input = "";

        assertEquals(ErrorCode.LICENSE_NUMBER_NULL, validator.isLicenseNumberValid(input));
    }

    @Test
    public void DigitsValidator_Should_Return_LICENSE_NUMBER_INVALID_ErrorCode_When_LicenseNumber_Field_Contaits_Anything_other_than_Numbers() {
        DigitsValidator validator = new DigitsValidator();

        String input = "zddsd";

        assertEquals(ErrorCode.LICENSE_NUMBER_INVALID, validator.isLicenseNumberValid(input));

        String inputtwo = "$935252";

        assertEquals(ErrorCode.LICENSE_NUMBER_INVALID, validator.isLicenseNumberValid(inputtwo));

        String inputthree = "6463.52352";

        assertEquals(ErrorCode.LICENSE_NUMBER_INVALID, validator.isLicenseNumberValid(inputthree));
    }

    @Test
    public void DigitsValidator_Should_Return_LICENSE_NUMBER_INVALID_ErrorCode_When_LicenseNumber_Field_isOver_20digits() {
        DigitsValidator validator = new DigitsValidator();

        String input = "12345678910123456789102";

        assertEquals(ErrorCode.LICENSE_NUMBER_INVALID, validator.isLicenseNumberValid(input));
    }

    @Test
    public void DigitsValidator_Should_Return_LICENSE_NUMBER_OK_ErrorCode_When_LicenseNumber_Field_IsCorrect() {
        DigitsValidator validator = new DigitsValidator();

        String input = "1234567890";

        assertEquals(ErrorCode.LICENSE_NUMBER_OK, validator.isLicenseNumberValid(input));
    }

}
