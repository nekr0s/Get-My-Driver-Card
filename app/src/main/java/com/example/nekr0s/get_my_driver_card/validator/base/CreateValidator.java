package com.example.nekr0s.get_my_driver_card.validator.base;

public interface CreateValidator<T> {
    boolean isValid(T object1, T object2, T object3);
}
