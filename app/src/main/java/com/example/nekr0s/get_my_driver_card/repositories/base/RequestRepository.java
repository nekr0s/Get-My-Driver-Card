package com.example.nekr0s.get_my_driver_card.repositories.base;

import com.example.nekr0s.get_my_driver_card.models.Request;

import java.io.IOException;
import java.util.List;

public interface RequestRepository extends Repository<Request> {
    List<Request> getAllCurrentUser(int userId) throws IOException;

    void updateStatus(Request request) throws IOException;
}
