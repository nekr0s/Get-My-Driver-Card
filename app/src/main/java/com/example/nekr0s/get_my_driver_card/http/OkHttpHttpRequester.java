package com.example.nekr0s.get_my_driver_card.http;

import com.example.nekr0s.get_my_driver_card.http.base.HttpRequester;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHttpRequester implements HttpRequester {
    @Override
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request)
                .execute();

        assert response.body() != null;
        return response.body().string();
    }

    @Override
    public String post(String url, String body) throws IOException {
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                body
        );

        Request request = new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();

        Response response = client.newCall(request)
                .execute();

        assert response.body() != null;
        return response.body().string();

    }
}
