package com.example.nekr0s.get_my_driver_card.validator.base;

public interface Validator<T> {
    boolean isValid(T object);
}
