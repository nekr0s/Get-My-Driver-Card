package com.example.nekr0s.get_my_driver_card;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.DateValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.ValidatorDate;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DateValidatorTests {


    @Test
    public void DateValidator_Should_Return_NULL_ErrorCode_When_Date_Field_isEmpty() {
        ValidatorDate validator = new DateValidator();

        String input = "";

        assertEquals(ErrorCode.DATE_NULL, validator.isDateValid(input));
    }


    @Test
    public void DateValidator_Should_Return_INVALID_ErrorCode_When_Date_Field_is_not_valid() {

        //Date must be in format dd.mm.yyyy
        //Day should not be over 31
        //Month should not be over 12
        //year not bellow 2000

        ValidatorDate validator = new DateValidator();

        String wrongFormat = "24.05/2014";
        assertEquals(ErrorCode.DATE_INVALID, validator.isDateValid(wrongFormat));

        String DayOver31 = "24.05/2014";
        assertEquals(ErrorCode.DATE_INVALID, validator.isDateValid(DayOver31));

        String MonthOver12 = "24.15.2015";
        assertEquals(ErrorCode.DATE_INVALID, validator.isDateValid(MonthOver12));

        String YearNotValid = "24.15.1995";
        assertEquals(ErrorCode.DATE_INVALID, validator.isDateValid(YearNotValid));


    }

    @Test
    public void DateValidator_Should_Return_OK_ErrorCode_When_Date_Field_is_Correct() {
        ValidatorDate validator = new DateValidator();

        String input = "24.05.2015";

        assertEquals(ErrorCode.DATE_OK, validator.isDateValid(input));
    }
}
