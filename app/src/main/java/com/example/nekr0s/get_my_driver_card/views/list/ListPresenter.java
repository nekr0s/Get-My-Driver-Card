package com.example.nekr0s.get_my_driver_card.views.list;

import android.content.Context;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.services.base.RequestService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class ListPresenter implements ListContracts.Presenter {

    private final SchedulerProvider mSchedulerProvider;
    private final RequestService mRequestsService;
    private ListContracts.View mView;

    ListPresenter(Context context) {
        mSchedulerProvider = GetMyDriverCardApplication.getSchedulerProvider();
        mRequestsService = GetMyDriverCardApplication.getRequestsService(context);
    }

    @Override
    public void loadRequestsAdmin() {
        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<List<Request>>) emitter -> {
                    List<Request> requests = mRequestsService.getAll();
                    emitter.onNext(requests);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnEach(x -> mView.hideLoading())
                .subscribe(
                        this::presentRequestsToView,
                        error -> mView.showError(error)
                );
    }

    @Override
    public void loadRequestsUser(int userId) {
        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<List<Request>>) emitter -> {
                    List<Request> requests = mRequestsService.getCurrentUserRequests(userId);
                    emitter.onNext(requests);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnEach(x -> mView.hideLoading())
                .subscribe(
                        this::presentRequestsToView,
                        error -> mView.showError(error)
                );
    }

    @Override
    public void subscribe(ListContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public void selectRequest(Request request) {
        mView.showRequestPreview(request);
    }

    private void presentRequestsToView(List<Request> requestList) {
        if (requestList.isEmpty())
            mView.showEmptyRequestList();
        else
            mView.showRequests(requestList);
    }
}
