package com.example.nekr0s.get_my_driver_card.http.base;

import java.io.IOException;

public interface HttpRequester {
    String get(String url) throws IOException;

    String post(String url, String body) throws IOException;

    String login(String username, String password) throws IOException;
}
