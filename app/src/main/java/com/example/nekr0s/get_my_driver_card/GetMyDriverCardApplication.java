package com.example.nekr0s.get_my_driver_card;

import android.app.Application;

import com.example.nekr0s.get_my_driver_card.async.AsyncSchedulerProvider;
import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.http.OkHttpHttpRequester;
import com.example.nekr0s.get_my_driver_card.http.base.HttpRequester;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.parsers.GsonJsonParser;
import com.example.nekr0s.get_my_driver_card.parsers.base.JsonParser;
import com.example.nekr0s.get_my_driver_card.repositories.RequestsRepository;
import com.example.nekr0s.get_my_driver_card.repositories.UsersRepository;
import com.example.nekr0s.get_my_driver_card.repositories.base.Repository;
import com.example.nekr0s.get_my_driver_card.repositories.base.RequestRepository;
import com.example.nekr0s.get_my_driver_card.services.HttpRequestsService;
import com.example.nekr0s.get_my_driver_card.services.HttpUsersService;
import com.example.nekr0s.get_my_driver_card.services.base.Service;
import com.example.nekr0s.get_my_driver_card.utils.Constants;

public class GetMyDriverCardApplication extends Application {

    private static SchedulerProvider mSchedulerProvider;
    private static HttpRequester mHttpRequester;
    private static JsonParser<User> mJsonParserUser;
    private static JsonParser<Request> mJsonParserRequest;
    private static Repository<User> mUserRepository;
    private static RequestRepository mRequestRepository;
    private static Service<User> mUsersService;
    private static Service<Request> mRequestsService;

    public static SchedulerProvider getSchedulerProvider() {
        if (mSchedulerProvider == null)
            mSchedulerProvider = AsyncSchedulerProvider.getInstance();

        return mSchedulerProvider;
    }

    public static HttpRequester getHttpRequester() {
        if (mHttpRequester == null)
            mHttpRequester = new OkHttpHttpRequester();

        return mHttpRequester;
    }

    public static JsonParser<User> getJsonParserUser() {
        if (mJsonParserUser == null)
            mJsonParserUser = new GsonJsonParser<>(User.class, User[].class);

        return mJsonParserUser;
    }

    public static JsonParser<Request> getJsonParserRequest() {
        if (mJsonParserRequest == null)
            mJsonParserRequest = new GsonJsonParser<>(Request.class, Request[].class);
        return mJsonParserRequest;
    }

    public static Repository<User> getUsersRepository() {
        if (mUserRepository == null) {
            String serverUrl = Constants.BASE_SERVER_URL + "/users";
            HttpRequester httpRequester = getHttpRequester();
            JsonParser<User> jsonParser = getJsonParserUser();
            mUserRepository = new UsersRepository<>(
                    serverUrl,
                    httpRequester,
                    jsonParser
            );

        }
        return mUserRepository;
    }

    public static RequestRepository getRequestRepository() {
        if (mRequestRepository == null) {
            String serverUrl = Constants.BASE_SERVER_URL + "/requests";
            HttpRequester httpRequester = getHttpRequester();
            JsonParser<Request> jsonParser = getJsonParserRequest();
            mRequestRepository = new RequestsRepository(
                    serverUrl,
                    httpRequester,
                    jsonParser
            );
        }
        return mRequestRepository;
    }

    public static Service<User> getUsersService() {
        if (mUsersService == null)
            mUsersService = new HttpUsersService();

        return mUsersService;
    }

    public static Service<Request> getRequestsService() {
        if (mRequestsService == null)
            mRequestsService = new HttpRequestsService();
        return mRequestsService;
    }

}
