package com.example.nekr0s.get_my_driver_card.views.list;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.services.base.Service;

public class ListPresenter implements ListContracts.Presenter {

    private final SchedulerProvider mSchedulerProvider;
    private final Service<Request> mRequestsService;

    public ListPresenter() {
        mSchedulerProvider = GetMyDriverCardApplication.getSchedulerProvider();
        mRequestsService = GetMyDriverCardApplication.getRequestsService();
    }

    @Override
    public void loadRequests() {
    }

    @Override
    public void subscribe(ListContracts.View view) {

    }

    @Override
    public void unsubscribe() {

    }
}
