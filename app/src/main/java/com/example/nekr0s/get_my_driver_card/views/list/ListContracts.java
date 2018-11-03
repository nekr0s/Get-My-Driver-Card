package com.example.nekr0s.get_my_driver_card.views.list;

import com.example.nekr0s.get_my_driver_card.models.Request;

import java.util.List;

public interface ListContracts {
    interface Presenter {
        void loadRequests();

        void subscribe(View view);

        void unsubscribe();
    }

    interface View {
        void setPresenter(Presenter presenter);

        void showRequests(List<Request> requestList);

        void showEmptyRequestList();

        void showLoading();

        void hideLoading();

        void showError(Throwable throwable);
    }

    interface Navigator {
        void navigateWith(Request request);
    }
}
