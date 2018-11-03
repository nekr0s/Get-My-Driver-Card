package com.example.nekr0s.get_my_driver_card.services.base;

import java.io.IOException;
import java.util.List;

public interface Service<T> {
    List<T> getAll() throws IOException;

    T getById(int id) throws IOException;

    T create(T item) throws Exception;
}
