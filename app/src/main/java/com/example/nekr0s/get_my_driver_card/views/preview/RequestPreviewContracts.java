package com.example.nekr0s.get_my_driver_card.views.preview;

import com.example.nekr0s.get_my_driver_card.models.Request;

import java.util.List;

public interface RequestPreviewContracts {

    interface Presenter {
        void submit(Request request);

        void subscribe(View view);

        void unsubscribe();
    }

    interface View {
        void setPresenter(Presenter presenter);

        void showLoading();

        void hideLoading();

        void showError(Throwable throwable);

        void navigateToHome(Request request);
    }
}
