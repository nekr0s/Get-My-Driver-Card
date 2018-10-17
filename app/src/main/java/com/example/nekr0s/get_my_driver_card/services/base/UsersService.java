package com.example.nekr0s.get_my_driver_card.services.base;

import com.example.nekr0s.get_my_driver_card.models.User;

import java.io.IOException;
import java.util.List;

public interface UsersService {
    List<User> getAllUsers() throws IOException;

    User getUserById(int id) throws IOException;

    User createUser(User user) throws Exception;
}
