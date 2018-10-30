package com.example.nekr0s.get_my_driver_card.views.login;

import com.example.nekr0s.get_my_driver_card.models.User;

public interface LoginContracts {

    interface Presenter {
        void register(User user);

        void login(String email, String password);

        void subscribe(View view);

        void unsubscribe();
    }

    interface View {
        void setPresenter(Presenter presenter);

        void showLoading();

        void hideLoading();

        void showError(Throwable throwable);

        void navigateToHome(User user);
    }

}
