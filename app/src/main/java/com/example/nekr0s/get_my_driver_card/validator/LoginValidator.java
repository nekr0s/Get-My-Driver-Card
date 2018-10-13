package com.example.nekr0s.get_my_driver_card.validator;

import com.example.nekr0s.get_my_driver_card.Constants;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.validator.base.Validator;

public class LoginValidator implements Validator<User> {

    @Override
    public boolean isValid(User user) {
        return user != null &&
                isEmailLengthValid(user) &&
                isPasswordLengthValid(user);
    }

    private boolean isEmailLengthValid(User user) {
        return user.getEmail().length() >= Constants.EMAIL_MIN_LENGTH &&
                user.getEmail().length() <= Constants.EMAIL_MAX_LENGTH;
    }

    private boolean isPasswordLengthValid(User user) {
        return user.getPassword().length() >= Constants.PASSWORD_MIN_LENGTH &&
                user.getPassword().length() <= Constants.PASSWORD_MAX_LENGTH;
    }

}
