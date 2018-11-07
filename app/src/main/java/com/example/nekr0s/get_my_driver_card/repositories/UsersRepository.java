package com.example.nekr0s.get_my_driver_card.repositories;

import com.example.nekr0s.get_my_driver_card.http.base.HttpRequester;
import com.example.nekr0s.get_my_driver_card.parsers.base.JsonParser;
import com.example.nekr0s.get_my_driver_card.repositories.base.UsersLoginOnce;

import java.io.IOException;
import java.util.List;

public class UsersRepository<T> implements UsersLoginOnce<T> {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<T> mJsonParser;

    public UsersRepository(String mServerUrl, HttpRequester mHttpRequester, JsonParser<T> mJsonParser) {
        this.mServerUrl = mServerUrl;
        this.mHttpRequester = mHttpRequester;
        this.mJsonParser = mJsonParser;
    }

    @Override
    public List<T> getAll() throws IOException {
        String jsonArray = null;
        jsonArray = mHttpRequester.get(mServerUrl + "/secured/all");
        return mJsonParser.fromJsonArray(jsonArray);
    }

    @Override
    public T getById(int id) throws IOException {
        String url = mServerUrl + "/" + id;
        String json = mHttpRequester.get(url);
        return mJsonParser.fromJson(json);
    }

    @Override
    public T add(T item) throws IOException {
        String requestBody = mJsonParser.toJson(item);
        String responseBody = mHttpRequester.post(mServerUrl + "/signup", requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    @Override
    public T login(String username, String password) throws IOException {
        String responseBody = mHttpRequester.login(username, password);
        return mJsonParser.fromJson(responseBody);
    }
}
