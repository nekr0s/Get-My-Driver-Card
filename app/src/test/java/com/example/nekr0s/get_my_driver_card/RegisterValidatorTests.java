package com.example.nekr0s.get_my_driver_card;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;
import com.example.nekr0s.get_my_driver_card.validator.RegisterValidator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegisterValidatorTests {

    @Test
    public void RegisterValidator_Should_Return_USERNAME_NULL_When_Username_Field_IsEmpty() {
        RegisterValidator validator = new RegisterValidator();

        String input = "";

        assertEquals(ErrorCode.USERNAME_NULL, validator.isUsernameValid(input));
    }

    @Test
    public void RegisterValidator_Should_Return_USERNAME_TOO_SIMPLE_When_Username_Field_lessThan_4_digits() {
        RegisterValidator validator = new RegisterValidator();

        String input = "alex";

        assertEquals(ErrorCode.USERNAME_TOO_SIMPLE, validator.isUsernameValid(input));
    }


    @Test
    public void RegisterValidator_Should_Return_USERNAME_OK_When_Username_Field_Is_Correct() {
        RegisterValidator validator = new RegisterValidator();

        String input = "alex96";

        assertEquals(ErrorCode.USERNAME_OK, validator.isUsernameValid(input));
    }

    @Test
    public void RegisterValidator_Should_Return_PASSWORD_NULL_When_Password_Fields_Are_Empty() {
        RegisterValidator validator = new RegisterValidator();

        String password = "";

        String passwordTwo = "";

        assertEquals(ErrorCode.PASSWORD_NULL, validator.isPasswordValid(password, passwordTwo));
    }

    @Test
    public void RegisterValidator_Should_Return_PASSWORD_TOO_SIMPLE_When_Password_Fields_Do_Not_Match_PASSWORD_PATTERN() {
        RegisterValidator validator = new RegisterValidator();

        String password = "ale";

        String passwordTwo = "ale";

        assertEquals(ErrorCode.PASSWORD_TOO_SIMPLE, validator.isPasswordValid(password, passwordTwo));

        String password2 = "my password";

        String passwordTwo2 = "my password";

        assertEquals(ErrorCode.PASSWORD_TOO_SIMPLE, validator.isPasswordValid(password2, passwordTwo2));
    }


    @Test
    public void RegisterValidator_Should_Return_PASSWORD_NULL_When_Password_Fields_Do_Not_Match() {
        RegisterValidator validator = new RegisterValidator();

        String password = "matchedno";

        String passwordTwo = "matchdve";

        assertEquals(ErrorCode.PASSWORDS_DONT_MATCH, validator.isPasswordValid(password, passwordTwo));
    }

    @Test
    public void RegisterValidator_Should_Return_PASSWORD_OK_When_Password_Fields_Are_Correct() {
        RegisterValidator validator = new RegisterValidator();

        String password = "mypassword96";

        String passwordTwo = "mypassword96";

        assertEquals(ErrorCode.PASSWORD_OK, validator.isPasswordValid(password, passwordTwo));
    }
}
