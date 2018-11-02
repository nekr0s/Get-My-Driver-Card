package com.example.nekr0s.get_my_driver_card.repositories;

import com.example.nekr0s.get_my_driver_card.http.base.HttpRequester;
import com.example.nekr0s.get_my_driver_card.parsers.base.JsonParser;
import com.example.nekr0s.get_my_driver_card.repositories.base.Repository;

import java.io.IOException;
import java.util.List;

public class RequestsRepository<Request> implements Repository<Request> {

    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<Request> mJsonParser;

    public RequestsRepository(String mServerUrl, HttpRequester mHttpRequester,
                              JsonParser<Request> mJsonParser) {
        this.mServerUrl = mServerUrl;
        this.mHttpRequester = mHttpRequester;
        this.mJsonParser = mJsonParser;
    }

    @Override
    public List<Request> getAll() throws IOException {
        String jsonArray = null;
        jsonArray = mHttpRequester.get(mServerUrl + "/secured/all");
        return mJsonParser.fromJsonArray(jsonArray);
    }

    @Override
    public Request getById(int id) throws IOException {
        String url = mServerUrl + "/" + id;
        String json = mHttpRequester.get(url);
        return mJsonParser.fromJson(json);
    }

    @Override
    public Request add(Request item) throws IOException {
        String requestBody = mJsonParser.toJson(item);
        String responseBody = mHttpRequester.post(mServerUrl + "/signup", requestBody);
        return mJsonParser.fromJson(responseBody);
    }

    public List<Request> getAllCurrentUser(int id) throws IOException {
        String jsonArray = null;
        jsonArray = mHttpRequester.get(mServerUrl + "/secured" + id + "/all");
        return mJsonParser.fromJsonArray(jsonArray);
    }
}
