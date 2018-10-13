package com.example.nekr0s.get_my_driver_card.Validators;

import com.example.nekr0s.get_my_driver_card.Constants;

public class LoginValidator implements Validator<String> {

    @Override
    public boolean isValid(String login) {
        return login != null &&
                isEmailLengthValid(login) &&
                isPasswordLengthValid(login);


    }


    private boolean isEmailLengthValid(String Email) {
        return Email.length() >= Constants.EMAIL_MIN_LENGTH &&
                Email.length() <= Constants.EMAIL_MAX_LENGTH;
    }

    private boolean isPasswordLengthValid(String login) {
        return login.length() >= Constants.PASSWORD_MIN_LENGTH &&
                login.length() <= Constants.PASSWORD_MAX_LENGTH;
    }

}
