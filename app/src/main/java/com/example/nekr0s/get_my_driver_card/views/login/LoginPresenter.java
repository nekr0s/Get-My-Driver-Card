package com.example.nekr0s.get_my_driver_card.views.login;

import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.services.base.UsersService;
import com.example.nekr0s.get_my_driver_card.utils.Constants;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements LoginContracts.Presenter {

    private final UsersService mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private LoginContracts.View mView;

    LoginPresenter(UsersService mUsersService, SchedulerProvider mSchedulerProvider) {
        this.mUsersService = mUsersService;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void register(final User user) {
        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User createUser = mUsersService.createUser(user);
                    emitter.onNext(createUser);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnEach(x -> mView.hideLoading())
                .doOnError(mView::showError)
                .subscribe(s -> mView.navigateToHome(user));
    }

    @Override
    public void login(String email, String password) {
        mView.showLoading();
        final String url = Constants.BASE_SERVER_URL + "/users/me";
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    // Populate
                    HttpAuthentication authHeader = new HttpBasicAuthentication(email, password);
                    HttpHeaders requestHeaders = new HttpHeaders();
                    requestHeaders.setAuthorization(authHeader);
                    requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

                    // Create rest template
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                    ResponseEntity<User> responseEntity = restTemplate
                            .exchange(url, HttpMethod.GET, new HttpEntity<>(requestHeaders),
                                    User.class);
                    emitter.onNext(responseEntity.getBody());
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnEach(x -> mView.hideLoading())
                .subscribe(s -> mView.navigateToHome(s), e -> mView.showError(e));
    }

    @Override
    public void subscribe(LoginContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
