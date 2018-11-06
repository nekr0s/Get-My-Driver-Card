package com.example.nekr0s.get_my_driver_card.validator.base;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;

public interface ValidatorLogin {

    ErrorCode isUsernameValid(String input);

    ErrorCode isPasswordValid(String input, String confirmPassword);
}
