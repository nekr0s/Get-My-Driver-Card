package com.example.nekr0s.get_my_driver_card.services;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.repositories.base.Repository;
import com.example.nekr0s.get_my_driver_card.services.base.UsersService;

import java.io.IOException;
import java.util.List;

public class HttpUsersService implements UsersService {

    private final Repository<User> mRepository;

    public HttpUsersService() {
        mRepository = GetMyDriverCardApplication.getUsersRepository();
    }

    @Override
    public List<User> getAllUsers() throws IOException {
        return mRepository.getAll();
    }

    @Override
    public User getUserById(int id) throws IOException {
        return mRepository.getById(id);
    }

    @Override
    public User createUser(User user) throws Exception {
        return mRepository.add(user);
    }
}
