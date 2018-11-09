package com.example.nekr0s.get_my_driver_card;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.EmailValidator;
import com.example.nekr0s.get_my_driver_card.validator.base.Validator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidatorTests {


    @Test
    public void Validator_Should_ReturnInvalidErrorCode_When_InvalidEmailEntered() {
        Validator validator = new EmailValidator();

        String input = "sfsfs@sdasa";
        String input2 = "alexander@gmail.com";



        assertEquals(ErrorCode.EMAIL_INVALID, validator.isValid(input));
        assertEquals(ErrorCode.EMAIL_OK, validator.isValid(input2));

    }

}

