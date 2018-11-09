package com.example.nekr0s.get_my_driver_card.views.login;

import android.support.design.widget.TextInputLayout;

import com.example.nekr0s.get_my_driver_card.models.User;
import com.example.nekr0s.get_my_driver_card.utils.enums.ErrorCode;

import java.util.Map;
import java.util.Set;

public interface LoginContracts {

    interface Presenter {
        void register(User user);

        void login(String email, String password);

        void subscribe(View view);

        void unsubscribe();

        Set<ErrorCode> checkCredentials(String s, String toString, String string);
    }

    interface View {
        void setPresenter(Presenter presenter);

        void showLoading();

        void hideLoading();

        void setRegisterErrors(Set<ErrorCode> errors, Map<String, TextInputLayout> tils);

        void showError(Throwable throwable);

        void navigateToHome(User user);
    }

}
