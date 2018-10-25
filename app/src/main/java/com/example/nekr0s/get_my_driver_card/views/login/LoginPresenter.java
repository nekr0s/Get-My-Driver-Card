package com.example.nekr0s.get_my_driver_card.views.login;

import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.services.base.UsersService;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class LoginPresenter implements LoginContracts.Presenter {

    private final UsersService mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private LoginContracts.View mView;

    public LoginPresenter(UsersService mUsersService, SchedulerProvider mSchedulerProvider) {
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
    public void login(User user) {

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
