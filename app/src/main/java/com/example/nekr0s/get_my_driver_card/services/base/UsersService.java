package com.example.nekr0s.get_my_driver_card.services.base;

import java.io.IOException;

public interface UsersService<T> extends Service<T> {
    T login(String username, String password) throws IOException;
}
