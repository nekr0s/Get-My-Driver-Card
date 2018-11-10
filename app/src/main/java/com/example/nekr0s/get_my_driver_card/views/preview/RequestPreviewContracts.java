package com.example.nekr0s.get_my_driver_card.views.preview;

import android.graphics.Bitmap;

import com.example.nekr0s.get_my_driver_card.models.Request;

public interface RequestPreviewContracts {

    interface Presenter {
        void submit(Request request);

        void update(Request request);

        void subscribe(View view);

        void unsubscribe();

        Bitmap convertStringBytesToBitmap(String bytes);
    }

    interface View {
        void setPresenter(Presenter presenter);

        void finishHim(Request request);

        void showLoading();

        void hideLoading();

        void showError(Throwable throwable);

        void navigateToList(Request request);


    }
}
