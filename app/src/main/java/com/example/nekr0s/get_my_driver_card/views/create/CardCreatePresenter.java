package com.example.nekr0s.get_my_driver_card.views.create;

import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.services.base.UsersService;

public class CardCreatePresenter implements CardCreateContracts.Presenter {
    private final UsersService mUsersService;
    private final SchedulerProvider mSchedulerProvider;
    private CardCreateContracts.View mView;

    public CardCreatePresenter(UsersService mUsersService, SchedulerProvider mSchedulerProvider) {
        this.mUsersService = mUsersService;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void subscribe(CardCreateContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void save(Request request) {

    }
}
