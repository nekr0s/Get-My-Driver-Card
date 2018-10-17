package com.example.nekr0s.get_my_driver_card;

import android.app.Application;

import com.example.nekr0s.get_my_driver_card.async.AsyncSchedulerProvider;
import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.http.OkHttpHttpRequester;
import com.example.nekr0s.get_my_driver_card.http.base.HttpRequester;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.parsers.GsonJsonParser;
import com.example.nekr0s.get_my_driver_card.parsers.base.JsonParser;
import com.example.nekr0s.get_my_driver_card.repositories.HttpRepository;
import com.example.nekr0s.get_my_driver_card.repositories.base.Repository;
import com.example.nekr0s.get_my_driver_card.services.HttpUsersService;
import com.example.nekr0s.get_my_driver_card.services.base.UsersService;
import com.example.nekr0s.get_my_driver_card.utils.Constants;

public class GetMyDriverCardApplication extends Application {

    private static SchedulerProvider mSchedulerProvider;
    private static HttpRequester mHttpRequester;
    private static JsonParser<User> mJsonParser;
    private static Repository<User> mRepository;
    private static UsersService mUsersService;

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

    public static JsonParser<User> getJsonParser() {
        if (mJsonParser == null)
            mJsonParser = new GsonJsonParser<>(User.class, User[].class);

        return mJsonParser;
    }

    public static Repository<User> getUsersRepository() {
        if (mRepository == null) {
            String serverUrl = Constants.BASE_SERVER_URL + "/users";
            HttpRequester httpRequester = getHttpRequester();
            JsonParser<User> jsonParser = getJsonParser();
            mRepository = new HttpRepository<>(
                    serverUrl,
                    httpRequester,
                    jsonParser
            );

        }
        return mRepository;
    }

    public static UsersService getUsersService() {
        if (mUsersService == null)
            mUsersService = new HttpUsersService();

        return mUsersService;
    }

}
