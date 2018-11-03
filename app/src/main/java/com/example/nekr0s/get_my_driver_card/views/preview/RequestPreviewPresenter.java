package com.example.nekr0s.get_my_driver_card.views.preview;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.services.base.Service;

public class RequestPreviewPresenter implements RequestPreviewContracts.Presenter {

    private RequestPreviewContracts.View mView;
    private final SchedulerProvider mSchedulerProvider;
    private final Service<Request> mService;

    public RequestPreviewPresenter() {
        mService = GetMyDriverCardApplication.getRequestsService();
        mSchedulerProvider = GetMyDriverCardApplication.getSchedulerProvider();
    }


    @Override
    public void submit(Request request) {
        // Async task to submit a request
    }

    @Override
    public void subscribe(RequestPreviewContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }
}
