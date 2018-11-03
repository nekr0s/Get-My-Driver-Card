package com.example.nekr0s.get_my_driver_card.services.base;

import com.example.nekr0s.get_my_driver_card.models.Request;

import java.io.IOException;
import java.util.List;

public interface RequestService extends Service<Request> {
    List<Request> getCurrentUserRequests(int userId) throws IOException;
}
