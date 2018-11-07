package com.example.nekr0s.get_my_driver_card.services;

import android.content.Context;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.repositories.base.UsersLoginOnce;
import com.example.nekr0s.get_my_driver_card.services.base.UsersService;

import java.io.IOException;
import java.util.List;

public class HttpUsersService implements UsersService<User> {

    private final UsersLoginOnce<User> mRepository;

    public HttpUsersService(Context context) {
        mRepository = GetMyDriverCardApplication.getUsersRepository(context);
    }

    @Override
    public List<User> getAll() throws IOException {
        return mRepository.getAll();
    }

    @Override
    public User getById(int id) throws IOException {
        return mRepository.getById(id);
    }

    @Override
    public User create(User item) throws Exception {
        return mRepository.add(item);
    }

    @Override
    public User login(String username, String password) throws IOException {
        return mRepository.login(username, password);
    }
}
