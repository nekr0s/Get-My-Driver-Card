package com.example.nekr0s.get_my_driver_card.repositories.base;

import java.io.IOException;
import java.util.List;

public interface ReqRepository<Request> extends Repository<Request> {
    List<Request> getAllCurrentUser(int userId) throws IOException;
}
