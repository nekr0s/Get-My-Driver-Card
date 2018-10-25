package com.example.nekr0s.get_my_driver_card.validator.base;

import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;

public interface CreateValidator {
    ErrorCode validate(String email, String password, String password2);
}
