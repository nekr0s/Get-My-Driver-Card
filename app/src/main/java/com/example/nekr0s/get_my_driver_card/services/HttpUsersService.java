package com.example.nekr0s.get_my_driver_card.services;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.repositories.base.Repository;
import com.example.nekr0s.get_my_driver_card.services.base.Service;

import java.io.IOException;
import java.util.List;

public class HttpUsersService implements Service<User> {

    private final Repository<User> mRepository;

    public HttpUsersService() {
        mRepository = GetMyDriverCardApplication.getUsersRepository();
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
}
