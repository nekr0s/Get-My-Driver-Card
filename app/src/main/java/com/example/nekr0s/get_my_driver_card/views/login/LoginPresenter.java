package com.example.nekr0s.get_my_driver_card.views.login;

import android.content.Context;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.services.base.UsersService;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements LoginContracts.Presenter {

    private final UsersService<User> mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private LoginContracts.View mView;
    // Dont do that!
    private String unHashedPassword;

    LoginPresenter(Context context) {
        mUsersService = GetMyDriverCardApplication.getUsersService(context);
        mSchedulerProvider = GetMyDriverCardApplication.getSchedulerProvider();
    }

    @Override
    public void register(final User user) {
        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User createUser = mUsersService.create(user);
                    if (createUser == null) throw new Exception("User already exists.");
                    unHashedPassword = user.getPassword();
                    emitter.onNext(createUser);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnEach(x -> mView.hideLoading())
                .subscribe(u -> login(u.getUsername(), unHashedPassword), e -> mView.showError(e));
    }

    @Override
    public void login(String username, String password) {
        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<User>) emitter -> {
                    User currentUser = mUsersService.login(username, password);
                    emitter.onNext(currentUser);
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
