package com.example.nekr0s.get_my_driver_card;

import android.app.Application;
import android.content.Context;

import com.example.nekr0s.get_my_driver_card.async.AsyncSchedulerProvider;
import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.http.OkHttpHttpRequester;
import com.example.nekr0s.get_my_driver_card.http.base.HttpRequester;
import com.example.nekr0s.get_my_driver_card.models.Attachment;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.parsers.GsonJsonParser;
import com.example.nekr0s.get_my_driver_card.parsers.base.JsonParser;
import com.example.nekr0s.get_my_driver_card.repositories.RequestsRepository;
import com.example.nekr0s.get_my_driver_card.repositories.UsersRepository;
import com.example.nekr0s.get_my_driver_card.repositories.base.Repository;
import com.example.nekr0s.get_my_driver_card.repositories.base.RequestRepository;
import com.example.nekr0s.get_my_driver_card.repositories.base.UsersLoginOnce;
import com.example.nekr0s.get_my_driver_card.services.HttpRequestsService;
import com.example.nekr0s.get_my_driver_card.services.HttpUsersService;
import com.example.nekr0s.get_my_driver_card.services.base.RequestService;
import com.example.nekr0s.get_my_driver_card.services.base.UsersService;
import com.example.nekr0s.get_my_driver_card.utils.Constants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

public class GetMyDriverCardApplication extends Application {

    private static SchedulerProvider mSchedulerProvider;
    private static HttpRequester mHttpRequester;
    private static JsonParser<User> mJsonParserUser;
    private static JsonParser<Request> mJsonParserRequest;
    private static UsersLoginOnce<User> mUserRepository;
    private static RequestRepository mRequestRepository;
    private static UsersService<User> mUsersService;
    private static RequestService mRequestsService;
    private static Repository<Attachment> mAttachmentRepository;
    private static JsonParser<Attachment> mJsonParserAttachment;
    private static ClearableCookieJar mCookieJar;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

    public static SchedulerProvider getSchedulerProvider() {
        if (mSchedulerProvider == null)
            mSchedulerProvider = AsyncSchedulerProvider.getInstance();

        return mSchedulerProvider;
    }

    public static HttpRequester getHttpRequester(Context context) {
        if (mHttpRequester == null)
            mHttpRequester = new OkHttpHttpRequester(context);

        return mHttpRequester;
    }

    public static JsonParser<User> getJsonParserUser() {
        if (mJsonParserUser == null)
            mJsonParserUser = new GsonJsonParser<>(User.class, User[].class);

        return mJsonParserUser;
    }

    public static JsonParser<Attachment> getJsonParserAttachment() {
        if (mJsonParserAttachment == null)
            mJsonParserAttachment = new GsonJsonParser<>(Attachment.class, Attachment[].class);

        return mJsonParserAttachment;
    }

    public static JsonParser<Request> getJsonParserRequest() {
        if (mJsonParserRequest == null)
            mJsonParserRequest = new GsonJsonParser<>(Request.class, Request[].class);
        return mJsonParserRequest;
    }

    public static UsersLoginOnce<User> getUsersRepository(Context context) {
        if (mUserRepository == null) {
            String serverUrl = Constants.BASE_SERVER_URL + "/users";
            HttpRequester httpRequester = getHttpRequester(context);
            JsonParser<User> jsonParser = getJsonParserUser();
            mUserRepository = new UsersRepository<>(
                    serverUrl,
                    httpRequester,
                    jsonParser
            );

        }
        return mUserRepository;
    }

    public static Repository<Attachment> getAttachmentRepository(Context context) {
        if (mAttachmentRepository == null) {
            String serverUrl = Constants.BASE_SERVER_URL + "/attachments";
            HttpRequester httpRequester = getHttpRequester(context);
            JsonParser<Attachment> jsonParser = getJsonParserAttachment();
            mAttachmentRepository = new UsersRepository<>(
                    serverUrl,
                    httpRequester,
                    jsonParser
            );

        }
        return mAttachmentRepository;
    }

    public static RequestRepository getRequestRepository(Context context) {
        if (mRequestRepository == null) {
            String serverUrl = Constants.BASE_SERVER_URL + "/requests";
            HttpRequester httpRequester = getHttpRequester(context);
            JsonParser<Request> jsonParser = getJsonParserRequest();
            mRequestRepository = new RequestsRepository(
                    serverUrl,
                    httpRequester,
                    jsonParser
            );
        }
        return mRequestRepository;
    }

    public static UsersService<User> getUsersService(Context context) {
        if (mUsersService == null)
            mUsersService = new HttpUsersService(context);

        return mUsersService;
    }

    public static RequestService getRequestsService(Context context) {
        if (mRequestsService == null)
            mRequestsService = new HttpRequestsService(context);
        return mRequestsService;
    }

    public static ClearableCookieJar getCookieJar(Context ctx) {
        if (mCookieJar == null)
            mCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(ctx));
        return mCookieJar;
    }
}
