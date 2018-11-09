package com.example.nekr0s.get_my_driver_card;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.EmailValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.Validator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmailValidatorTests {


    @Test
    public void EmailValidator_Should_Return_NULL_ErrorCode_When_EmailFieldIsEmpty() {
        Validator validator = new EmailValidator();

        String input = "";

        assertEquals(ErrorCode.EMAIL_CARD_NULL, validator.isValid(input));
    }

    @Test
    public void EmailValidator_Should_ReturnInvalidErrorCode_When_InvalidEmailEntered() {
        Validator validator = new EmailValidator();

        String input = "sfsfs@sdasa";

        assertEquals(ErrorCode.EMAIL_INVALID, validator.isValid(input));

    }

    @Test
    public void EmailValidator_Should_Return_TOO_LONG_ErrorCode_When_EmailEnteredIsOver256Characters() {
        Validator validator = new EmailValidator();

        String input = "wishipergewishipergewishipergewishipergewishipergewi" +
                "shipergewishipergewishipergewishipergewishipergewishiperge" +
                "wishipergewishipergewishipergewishipergewishipergewishiperge" +
                "wishipergewishipergewishipergewishipergewishipergewishi" +
                "pergewishipergewishipergewishipergewishipergewishiperge" +
                "wishipergewishipergewishipergewishipergewishipergewishipergewishiperge" +
                "wishipergewishipergewishipergewishipergewishipergewishiperge@gmail.com";

        assertEquals(ErrorCode.EMAIL_TOO_LONG, validator.isValid(input));
    }


    @Test
    public void EmailValidator_Should_Return_OK_ErrorCode_When_ValidEmailEntered() {
        Validator validator = new EmailValidator();
        String input2 = "alexander@gmail.com";

        assertEquals(ErrorCode.EMAIL_OK, validator.isValid(input2));
    }


}

