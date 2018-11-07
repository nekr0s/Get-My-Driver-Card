package com.example.nekr0s.get_my_driver_card.views.preview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.nekr0s.get_my_driver_card.GetMyDriverCardApplication;
import com.example.nekr0s.get_my_driver_card.async.base.SchedulerProvider;
import com.example.nekr0s.get_my_driver_card.models.Request;
import com.example.nekr0s.get_my_driver_card.services.base.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class RequestPreviewPresenter implements RequestPreviewContracts.Presenter {

    private RequestPreviewContracts.View mView;
    private final SchedulerProvider mSchedulerProvider;
    private final Service<Request> mService;

    public RequestPreviewPresenter(Context context) {
        mService = GetMyDriverCardApplication.getRequestsService(context);
        mSchedulerProvider = GetMyDriverCardApplication.getSchedulerProvider();
    }


    @Override
    public void submit(Request request) {
        // Async task to submit a request
        mView.showLoading();
        Disposable disposable = Observable
                .create((ObservableOnSubscribe<Request>) emitter -> {
                    Request createRequest = mService.create(request);
                    emitter.onNext(createRequest);
                    emitter.onComplete();
                })
                .subscribeOn(mSchedulerProvider.background())
                .observeOn(mSchedulerProvider.ui())
                .doOnEach(x -> mView.hideLoading())
                .doOnError(mView::showError)
                .subscribe(s -> mView.navigateToList(request));
    }

    @Override
    public void subscribe(RequestPreviewContracts.View view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    @Override
    public Bitmap convertStringBytesToBitmap(String bytes) {
        InputStream stream = new ByteArrayInputStream(Base64
                .decode(bytes.getBytes(), Base64.DEFAULT));
        return BitmapFactory.decodeStream(stream);
    }
}
