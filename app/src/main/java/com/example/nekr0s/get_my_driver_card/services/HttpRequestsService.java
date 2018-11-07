package com.example.nekr0s.get_my_driver_card.services;

import android.content.Context;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.repositories.base.RequestRepository;
import com.example.nekr0s.get_my_driver_card.services.base.RequestService;

import java.io.IOException;
import java.util.List;

public class HttpRequestsService implements RequestService {

    private final RequestRepository mRequestsRepository;

    public HttpRequestsService(Context context) {
        mRequestsRepository = GetMyDriverCardApplication.getRequestRepository(context);
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

    @Override
    public List<Request> getCurrentUserRequests(int userId) throws IOException {
        return mRequestsRepository.getAllCurrentUser(userId);
    }
}
