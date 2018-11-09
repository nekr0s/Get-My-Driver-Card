package com.example.nekr0s.get_my_driver_card;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.NamesValidator;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class NamesValidatorTests {


    //isFirstNameValid Tests
    @Test
    public void NamesValidator_Should_Return_NAME_NULL_ErrorCode_When_FirstName_Field_isEmpty() {
        NamesValidator validator = new NamesValidator();

        String input = "";

        assertEquals(ErrorCode.NAME_NULL, validator.isFirstNameValid(input));
    }


    @Test
    public void NamesValidator_Should_Return_NAME_TOO_LONG_ErrorCode_When_FirstName_Field_is_Over_256() {
        NamesValidator validator = new NamesValidator();

        String input = "azobichamtestoveazobichamtestove" +
                "azobichamtestoveazobichamtestoveazobicham" +
                "testoveazobichamtestoveazobichamtestoveazo" +
                "azobichamtestoveazobichamtestovebichamtestove" +
                "azobichamtestoveazobichamtestoveazobichamtest" +
                "azobichamtestoveazobichamtestoveoveazobichamtestove" +
                "azobichamtestoveazobichamtestoveazobichamtestoveazobichamtestove" +
                "azobichamtestoveazobichamtestoveazobichamtestoveazobichamtestove" +
                "azobichamtestoveazobichamtestoveazobichamtestoveazobichamtestove";

        assertEquals(ErrorCode.NAME_TOO_LONG, validator.isFirstNameValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_NAME_OK_ErrorCode_When_FirstName_Field_is_Correct() {
        NamesValidator validator = new NamesValidator();

        String input = "Ivan Petran";

        assertEquals(ErrorCode.NAME_OK, validator.isFirstNameValid(input));

        String inputtwo = "Jack O'Donald";

        assertEquals(ErrorCode.NAME_OK, validator.isFirstNameValid(inputtwo));

    }


    //isLastNameValid Tests
    @Test
    public void NamesValidator_Should_Return_LAST_NAME_NULL_ErrorCode_When_LastName_Field_isEmpty() {
        NamesValidator validator = new NamesValidator();

        String input = "";

        assertEquals(ErrorCode.LAST_NAME_NULL, validator.isLastNameValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_LAST_NAME_TOO_LONG_ErrorCode_When_Last_Name_Field_is_Over_256() {
        NamesValidator validator = new NamesValidator();

        String input = "azobichamtestoveazobichamtestove" +
                "azobichamtestoveazobichamtestoveazobicham" +
                "testoveazobichamtestoveazobichamtestoveazo" +
                "azobichamtestoveazobichamtestovebichamtestove" +
                "azobichamtestoveazobichamtestoveazobichamtest" +
                "azobichamtestoveazobichamtestoveoveazobichamtestove" +
                "azobichamtestoveazobichamtestoveazobichamtestoveazobichamtestove" +
                "azobichamtestoveazobichamtestoveazobichamtestoveazobichamtestove" +
                "azobichamtestoveazobichamtestoveazobichamtestoveazobichamtestove";

        assertEquals(ErrorCode.LAST_NAME_TOO_LONG, validator.isLastNameValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_NAME_OK_ErrorCode_When_LastName_Field_is_Correct() {
        NamesValidator validator = new NamesValidator();

        String input = "Georgiev";

        assertEquals(ErrorCode.NAME_OK, validator.isFirstNameValid(input));

        String inputtwo = "Parkinson";

        assertEquals(ErrorCode.NAME_OK, validator.isFirstNameValid(inputtwo));

    }


    //isFirstNameCyrValid Tests
    @Test
    public void NamesValidator_Should_Return_CYR_NAME_NULL_ErrorCode_When_Cyr_FirstName_Field_isEmpty() {
        NamesValidator validator = new NamesValidator();

        String input = "";

        assertEquals(ErrorCode.CYR_NAME_NULL, validator.isFirstNameCyrValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_CYR_NAME_TOO_LONG_ErrorCode_When_Cyr_FirstName_Field_is_Over_256() {
        NamesValidator validator = new NamesValidator();

        String input = "ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ" +
                "ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ" +
                "ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ" +
                "ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ" +
                "ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ";

        assertEquals(ErrorCode.CYR_NAME_TOO_LONG, validator.isFirstNameCyrValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_CYR_NAME_OK_ErrorCode_When_FirstNameCyr_Field_is_Correct() {
        NamesValidator validator = new NamesValidator();

        String input = "Александър";

        assertEquals(ErrorCode.CYR_NAME_OK, validator.isFirstNameCyrValid(input));

        String inputtwo = "ПЕткан";

        assertEquals(ErrorCode.CYR_NAME_OK, validator.isFirstNameCyrValid(inputtwo));

    }


    //isLastNameCyrValid Tests
    @Test
    public void NamesValidator_Should_Return_CYR_LAST_NAME_NULL_ErrorCode_When_Cyr_LastName_Field_isEmpty() {
        NamesValidator validator = new NamesValidator();

        String input = "";

        assertEquals(ErrorCode.CYR_LAST_NAME_NULL, validator.isLastNameCyrValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_CYR_LAST_NAME_TOO_LONG_ErrorCode_When_Cyr_LastName_Field_is_Over_256() {
        NamesValidator validator = new NamesValidator();

        String input = "ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ" +
                "ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ" +
                "ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ" +
                "ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ" +
                "ТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТТЕСТ";

        assertEquals(ErrorCode.CYR_LAST_NAME_TOO_LONG, validator.isLastNameCyrValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_CYR_LAST_NAME_OK_ErrorCode_When_LastNameCyr_Field_is_Correct() {
        NamesValidator validator = new NamesValidator();

        String input = "Петканов";

        assertEquals(ErrorCode.CYR_LAST_NAME_OK, validator.isLastNameCyrValid(input));

        String inputtwo = "Георгиевич";

        assertEquals(ErrorCode.CYR_LAST_NAME_OK, validator.isLastNameCyrValid(inputtwo));

    }


    //isAddressValid Tests
    @Test
    public void NamesValidator_Should_Return_ADDRESS_NULL_ErrorCode_When_Address_Field_isEmpty() {
        NamesValidator validator = new NamesValidator();

        String input = "";

        assertEquals(ErrorCode.ADDRESS_NULL, validator.isAddressValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_ADDRESS_TOO_LONG_ErrorCode_When_Address_Field_is_Over_100_characters() {
        NamesValidator validator = new NamesValidator();

        String input = "Strelbishte Midia 10,Strelbishte Midia 10,Strelbishte Midia 10,Strelbishte Midia 10" +
                "Strelbishte Midia 10.Strelbishte Midia 10.Strelbishte Midia 10.Strelbishte Midia 10" +
                "Strelbishte Midia 10.Strelbishte Midia 10" +
                ",Strelbishte Midia 10 ";

        assertEquals(ErrorCode.ADDRESS_TOO_LONG, validator.isAddressValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_ADDRESS_OK_ErrorCode_When_Address_Field_is_Correct() {
        NamesValidator validator = new NamesValidator();

        String input = "Ul. Midia 10 , Strelbishte , Sofia , Blok 10 , ap 3 , kvartal whatever";

        assertEquals(ErrorCode.ADDRESS_OK, validator.isAddressValid(input));
    }


    //isIssuerAuthorityValid Tests
    @Test
    public void NamesValidator_Should_Return_ISSUING_AUTHORITY_NULL_ErrorCode_When_IssuingAuthority_Field_isEmpty() {
        NamesValidator validator = new NamesValidator();

        String input = "";

        assertEquals(ErrorCode.ISSUING_AUTHORITY_NULL, validator.isIssuerAuthorityValid(input));
    }


    @Test
    public void NamesValidator_Should_Return_ISSUING_AUTHORITY_INVALID_ErrorCode_When_IssuingAuthority_Field_is_Over_50() {
        NamesValidator validator = new NamesValidator();

        String input = "azobichamtestoveazobichamtestove" +
                "azobichamtestoveazobichamtestoveazobicham" +
                "testoveazobichamtestoveazobichamtestoveazo" +
                "azobichamtestoveazobichamtestovebichamtestove";

        assertEquals(ErrorCode.ISSUING_AUTHORITY_INVALID, validator.isIssuerAuthorityValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_ISSUING_AUTHORITY_OK_ErrorCode_When_IssuingAuthority_Field_is_Correct() {
        NamesValidator validator = new NamesValidator();

        String input = "ME VE RE SOFIA , Bulgaria 1251251";

        assertEquals(ErrorCode.ISSUING_AUTHORITY_OK, validator.isIssuerAuthorityValid(input));

        String inputtwo = "Granichna Policiq TUTRAKAN , 35352 zdr ";

        assertEquals(ErrorCode.ISSUING_AUTHORITY_OK, validator.isIssuerAuthorityValid(inputtwo));

    }


    //isEuCountryOfIssuingValid Tests
    @Test
    public void NamesValidator_Should_Return_COUNTRY_NULL_ErrorCode_When_EuCountryOfIssuing_Field_isEmpty() {
        NamesValidator validator = new NamesValidator();

        String input = "";

        assertEquals(ErrorCode.COUNTRY_NULL, validator.isEuCountryOfIssuingValid(input));
    }


    @Test
    public void NamesValidator_Should_Return_COUNTRY_TOO_LONG_ErrorCode_When_EuCountryOfIssuing_Field_is_Over_35_Characters() {
        NamesValidator validator = new NamesValidator();

        String input = "BulgariaBulgariaBulgariaBulgaria" +
                "BulgariaBulgariaBulgariaBulgariaBulgariaBulgaria" +
                "BulgariaBulgariaBulgariaBulgariaBulgariaBulgaria";

        assertEquals(ErrorCode.COUNTRY_TOO_LONG, validator.isEuCountryOfIssuingValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_COUNTRY_OK_ErrorCode_When_EuCountryOfIssuing_Field_is_Correct() {
        NamesValidator validator = new NamesValidator();

        String input = "Germany";

        assertEquals(ErrorCode.COUNTRY_OK, validator.isEuCountryOfIssuingValid(input));

        String inputtwo = "Obedineni Balkanski Shtati";

        assertEquals(ErrorCode.COUNTRY_OK, validator.isEuCountryOfIssuingValid(inputtwo));

    }


    //isLicenseCountryOfIssuingValid Tests
    @Test
    public void NamesValidator_Should_Return_LICENSE_COUNTRY_ISSUER_NULL_ErrorCode_When_DrivingLicenseCountryOfIssuing_Field_isEmpty() {
        NamesValidator validator = new NamesValidator();

        String input = "";

        assertEquals(ErrorCode.LICENSE_COUNTRY_ISSUER_NULL, validator.isLicenseCountryOfIssuingValid(input));
    }


    @Test
    public void NamesValidator_Should_Return_LICENSE_COUNTRY_ISSUER_INVALID_ErrorCode_When_DrivingLicenseCountryOfIssuing_Field_is_Over_35_Characters() {
        NamesValidator validator = new NamesValidator();

        String input = "BulgariaBulgariaBulgariaBulgaria" +
                "BulgariaBulgariaBulgariaBulgariaBulgariaBulgaria" +
                "BulgariaBulgariaBulgariaBulgariaBulgariaBulgaria";

        assertEquals(ErrorCode.LICENSE_COUNTRY_ISSUER_INVALID, validator.isLicenseCountryOfIssuingValid(input));
    }

    @Test
    public void NamesValidator_Should_Return_LICENSE_COUNTRY_ISSUER_OK_ErrorCode_When_DrivingLicenseCountryOfIssuing_Field_is_Correct() {
        NamesValidator validator = new NamesValidator();

        String input = "Serbia";

        assertEquals(ErrorCode.LICENSE_COUNTRY_ISSUER_OK, validator.isLicenseCountryOfIssuingValid(input));

        String inputtwo = "Portugal";

        assertEquals(ErrorCode.LICENSE_COUNTRY_ISSUER_OK, validator.isLicenseCountryOfIssuingValid(inputtwo));

    }


}
