package com.example.nekr0s.get_my_driver_card.services;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.repositories.base.Repository;
import com.example.nekr0s.get_my_driver_card.services.base.Service;

import java.io.IOException;
import java.util.List;

public class HttpRequestsService implements Service<Request> {

    private final Repository<Request> mRequestsRepository;

    public HttpRequestsService() {
        mRequestsRepository = GetMyDriverCardApplication.getRequestRepository();
    }

    @Override
    public List<Request> getAll() throws IOException {
        return mRequestsRepository.getAll();
    }

    @Override
    public Request getById(int id) throws IOException {
        return mRequestsRepository.getById(id);
    }

    @Override
    public Request create(Request item) throws Exception {
        return mRequestsRepository.add(item);
    }
}
