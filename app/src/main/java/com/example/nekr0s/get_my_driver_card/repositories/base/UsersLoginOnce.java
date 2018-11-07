package com.example.nekr0s.get_my_driver_card.repositories.base;

import java.io.IOException;

public interface UsersLoginOnce<T> extends Repository<T> {
    T login(String username, String password) throws IOException;
}
